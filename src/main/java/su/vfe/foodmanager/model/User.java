package su.vfe.foodmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 5, max = 100)
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    //TODO: Think about EAGER loading
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_id")
    private Family family;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRole());
    }

    public User(Integer id, String name, String email, String password, Role role) {
        this(id, name, email, password, true, role);
    }

    public User(Integer id, String name, String email, String password, boolean enabled,Role role) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                ", family=" + family +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}