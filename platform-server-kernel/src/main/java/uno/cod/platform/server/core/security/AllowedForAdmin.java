package uno.cod.platform.server.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(AllowedForAdmin.IS_ADMIN)
public @interface AllowedForAdmin {
    String IS_ADMIN = "isAuthenticated() and principal.isAdmin()";
}
