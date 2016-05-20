package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.domain.User;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private HttpSession httpSession;

    public void setActiveOrganization(UUID organizationId) {
        SecurityContext ctx = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        User user = (User) ctx.getAuthentication().getPrincipal();
        user.setActiveOrganization(organizationId);
        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", ctx);
    }

    public UUID getActiveOrganization() {
        SecurityContext ctx = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        User user = (User) ctx.getAuthentication().getPrincipal();
        return user.getActiveOrganization();
    }
}
