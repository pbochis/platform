package uno.cod.platform.server.core.service.util;

import uno.cod.platform.server.core.domain.Runner;

import java.util.UUID;

public class RunnerTestUtil {
    public static String NAME = "default runner";
    public static Runner getRunner(){
        Runner runner = new Runner();
        runner.setId(UUID.randomUUID());
        runner.setName(NAME);
        return runner;
    }

    public static Runner getRunner(UUID id){
        Runner runner = new Runner();
        runner.setId(id);
        runner.setName(NAME);
        return runner;
    }
}
