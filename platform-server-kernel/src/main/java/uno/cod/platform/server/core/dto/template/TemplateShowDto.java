package uno.cod.platform.server.core.dto.template;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Template;

public class TemplateShowDto {
    public TemplateShowDto(Template template) {
        BeanUtils.copyProperties(template, this);
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
