package person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forest on 25.05.2017.
 */
public class ListOfPer {
    private List<Person> personList;

    public ListOfPer(List<Person> personList) {
        this.personList = new ArrayList<>();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public void addNewPerson(Person person) {
        personList.add(person);
    }

    public void removePerson(String adress) {
        for(Person p:personList){
            if(p.getLogin().toLowerCase().equals(adress.toLowerCase())){
                personList.remove(p);
            }
        }

        personList.stream().filter(person -> person.getLogin().toLowerCase().equals(adress.toLowerCase()))
                           .forEach(person -> personList.remove(person));
    }
}
