package su.vfe.foodmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "families")
@Access(value = AccessType.FIELD)
public class Family extends AbstractNamedEntity {

    //TODO: Think about EAGER loading
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "family")
    @OrderBy("name ASC")
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family")
    @OrderBy("createDate ASC")
    private List<Item> items;

    public Family() {
    }

    public Family(Integer id, String name) {
        super(id, name);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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