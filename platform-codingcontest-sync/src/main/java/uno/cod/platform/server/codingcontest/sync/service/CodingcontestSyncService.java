package uno.cod.platform.server.codingcontest.sync.service;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uno.cod.platform.server.codingcontest.sync.dto.*;
import uno.cod.platform.server.codingcontest.sync.dto.game.CodingContestGameDto;
import uno.cod.platform.server.codingcontest.sync.dto.game.PuzzleDto;
import uno.cod.platform.server.codingcontest.sync.dto.game.PuzzleTestDto;
import uno.cod.platform.server.core.domain.*;
import uno.cod.platform.server.core.repository.*;
import uno.cod.storage.PlatformStorage;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@Transactional
public class CodingcontestSyncService {
    private static final String DRONES_UUID = "bc14f054-3ef5-4816-8255-0b30c2a22856";
    private static final String DRONES_2D_UUID = "3890c253-e754-4185-a9bb-6c963abd6d76";

    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final ChallengeTemplateRepository challengeTemplateRepository;
    private final ResultRepository resultRepository;
    private final EndpointRepository endpointRepository;
    private final RunnerRepository runnerRepository;
    private final LanguageRepository languageRepository;
    private final TestRepository testRepository;
    private final PlatformStorage storage;
    private final OrganizationRepository organizationRepository;
    private final TaskRepository taskRepository;

    @Value("${coduno.storage.gcs.buckets.tests}")
    private String testsBucket;

    @Value("${coduno.storage.gcs.buckets.instructions}")
    private String instructionsBucket;

    @Autowired
    public CodingcontestSyncService(UserRepository userRepository,
                                    ChallengeRepository challengeRepository,
                                    ChallengeTemplateRepository challengeTemplateRepository,
                                    ResultRepository resultRepository,
                                    EndpointRepository endpointRepository,
                                    RunnerRepository runnerRepository,
                                    LanguageRepository languageRepository,
                                    PlatformStorage storage,
                                    TestRepository testRepository,
                                    OrganizationRepository organizationRepository,
                                    TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
        this.challengeTemplateRepository = challengeTemplateRepository;
        this.resultRepository = resultRepository;
        this.endpointRepository = endpointRepository;
        this.runnerRepository = runnerRepository;
        this.languageRepository = languageRepository;
        this.storage = storage;
        this.testRepository = testRepository;
        this.organizationRepository = organizationRepository;
        this.taskRepository = taskRepository;
    }

    public User updateUserFromCodingcontest(ParticipationDto dto) {
        User user = userRepository.findOne(UUID.fromString(dto.getUuid()));
        if (user == null) {
            user = createUserFromDto(dto);
        } else {
            user.setPassword(dto.getPassword());
        }
        return userRepository.save(user);
    }

    private Duration parseGameDuration(String duration){
        LocalTime time = LocalTime.parse(duration);
        int seconds = time.toSecondOfDay();
        return Duration.ofSeconds(seconds);
    }

