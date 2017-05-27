package interfaces.impls;

import interfaces.ListOfSites;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by forest on 25.05.2017.
 */
public class CollectionsListOfSites implements ListOfSites {
    private List<Person> personList;

    public CollectionsListOfSites() {
        this.personList = new ArrayList<>();
    }



    public List<Person> getPersonList() {
        return personList;
    }


    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public void add(Person person) {
        personList.add(person);
    }

//    public void remove(String adress) {
//        for(Person p:personList){
//            if(p.getLogin().toLowerCase().equals(adress.toLowerCase())){
//                personList.remove(p);
//            }
//        }
//
//        personList.stream().filter(person -> person.getLogin().toLowerCase().equals(adress.toLowerCase()))
//                           .forEach(person -> personList.remove(person));
//    }

    @Override
    public void remove(Person person) {personList.remove(person);}

    @Override
    public void update(Person person) {}

}
