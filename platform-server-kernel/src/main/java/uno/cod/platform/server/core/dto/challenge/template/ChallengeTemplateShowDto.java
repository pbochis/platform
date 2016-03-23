package uno.cod.platform.server.core.dto.challenge.template;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.ChallengeTemplate;
import uno.cod.platform.server.core.dto.assignment.AssignmentShowDto;
import uno.cod.platform.server.core.dto.challenge.ChallengeShortShowDto;

import java.util.List;
import java.util.UUID;

public class ChallengeTemplateShowDto extends AssignmentShowDto {
    private List<UUID> tasks;
    private List<ChallengeShortShowDto> challenges;

    public ChallengeTemplateShowDto(ChallengeTemplate challengeTemplate) {
        BeanUtils.copyProperties(challengeTemplate, this);
    }

    public List<ChallengeShortShowDto> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<ChallengeShortShowDto> challenges) {
        this.challenges = challenges;
    }

    public List<UUID> getTasks() {
        return tasks;
    }

    public void setTasks(List<UUID> tasks) {
        this.tasks = tasks;
    }
}
