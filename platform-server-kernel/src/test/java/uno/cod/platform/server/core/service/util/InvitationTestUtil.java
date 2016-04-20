package uno.cod.platform.server.core.service.util;

import uno.cod.platform.server.core.domain.Invitation;

import java.time.ZonedDateTime;

public class InvitationTestUtil {

    public static Invitation getInvitation() {
        Invitation invitation = new Invitation();

        invitation.setToken("token");
        invitation.setExpire(ZonedDateTime.now());
        invitation.setEmail("email");
        invitation.setChallenge(ChallengeTestUtil.getChallenge());

        return invitation;
    }
}
