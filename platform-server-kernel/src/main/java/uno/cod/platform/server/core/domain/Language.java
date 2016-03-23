package uno.cod.platform.server.core.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "language")
public class Language extends IdentifiableEntity {
    private String name;
    private String tag;

    @ManyToMany(mappedBy = "languages")
    private Set<Task> tasks;

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

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}
