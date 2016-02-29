package uno.cod.platform.server.core.dto.test;

import uno.cod.platform.server.core.domain.Test;

/**
 * Created by vbalan on 2/25/2016.
 */
public class TestShowDto {
    private Long id;

    public TestShowDto(Test test){
        this.id = test.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
