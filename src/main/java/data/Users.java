package data;

import es.uji.belfern.generador.GeneradorDatosINE;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Users {
    public static final int MAX_USERS = 100;
    private static final GeneradorDatosINE GENERADOR = new GeneradorDatosINE();

    private final Map<String, User> users = new TreeMap<>();

    public Users() {
        String nif;
        for (long i = 0; i < MAX_USERS; i++) {
            nif = GENERADOR.getNIF();
            users.put(nif, new User(GENERADOR.getNombre(), GENERADOR.getApellido(), nif));
        }
    }

    private Users(List<User> datos) {
        for(User user: datos)
            users.put(user.getId(), user);
    }

    @XmlElement(name = "user")
    public Collection<User> getUsers() {
        return users.values();
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public Users getUsers(int page, int pageSize) {
        int first = (page - 1) * pageSize > 0 ? (page - 1) * pageSize : 0;
        int last = page * pageSize < MAX_USERS ? page * pageSize : MAX_USERS;

        User[] tmp = new User[users.values().size()];
        users.values().toArray(tmp);
        List<User> pagina = new ArrayList<>();
        Collections.addAll(pagina, Arrays.copyOfRange(tmp, first, last));

        return new Users(pagina);
    }
}
