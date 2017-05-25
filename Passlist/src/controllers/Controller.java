package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button btn_find;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_change;
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

    public void doSome(ActionEvent e){
        System.out.println(iFind_field);
        try {
            btn_find.setText("Hello");

        }catch (Exception ex){}

    }
}
