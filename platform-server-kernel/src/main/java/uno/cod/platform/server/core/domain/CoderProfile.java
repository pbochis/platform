package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "coderprofile")
public class CoderProfile {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "coderProfile")
    private User user;

    @ElementCollection
    @CollectionTable(name = "coderprofile_skillmap")
    private Map<CodingSkill, Double> skillMap;

    @Column
    private Date lastUpdated;
}
