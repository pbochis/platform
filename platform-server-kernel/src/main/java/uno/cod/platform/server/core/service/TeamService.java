package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Team;
import uno.cod.platform.server.core.domain.TeamMember;
import uno.cod.platform.server.core.domain.TeamUserKey;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.core.dto.team.TeamCreateDto;
import uno.cod.platform.server.core.dto.team.TeamShowDto;
import uno.cod.platform.server.core.repository.TeamMemberRepository;
import uno.cod.platform.server.core.repository.TeamRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamService {
    private final TeamRepository repository;
    private final TeamMemberRepository teamMemberRepository;
    private final SessionService sessionService;

    @Autowired
    public TeamService(TeamRepository repository, TeamMemberRepository teamMemberRepository, SessionService sessionService) {
        this.repository = repository;
        this.teamMemberRepository = teamMemberRepository;
        this.sessionService = sessionService;
    }

    public void create(TeamCreateDto dto) {
        if (repository.findOneByCanonicalName(dto.getCanonicalName()) != null) {
            throw new IllegalArgumentException("team.canonicalName.existing");
        }
        Team team = new Team();
        team.setName(dto.getName());
        team.setCanonicalName(dto.getCanonicalName());
        team = repository.save(team);

        User user = sessionService.getLoggedInUser();

        TeamUserKey key = new TeamUserKey();
        key.setTeam(team);
        key.setUser(user);
        TeamMember member = new TeamMember();
        member.setKey(key);
        member.setAdmin(true);
        teamMemberRepository.save(member);
    }

    public void join(User user, UUID teamId) {
        Team team = repository.findOne(teamId);
        if (team == null) {
            throw new IllegalArgumentException("team.invalid");
        }
        TeamUserKey key = new TeamUserKey();
        key.setTeam(team);
        key.setUser(user);
        TeamMember member = new TeamMember();
        member.setKey(key);
        teamMemberRepository.save(member);
        //TODO maybe delete invitation after join/decline?
    }

    public List<TeamShowDto> findAllTeamsForUser(UUID userId) {
        return repository.findAllByUserId(userId).stream().map(TeamShowDto::new).collect(Collectors.toList());
    }
}