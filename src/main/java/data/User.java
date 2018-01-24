package data;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
    @XmlTransient
    private int tmp;

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
}
