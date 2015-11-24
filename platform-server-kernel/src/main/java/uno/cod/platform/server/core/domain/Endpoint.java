package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "endpoint")
public class Endpoint extends IdentifiableEntity {
    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String component;

    @OneToMany(mappedBy = "endpoint")
    private List<Task> tasks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    protected void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        task.setEndpoint(this);
        tasks.add(task);
    }
}
