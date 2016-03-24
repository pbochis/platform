package uno.cod.platform.server.core.security;


import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.Challenge;
import uno.cod.platform.server.core.domain.OrganizationMember;
import uno.cod.platform.server.core.domain.TeamMember;
import uno.cod.platform.server.core.domain.User;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * even if this does not need to be a spring bean, it is designed as
 * one. it is very likely that we add caching or more stuff here,
 * that needs interaction with other spring compontents, and
 * we do not want to refactor it afterwards
 */
@Service
public class SecurityService {
    public boolean isTeamMember(User user, UUID teamId) {
        if (user == null || teamId == null) {
            return false;
        }

        for (TeamMember teamMember : user.getTeams()) {
            if (teamMember.getKey().getTeam().getId().equals(teamId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTeamAdmin(User user, UUID teamId) {
        if (user == null || teamId == null) {
            return false;
        }

        for (TeamMember teamMember : user.getTeams()) {
            if (teamMember.isAdmin() && teamMember.getKey().getTeam().getId().equals(teamId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOrganizationMember(User user, UUID organizationId) {
        if (user == null || organizationId == null) {
            return false;
        }

        for (OrganizationMember organizationMember : user.getOrganizations()) {
            if (organizationMember.getKey().getOrganization().getId().equals(organizationId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOrganizationAdmin(User user, UUID organizationId) {
        if (user == null || organizationId == null) {
            return false;
        }

        for (OrganizationMember organizationMember : user.getOrganizations()) {
            if (organizationMember.isAdmin() && organizationMember.getKey().getOrganization().getId().equals(organizationId)) {
                return true;
            }
        }
        return false;
    }

    public boolean canAccessScheduledChallengeChallenge(User user, UUID scheduledChallengeId){
        if (user == null || scheduledChallengeId == null) {
            return false;
        }

        for(Challenge challenge: user.getInvitedChallenges()){
            if(challenge.getId().equals(scheduledChallengeId)){
                return challenge.getStartDate() == null || challenge.getStartDate().isBefore(ZonedDateTime.now());
            }
        }
        return false;
    }

    public boolean canAccessChallenge(User user, UUID challengeId) {
        if (user == null || challengeId == null) {
            return false;
        }

        // TODO add organization check
        if(user.getOrganizations() != null){
            return true;
        }
        for(Challenge challenge: user.getInvitedChallenges()){
            if(challenge.getId().equals(challengeId)){
                return true;
            }
        }
        return false;
    }

    public boolean canAccessTask(User user, UUID taskId) {
        if (user == null || taskId == null) {
            return false;
        }

        // TODO

        return true;
    }
}
