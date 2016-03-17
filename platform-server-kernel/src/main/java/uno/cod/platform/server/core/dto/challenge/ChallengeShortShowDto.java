package uno.cod.platform.server.core.dto.challenge;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Challenge;

import java.time.ZonedDateTime;

public class ChallengeShortShowDto {
    private Long id;
    private String name;
    private String canonicalName;
    private ZonedDateTime startDate;

    public ChallengeShortShowDto(Challenge challenge){
        BeanUtils.copyProperties(challenge, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }
}
