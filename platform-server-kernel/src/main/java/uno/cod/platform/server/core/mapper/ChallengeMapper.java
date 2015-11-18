package uno.cod.platform.server.core.mapper;

import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.Task;
import uno.cod.platform.server.core.dto.challenge.ChallengeCreateDto;
import uno.cod.platform.server.core.dto.challenge.ChallengeShowDto;
import uno.cod.platform.server.core.dto.task.TaskShowDto;
import uno.cod.platform.server.core.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ChallengeMapper {
    public static ChallengeShowDto map(Challenge challenge){
        ChallengeShowDto dto = new ChallengeShowDto();
        dto.setName(challenge.getName());
        dto.setTasks(challenge.getTasks().stream().map(e -> new TaskShowDto(e)).collect(Collectors.toList()));
        return dto;
    }

    public static List<ChallengeShowDto> map(List<Challenge> challenges){
        return challenges.stream().map(e -> map(e)).collect(Collectors.toList());
    }
}