    private void createChallengeTemplate(CodingContestGameDto dto, UUID organizationId, Map<String, ByteArrayOutputStream> files) throws IOException {
        ChallengeTemplate challengeTemplate = challengeTemplateRepository.findOneByCanonicalName(dto.getCanonicalName());
        if (challengeTemplate != null){
            return;
        }
        Organization organization = organizationRepository.findOne(organizationId);
        if (organization == null){
            throw new IllegalArgumentException("organization.invalid");
        }

        if (dto.getPuzzles()
                .stream()
                .findAny()
                .filter(puzzleDto -> puzzleDto.getValidationClass() != null)
                .isPresent()){
            throw new IllegalArgumentException("ccc.game.structure.unsuported");
        }

        Endpoint taskEndpoint = endpointRepository.findOneByComponent("ccc-drone-task");
        Endpoint challengeEndpoint = endpointRepository.findOneByComponent("ccc-challenge");
        Runner runner = runnerRepository.findOneByPath("/diff");
        Set<Language> languages = new HashSet<>(languageRepository.findAll());
        Duration gameDuration = parseGameDuration(dto.getTimeframe());

        challengeTemplate = new ChallengeTemplate();
        challengeTemplate.setCanonicalName(dto.getCanonicalName().replace(" ", "-"));
        challengeTemplate.setName(dto.getName());
        challengeTemplate.setDescription(dto.getDescription());
        challengeTemplate.setEndpoint(challengeEndpoint);
        challengeTemplate.setOrganization(organization);
        challengeTemplate.setDuration(gameDuration);
        challengeTemplate.setInstructions("Instructions");

        for (PuzzleDto puzzle: dto.getPuzzles()){
            Task task = new Task();
            task.setCanonicalName(challengeTemplate.getCanonicalName() + "-" + puzzle.getCanonicalName());
            task.setName(puzzle.getCanonicalName());
            task.setEndpoint(taskEndpoint);
            task.setDescription(puzzle.getCanonicalName());
            task.setInstructions(storage.uploadPublic(instructionsBucket,
                    instructionsFileName(challengeTemplate, puzzle.getInstructionsFile()),
                    new ByteArrayInputStream(files.get(puzzle.getInstructionsFile()).toByteArray()),
                    "application/pdf"));
            task.setDuration(gameDuration);
            task.setRunner(runner);
            task.setLanguages(languages);
            task.setOrganization(organization);
            Map<String, ByteArrayOutputStream> testFiles = null;
            if (puzzle.getInputFilePath() != null){
                testFiles = unzip(files.get(puzzle.getInputFilePath()).toByteArray());
            }
            for(PuzzleTestDto puzzleTest: puzzle.getTests()){
                Test test = new Test();
                test.setIndex(Integer.parseInt(puzzleTest.getIndex()));
                test.setRunner(runner);
                test = testRepository.save(test);
                if (testFiles == null){
                    storage.upload(testsBucket, test.getId() + "/input", new ByteArrayInputStream(puzzleTest.getData().getBytes()), "text/plain");
                }else{
                    storage.upload(testsBucket, test.getId() + "/input", new ByteArrayInputStream(testFiles.get(puzzleTest.getData()).toByteArray()), "text/plain");
                }
                storage.upload(testsBucket, test.getId() + "/output", new ByteArrayInputStream(puzzleTest.getSolution().getBytes()), "text/plain");
                Map<String, String> testParams = new HashMap<>();
                testParams.put("test", testsBucket + "/" + test.getId() + "/output");
                testParams.put("files_gcs", testsBucket + "/" + test.getId() + "/input");
                test.setParams(testParams);
                test = testRepository.save(test);
                test.setRunner(runner);
                task.addTest(test);
            }
            task = taskRepository.save(task);
            challengeTemplate.addTask(task);
        }
        challengeTemplateRepository.save(challengeTemplate);
    }

    private String instructionsFileName(ChallengeTemplate challengeTemplate, String fileName){
        return challengeTemplate.getCanonicalName() + "/" + UUID.randomUUID().toString() + "/" + fileName;
    }

