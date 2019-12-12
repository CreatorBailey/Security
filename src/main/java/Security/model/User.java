package Security.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_boo")
public class User {
    @Id
    @Column(name = "user_id")
    private Long user_id;
    private String first_name;
    private String last_name;
    @Column(unique = true)
    private String email;
    private String password;

//    one-to-many mapping means that one row in a table is mapped to multiple rows in another table.
    @OneToMany(targetEntity = Address.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private Set<Address> addresses;

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set addresses) {
        this.addresses = addresses;
    }
}
