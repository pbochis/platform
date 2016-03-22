package uno.cod.platform.server.core.dto.language;

import org.springframework.beans.BeanUtils;
import uno.cod.platform.server.core.domain.Language;

public class LanguageShowDto {
    private Long id;
    private String name;
    private String tag;

    public LanguageShowDto(Language language) {
        BeanUtils.copyProperties(language, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