    private Map<String, ByteArrayOutputStream> unzip(byte[] zipbytes) throws IOException {
        final int bufSize = 2048;
        Map<String, ByteArrayOutputStream> map = new HashMap<>();
        ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(zipbytes));
        ZipEntry entry;
        byte[] buf = new byte[bufSize];
        while ((entry = zip.getNextEntry()) != null){
            String fileName = entry.getName().substring(entry.getName().indexOf("/") + 1);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int count;
            while ((count = zip.read(buf, 0, bufSize)) != -1){
                baos.write(buf, 0, count);
            }
            map.put(fileName, baos);
        }
        return map;
    }



    public void createChallengeTemplateFromGameResources(MultipartFile gameZip, UUID organizationId) throws IOException {
        organizationId = organizationRepository.findAll().get(0).getId();
        Map<String, ByteArrayOutputStream> files = unzip(gameZip.getBytes());
        CodingContestGameDto game = null;
        for (String fileName: files.keySet()){
            if (fileName.endsWith(".xml")){
                byte[] byts = files.get(fileName).toByteArray();
                ObjectMapper mapper = new XmlMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                game = mapper.readValue(new ByteArrayInputStream(byts), CodingContestGameDto.class);
            }
        }
        if (game == null){
            throw new IllegalArgumentException("ccc.game.zip.invalid");
        }
        createChallengeTemplate(game, organizationId, files);
    }

    public void createOrUpdateContest(CodingcontestDto dto) {
        UUID id = UUID.fromString(dto.getUuid());
        Challenge challenge = challengeRepository.findOne(id);
        ChallengeTemplate template = challengeTemplateRepository.findOneByName(dto.getGameName());
        if (template == null) {
            switch (dto.getGameName()) {
                case DRONES_UUID:
                    template = challengeTemplateRepository.findOneByName("Drones");
                    break;
                case DRONES_2D_UUID:
                    template = challengeTemplateRepository.findOneByName("Drones 2D");
                    break;
            }
        }
        if (template == null) {
            throw new IllegalArgumentException("sync.game.invalid");
        }
        if (challenge == null) {
            challenge = new Challenge();
            challenge.setId(id);
            challenge.setName(dto.getName());
            challenge.setCanonicalName("ccc-" + dto.getLocation() + "-" + Year.now().toString());
            challenge.setChallengeTemplate(template);
            ZonedDateTime startDate = ZonedDateTime.ofInstant(dto.getStartTime().toInstant(), ZoneId.of("UTC"));
            challenge.setStartDate(startDate);
            if (startDate.plus(template.getDuration()).isAfter(ZonedDateTime.now(ZoneId.of("UTC")))) {
                challenge.setEndDate(startDate.plus(template.getDuration()));
            }
            challenge = challengeRepository.save(challenge);
        }

        for (ParticipationDto participation : dto.getParticipations()) {
            if (!participation.isDisabled()) {
                User user = userRepository.findOne(UUID.fromString(dto.getUuid()));
                if (user == null) {
                    user = createUserFromDto(participation);
                    user.addInvitedChallenge(challenge);
                } else {
                    user.setPassword(participation.getPassword());
                    if (!user.getInvitedChallenges().contains(challenge)) {
                        user.addInvitedChallenge(challenge);
                    }
                }
                user.setEnabled(true);
                userRepository.save(user);
            }
        }
    }

    public ContestInfoDto getResults(UUID id) {
        Challenge challenge = challengeRepository.findOneWithTemplate(id);
        if (challenge == null) {
            return null;
        }
        ContestInfoDto contestInfoDto = new ContestInfoDto();
        contestInfoDto.setUuid(id);
        contestInfoDto.setDurationHours(4);
        contestInfoDto.setDurationMinutes(4 * 60);
        contestInfoDto.setFailedTestPenalty(0);
        contestInfoDto.setUploadedCodePerLevelBonus(0);
        contestInfoDto.setGameName(challenge.getChallengeTemplate().getName());
        contestInfoDto.setName(challenge.getName());
        List<Object[]> results = resultRepository.findLeaderboardForChallenge(challenge.getId());
        List<ContestantDto> contestants = new ArrayList<>();
        int rank = 0;
        for (Object[] resultRow : results) {
            Result result = (Result) resultRow[0];
            int finishedTasks = ((Long) resultRow[1]).intValue();

            ContestantDto contestant = new ContestantDto();
            contestant.setEmail(result.getUser().getEmail());
            contestant.setUuid(result.getUser().getId());
            contestant.setCodingLanguage(findLanguage(result));
            contestant.setFinished(result.getFinished() != null);
            contestant.setLevelsCompleted(finishedTasks);
            contestant.setFailedTests(countFailedTests(result));
            contestant.setRank(++rank);
            contestant.setResults(getContestResults(result));
            contestants.add(contestant);
        }
        contestInfoDto.setParticipations(contestants);
        return contestInfoDto;
    }

    private List<ContestResultDto> getContestResults(Result result) {
        List<Task> tasks = result.getChallenge().getChallengeTemplate().getTasks();
        List<ContestResultDto> contestResults = new ArrayList<>();

        for (TaskResult taskResult : result.getTaskResults()) {
            int failedTests = countFailedTests(taskResult);
            ContestResultDto contestResultDto = new ContestResultDto();
            contestResultDto.setFailedTests(failedTests);
            contestResultDto.setCodeUploaded(taskResult.getSubmissions() != null && !taskResult.getSubmissions().isEmpty());
            Long finishTime = null;
            if (taskResult.getEndTime() != null) {
                finishTime = ChronoUnit.MILLIS.between(taskResult.getStartTime(), taskResult.getEndTime());
            }
            contestResultDto.setFinishTime(finishTime);
            contestResultDto.setLevel(tasks.indexOf(taskResult.getKey().getTask()) + 1);
            contestResults.add(contestResultDto);
        }
        return contestResults;
    }

    private int countFailedTests(TaskResult taskResult) {
        int failed = 0;
        for (Submission submission : taskResult.getSubmissions()) {
            for (TestResult testResult : submission.getTestResults()) {
                if (!testResult.isSuccessful()) {
                    failed++;
                }
            }
        }
        return failed;
    }

    private int countFailedTests(Result result) {
        if (result.getTaskResults() == null || result.getTaskResults().isEmpty()) {
            return 0;
        }
        int failed = 0;
        for (TaskResult taskResult : result.getTaskResults()) {
            failed += countFailedTests(taskResult);
        }
        return failed;
    }

    private String findLanguage(Result result) {
        if (result.getTaskResults() == null || result.getTaskResults().isEmpty()) {
            return null;
        }
        for (TaskResult taskResult : result.getTaskResults()) {
            for (Submission submission : taskResult.getSubmissions()) {
                if (submission.getLanguage() != null) {
                    return submission.getLanguage().getName();
                }
            }
        }
        return null;
    }

    private User createUserFromDto(ParticipationDto dto) {
        User user = new User();
        user.setId(UUID.fromString(dto.getUuid()));
        user.setUsername(dto.getName());
        user.setPassword(dto.getPassword());
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(dto.getUuid() + "@team.cod.uno");
        }
        return user;
    }
}
