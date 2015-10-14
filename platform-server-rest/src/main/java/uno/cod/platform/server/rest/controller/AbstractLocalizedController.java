package uno.cod.platform.server.rest.controller;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

public abstract class AbstractLocalizedController {
    protected HttpServletRequest request;

    protected AbstractLocalizedController(HttpServletRequest request) {
        this.request = request;
    }

    protected List<Locale.LanguageRange> getLanguagePriorityList() {
        return Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
    }
}
