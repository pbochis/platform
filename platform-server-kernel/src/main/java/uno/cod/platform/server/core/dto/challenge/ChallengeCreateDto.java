package uno.cod.platform.server.core.dto.challenge;

import java.time.ZonedDateTime;

/**
 * Created by Paul on 3/15/2016.
 */
public class ChallengeCreateDto {
    private String name;
    private String canonicalName;
    private boolean inviteOnly;
    private ZonedDateTime startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInviteOnly() {
        return inviteOnly;
    }

    public void setInviteOnly(boolean inviteOnly) {
        this.inviteOnly = inviteOnly;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }
}
