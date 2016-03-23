package uno.cod.platform.server.core.dto.test;

import uno.cod.platform.server.core.domain.Test;

import java.util.UUID;

public class TestShowDto {
    private UUID id;

    public TestShowDto(Test test){
        this.id = test.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
