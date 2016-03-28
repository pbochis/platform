package uno.cod.platform.server.core.service.util;

import uno.cod.platform.server.core.domain.Language;

import java.util.UUID;

public class LanguageTestUtil {
    public static Language getLanguage(){
        Language language = new Language();
        language.setId(UUID.randomUUID());
        language.setName("Java");
        language.setTag("java");
        return language;
    }
}
