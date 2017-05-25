package person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forest on 25.05.2017.
 */
public class Person {
    private String login;
    private String password;
    private List<String> secretQ;

    public Person() {}

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Person(String login, String password, List<String> secretQ) {
        this.login = login;
        this.password = password;
        this.secretQ = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getSecretQ() {
        return secretQ;
    }

    public void setSecretQ(List<String> secretQ) {
        this.secretQ = secretQ;
    }
}
