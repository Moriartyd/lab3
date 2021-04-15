package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.utils.Exampler;
import sample.utils.Parser;
import sample.utils.Solver;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField textField;
    @FXML
    private TextField vecDimension;
    @FXML
    private TextArea textAreaW;
    @FXML
    private TextArea textAreaV;
    @FXML
    private Button clearButton;
    @FXML
    private Button calculateButton;
    @FXML
    private Button exampleButton;
    @FXML
    private CheckBox checkBox;

    private static final String FORMAT_MSG = "Заполните все поля.";
    private static final String DATA_MSG = "В полях должны находиться только числа и разделительные символы.";
    private static final String MATRIX_V = "Матрица V";
    private static final String MATRIX_W = "Матрица W";
    private static final String VECTOR = "Вектор X";
    private static final String DEFAULT = "Ошибка";

    @FXML
    public void clearFields(ActionEvent actionEvent) {
        textAreaV.clear();
        textField.clear();
        textAreaW.clear();
    }

    @FXML
    public void showExample(ActionEvent actionEvent) {
        int k = 0;
        try {
            k = Integer.parseUnsignedInt(vecDimension.getText());
            if(k == 0) throw new NumberFormatException();

        }
        catch (NumberFormatException e) {
            k = ((int) (Math.random() * 100)) % 5 +1;
        }

        textField.setText(Exampler.getVector(k, false));
        textAreaW.setText(Exampler.getMatrix(k));
        textAreaV.setText(Exampler.getMatrix(k));

        if (checkBox.isSelected()) {
            calculate(actionEvent);
        }
    }

    @FXML
    public void calculate(ActionEvent actionEvent) {
        double[] vector = null;
        double[][] v = null;
        double[][] w = null;
        if (textAreaW.getText().isEmpty() || textAreaV.getText().isEmpty() ||textField.getText().isEmpty()) {
            showAlert(DEFAULT, FORMAT_MSG);
            return;
        }
        try {
            vector = Parser.parseVector(textField.getText());
        } catch (Exception e) {
            showAlert(VECTOR, DATA_MSG);
            return;
        }
        try {
            v = Parser.parseMatrix(textAreaV.getText());
        } catch (Exception e) {
            showAlert(MATRIX_V, e.getMessage().equals(Parser.SQR_ERROR) ? Parser.SQR_ERROR : DATA_MSG);
            return;
        }
        try {
            w = Parser.parseMatrix(textAreaW.getText());
        } catch (Exception e) {
            showAlert(MATRIX_W, e.getMessage().equals(Parser.SQR_ERROR) ? Parser.SQR_ERROR : DATA_MSG);
            return;
        }
        if (w.length != v.length || w[0].length != vector.length) {
            showAlert(DEFAULT, FORMAT_MSG);
        }
        String[] result = Solver.getResult(vector, v, w);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/sample/view/result.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        ResultController resultController = fxmlLoader.getController();
        resultController.initialize(result);
        stage.showAndWait();
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

