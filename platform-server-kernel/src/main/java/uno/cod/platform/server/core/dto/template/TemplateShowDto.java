package uno.cod.platform.server.core.dto.template;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Template;

import java.util.UUID;

public class TemplateShowDto {
    public TemplateShowDto(Template template) {
        BeanUtils.copyProperties(template, this);
    }

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
