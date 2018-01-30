package data;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"surname", "name", "self"})
public class User {
    @XmlAttribute
    private String id;
    private String name;
    private String surname;
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private Link self;

    public User() {
        super();
    }

    public User(String name, String surname, String id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, surname);
    }
}
