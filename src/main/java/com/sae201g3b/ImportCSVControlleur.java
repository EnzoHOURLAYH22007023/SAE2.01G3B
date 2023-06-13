package com.sae201g3b;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ImportCSVControlleur {
    @FXML
    private BorderPane borderPane;

    @FXML
    public void changerFXMLMap() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SisMapView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void changerFXMLDashboard() {
        /**
         * A VOIR
         *
         * @author      Enzo Hourlay
         */
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnParcourir(){
        FileChooser file = new FileChooser();
        file.setTitle("Ouvrir le fichier");
        file.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        File fichier = file.showOpenDialog(new Stage());
        String path = fichier.getAbsolutePath();
        System.out.println(path);
    }
}
