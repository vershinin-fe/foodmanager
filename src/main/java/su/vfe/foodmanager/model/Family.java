package su.vfe.foodmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "families")
@Access(value = AccessType.FIELD)
public class Family extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family")
    @OrderBy("name ASC")
    protected List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family")
    @OrderBy("createDate ASC")
    protected List<Item> items;

    public Family() {
    }

    public Family(Integer id, String name) {
        super(id, name);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Family{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}