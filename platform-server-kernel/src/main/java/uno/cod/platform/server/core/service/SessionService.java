package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.User;

import javax.servlet.http.HttpSession;
import java.util.UUID;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class SessionService {

    @Autowired
    private HttpSession httpSession;

    public void setActiveOrganization(UUID organizationId) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        User user = (User) ctx.getAuthentication().getPrincipal();
        user.setActiveOrganization(organizationId);
        httpSession.setAttribute(SPRING_SECURITY_CONTEXT_KEY, ctx);
    }

    public UUID getActiveOrganization() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        User user = (User) ctx.getAuthentication().getPrincipal();
        return user.getActiveOrganization();
    }
}
