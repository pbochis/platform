package uno.cod.platform.server.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.Profiles;
import uno.cod.platform.server.core.domain.*;
import uno.cod.platform.server.core.repository.*;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SetupService {
    static final Logger LOGGER = LoggerFactory.getLogger(SetupService.class);
    private static final String TAG = SetupService.class.getSimpleName();
    private Environment environment;
    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private final EndpointRepository endpointRepository;
    private final TaskRepository taskRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationMemberRepository organizationMemberRepository;
    private final ChallengeRepository challengeRepository;
    private final RunnerRepository runnerRepository;
    private final TestRepository testRepository;

    @Autowired
    public SetupService(Environment environment, JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder, UserRepository userRepository, EndpointRepository endpointRepository, TaskRepository taskRepository, OrganizationRepository organizationRepository, OrganizationMemberRepository organizationMemberRepository, ChallengeRepository challengeRepository, RunnerRepository runnerRepository, TestRepository testRepository) {
        this.environment = environment;
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.endpointRepository = endpointRepository;
        this.taskRepository = taskRepository;
        this.organizationRepository = organizationRepository;
        this.organizationMemberRepository = organizationMemberRepository;
        this.challengeRepository = challengeRepository;
        this.runnerRepository = runnerRepository;
        this.testRepository = testRepository;
    }

    public void init(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setEnabled(true);
        this.userRepository.save(user);
        if(Arrays.asList(this.environment.getActiveProfiles()).contains(Profiles.DEVELOPMENT)) {
            LOGGER.info("initializing development database");
            this.initOrganizationsAndUsers();
            this.initDevelopmentDatabase();
        }
    }

    private void initDevelopmentDatabase() {
        Runner simpleRunner = new Runner();
        simpleRunner.setName("simple");
        simpleRunner = runnerRepository.save(simpleRunner);
        Runner diffRunner = new Runner();
        diffRunner.setName("diff");
        diffRunner = runnerRepository.save(diffRunner);
        Runner ioRunner = new Runner();
        ioRunner.setName("io");
        ioRunner = runnerRepository.save(ioRunner);

        Endpoint outputMatchEndpoint = new Endpoint();
        outputMatchEndpoint.setComponent("output-match-task");
        outputMatchEndpoint.setName("Output match");
        endpointRepository.save(outputMatchEndpoint);

        Endpoint javaUnitTestEndpoint = new Endpoint();
        javaUnitTestEndpoint.setComponent("javaut-task");
        javaUnitTestEndpoint.setName("Java Unit Test");
        endpointRepository.save(javaUnitTestEndpoint);

        Endpoint robotGameEndpoint = new Endpoint();
        robotGameEndpoint.setComponent("canvas-game-task");
        robotGameEndpoint.setName("Robot game");
        endpointRepository.save(robotGameEndpoint);

        Endpoint coderJUnitEndpoint = new Endpoint();
        coderJUnitEndpoint.setComponent("coder-javaut-task");
        coderJUnitEndpoint.setName("User coded JUnit tests");
        endpointRepository.save(coderJUnitEndpoint);

        Task helloWorldTask = new Task();
        helloWorldTask.setName("Hello, world!");
        helloWorldTask.setDescription("This is a welcome task to our platform. It is the easiest one so you can learn the ui and the workflow.");
        helloWorldTask.setInstructions("Create a program that outputs 'Hello, world!' in a language of your preference.");
        helloWorldTask.setEndpoint(outputMatchEndpoint);
        helloWorldTask.setDuration(Duration.ofMinutes(30));
        helloWorldTask.addSkill(CodingSkill.CODING_SPEED, 1D);
        taskRepository.save(helloWorldTask);

        Map<String, String> params = new HashMap<>();
        params.put(Test.PATH, "helloworld");
        Test helloWorldTest = new Test();
        helloWorldTest.setRunner(diffRunner);
        helloWorldTest.setTask(helloWorldTask);
        helloWorldTest.setParams(params);
        testRepository.save(helloWorldTest);

        Task fizzBuzzTask = new Task();
        fizzBuzzTask.setName("Fizz Buzz");
        fizzBuzzTask.setDescription("Fizz buzz is a group word game for children to teach them about division.\n" +
                "Players take turns to count incrementally, replacing any number divisible by three with the word 'fizz',\n" +
                "and any number divisible by five with the word 'buzz'.");
        fizzBuzzTask.setInstructions("Your job is to create the 'fizzbuzz(int n)' function.\n" +
                "The n parameter represents the max number to wich you need to generate the fizzbuzz data.\n" +
                "The output needs to be separated by '\\n'.");
        fizzBuzzTask.setEndpoint(outputMatchEndpoint);
        fizzBuzzTask.setDuration(Duration.ofMinutes(30));
        fizzBuzzTask.addSkill(CodingSkill.CODING_SPEED, 0.6);
        fizzBuzzTask.addSkill(CodingSkill.ALGORITHMICS, 0.4);
        taskRepository.save(fizzBuzzTask);

        params = new HashMap<>();
        params.put(Test.PATH, "fizzbuzz-fizzbuzz10^2");
        params.put(Test.STDIN, "fizzbuzz-fizzbuzzin10^2");
        Test fizzBuzzTest2 = new Test();
        fizzBuzzTest2.setParams(params);
        fizzBuzzTest2.setRunner(ioRunner);
        fizzBuzzTest2.setTask(fizzBuzzTask);
        testRepository.save(fizzBuzzTest2);

        params = new HashMap<>();
        params.put(Test.PATH, "fizzbuzz-fizzbuzz10^3");
        params.put(Test.STDIN, "fizzbuzz-fizzbuzzin10^3");
        Test fizzBuzzTest3 = new Test();
        fizzBuzzTest3.setParams(params);
        fizzBuzzTest3.setRunner(ioRunner);
        fizzBuzzTest3.setTask(fizzBuzzTask);
        testRepository.save(fizzBuzzTest3);
        Endpoint sequentialChallengeEndpoint = new Endpoint();
        sequentialChallengeEndpoint.setComponent("sequential-challenge");
        sequentialChallengeEndpoint.setName("Sequential challenge");
        sequentialChallengeEndpoint = endpointRepository.save(sequentialChallengeEndpoint);

        Challenge challenge = new Challenge();
        challenge.setName("Coduno test");
        challenge.setInstructions("Instructions for Coduno test");
        challenge.setDescription("Description for Coduno test");
        challenge.setOrganization(organizationRepository.findByNick("coduno"));
        challenge.setEndpoint(sequentialChallengeEndpoint);
        challenge.addTask(helloWorldTask);
        challenge.addTask(fizzBuzzTask);
        challenge.setDuration(Duration.ofMinutes(30));
        challengeRepository.save(challenge);
    }

    private void initOrganizationsAndUsers(){
        User victor = new User();
        victor.setUsername("vbalan");
        victor.setEmail("victor.balan@cod.uno");
        victor.setPassword(this.passwordEncoder.encode("password"));
        victor.setAdmin(true);
        victor.setEnabled(true);
        victor = userRepository.save(victor);

        Organization coduno = new Organization();
        coduno.setName("Coduno");
        coduno.setNick("coduno");
        coduno = organizationRepository.save(coduno);

        OrganizationMemberKey victorCodunoKey = new OrganizationMemberKey();
        victorCodunoKey.setUser(victor);
        victorCodunoKey.setOrganization(coduno);

        OrganizationMember victorCoduno = new OrganizationMember();
        victorCoduno.setKey(victorCodunoKey);
        victorCoduno.setAdmin(true);
        victorCoduno = organizationMemberRepository.save(victorCoduno);
    }
}
