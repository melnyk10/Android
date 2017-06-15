package controllers;

import interfaces.impls.CollectionsListOfSites;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import objects.Person;

import java.net.URL;
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

    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();

    private ObservableList<String> temp;

    public void doSome(ActionEvent e) {
        Object source = e.getSource();

        if (!(source instanceof Button)) return;

        Button btn = (Button) source;

        //String selectPerson = iListView.getSelectionModel().getSelectedItem();

        switch (btn.getId()) {
            case "btn_find":
//                for(Person p:collectionsListOfSites.getPersonList()){
//                    if(p.getAddress().contains(iFind_field.getText())){
//                        System.out.println(p.getLogin()+" "+p.getPassword());
//                        System.out.println(iListView.getSelectionModel());
//                        //iLogin_field.setText(p.getLogin());
//                        //iPass_field.setText(p.getPassword());
//                    }
//                }
                findAdress();
                break;
            case "btn_add":
                break;
            case "btn_update":
                break;
            case "btn_remove":
                break;
            case "btn_copyLog":
                content.putString(iLogin_field.getText());
                clipboard.setContent(content);
                break;
            case "btn_copyPass":
                content.putString(iPass_field.getText());
                clipboard.setContent(content);
                break;
            case "btn_copySQ":
                content.putString(iPass_field.getText());
                clipboard.setContent(content);
                break;
            default:
                break;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collectionsListOfSites.add(new Person("vk.com", "forest", "qwerty"));
        collectionsListOfSites.add(new Person("fb.com", "tom", "123456"));
        collectionsListOfSites.add(new Person("youtube.com", "bob", "159"));

        initListeners();
        temp = FXCollections.observableArrayList();
        collectionsListOfSites.getPersonList().stream().forEach(person -> temp.add(person.getAddress()));
        iListView.setItems(temp);


    }

    private void initListeners() {
        iListView.setOnMouseClicked(event -> {
            for (Person p : collectionsListOfSites.getPersonList()) {
                if (p.getAddress().equals(iListView.getSelectionModel().getSelectedItem())) {
                    iLogin_field.setText(p.getLogin());
                    iPass_field.setText(p.getPassword());
                }
            }

            if (event.getClickCount() == 2) {
                System.out.println("working");
            }
        });
    }

    private void findAdress() {
            //if(iListView.getItems().contains(iFind_field.getText())){
//                System.out.println(iListView.getItems());
        ObservableList<String> temp2 = FXCollections.observableArrayList();
        for(String s:iListView.getItems()){
            if(s.contains(iFind_field.getText())){
                temp2.add(s);
            }
        }
        iListView.setItems(temp2);


        if(iFind_field.getText().equals("")) {
            iListView.setItems(temp);
        }
        //}
    }
}
