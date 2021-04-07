package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResultController {

    @FXML
    private Text net1;
    @FXML
    private Text out1;
    @FXML
    private Text net2;
    @FXML
    private Text out2;
    @FXML
    private Button backButton;

    @FXML
    void initialize(String[] data) {
        net1.setText(data[0]);
        out1.setText(data[1]);
        net2.setText(data[2]);
        out2.setText(data[3]);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}


