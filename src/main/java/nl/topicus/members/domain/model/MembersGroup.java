package nl.topicus.members.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thijs Reeringh on 5/10/2016.
 */
@Entity
public class MembersGroup implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date founded;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Member> members;

    public MembersGroup() {
        members = new HashSet<Member>();
    }

    public MembersGroup(String name, Date founded)
    {
        this();

        this.name = name;
        this.founded = founded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }
}
