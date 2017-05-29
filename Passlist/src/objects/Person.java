package objects;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forest on 25.05.2017.
 */
public class Person {
    private SimpleStringProperty address;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
    private List<String> secretQ;

    public Person() {}

    public Person(String address, String login, String password) {
        this.address = new SimpleStringProperty(address);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    public Person(String address, String login, String password, List<String> secretQ) {
        this.address = new SimpleStringProperty(address);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.secretQ = new ArrayList<>();
    }


    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public List<String> getSecretQ() {
        return secretQ;
    }

    public void setSecretQ(List<String> secretQ) {
        this.secretQ = secretQ;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }
}
