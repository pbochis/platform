package uno.cod.platform.server.core.dto.template;

import uno.cod.platform.server.core.domain.Template;

import java.util.UUID;

public class TemplateShowDto {
    public TemplateShowDto(Template template) {
        this.id = template.getId();
        if (template.getLanguage() != null) {
            this.language = template.getLanguage().getTag();
        }
    }

    private UUID id;
    private String language;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
