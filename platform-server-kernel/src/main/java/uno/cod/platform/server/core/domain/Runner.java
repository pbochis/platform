package uno.cod.platform.server.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by vbalan on 2/25/2016.
 */
@Entity
@Table(name = "runner")
public class Runner extends IdentifiableEntity{
    @Column
    private String name;

    @OneToMany(mappedBy = "runner")
    private List<Test> tests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
