package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private String id;
    private String name;

    public User() {
        super();
    }

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
