package uno.cod.platform.server.core.security;


import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.*;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * even if this does not need to be a spring bean, it is designed as
 * one. it is very likely that we add caching or more stuff here,
 * that needs interaction with other spring compontents, and
 * we do not want to refactor it afterwards
 */
@Service
public class SecurityService {
    public boolean isTeamMember(User user, Long teamId) {
        if (user == null || teamId == null) {
            return false;
        }

        Set<TeamMember> teams = user.getTeams();
        for (TeamMember teamMember : user.getTeams()) {
            if (teamMember.getKey().getTeam().getId().equals(teamId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTeamAdmin(User user, Long teamId) {
        if (user == null || teamId == null) {
            return false;
        }

        Set<TeamMember> teams = user.getTeams();
        for (TeamMember teamMember : user.getTeams()) {
            if (teamMember.isAdmin() && teamMember.getKey().getTeam().getId().equals(teamId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOrganizationMember(User user, Long organizationId) {
        if (user == null || organizationId == null) {
            return false;
        }

        Set<OrganizationMember> organizations = user.getOrganizations();
        for (OrganizationMember organizationMember : user.getOrganizations()) {
            if (organizationMember.getKey().getOrganization().getId().equals(organizationId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOrganizationAdmin(User user, Long organizationId) {
        if (user == null || organizationId == null) {
            return false;
        }

        Set<OrganizationMember> organizations = user.getOrganizations();
        for (OrganizationMember organizationMember : user.getOrganizations()) {
            if (organizationMember.isAdmin() && organizationMember.getKey().getOrganization().getId().equals(organizationId)) {
                return true;
            }
        }
        return false;
    }

    public boolean canAccessScheduledChallengeChallenge(User user, Long scheduledChallengeId){
        if (user == null || scheduledChallengeId == null) {
            return false;
        }

        for(ScheduledChallenge challenge: user.getInvitedChallenges()){
            if(challenge.getId().equals(scheduledChallengeId)){
                return challenge.getStartDate() == null || challenge.getStartDate().isBefore(ZonedDateTime.now());
            }
        }
        return false;
    }

    public boolean canAccessChallenge(User user, Long challengeId) {
        if (user == null || challengeId == null) {
            return false;
        }

        // TODO add organization check
        if(user.getOrganizations() != null){
            return true;
        }
        return false;
    }

    public boolean canAccessTask(User user, Long taskId) {
        if (user == null || taskId == null) {
            return false;
        }

        // TODO

        return true;
    }
}
