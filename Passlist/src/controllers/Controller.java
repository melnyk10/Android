package controllers;

import interfaces.impls.CollectionsListOfSites;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import objects.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    CollectionsListOfSites collectionsListOfSites = new CollectionsListOfSites();

    @FXML
    private Button btn_find;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_remove;
    @FXML
    private Button btn_copyLog;
    @FXML
    private Button btn_copyPass;
    @FXML
    private Button btn_copySQ;
    @FXML
    private TextField iFind_field;
    @FXML
    private TextField iLogin_field;
    @FXML
    private TextField iPass_field;
    @FXML
    private ListView<String> iListView;


    public void doSome(ActionEvent e){
        System.out.println(iFind_field);
        try {
            btn_find.setText("Hello");

        }catch (Exception ex){}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collectionsListOfSites.add(new Person("vk.com", "forest", "qwerty"));
        collectionsListOfSites.add(new Person("fb.com", "tom", "123456"));
        collectionsListOfSites.add(new Person("youtube.com", "bob", "159"));

        ObservableList<String> temp = FXCollections.observableArrayList();
        collectionsListOfSites.getPersonList().stream().forEach(person -> temp.add(person.getAddress()));
        iListView.setItems(temp);
    }
}
