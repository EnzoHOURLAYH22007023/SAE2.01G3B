/**
 * Cette classe est le controller du fichier fxml SisMapView qui est notre page principal
 *
 * @version 1.0
 *
 * @see com.sae201g3b.SisMapApplication
 * @author Enzo HOURLAY
 */
package com.sae201g3b;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.sae201g3b.Database.*;

public class DashboardController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private LineChart lineChart;
    private List<Seisme> data;
    private List<Seisme> datafiltrer;
    public void initialize(){
        Database CSV = new Database();
        ImportCSV();
        data = getData();
        datafiltrer = getDataFiltrer();

        ObservableList<XYChart.Data<String,Number>> tmp = FXCollections.observableArrayList();
        for (Seisme seisme : data) {
            Float intensite = Float.parseFloat(seisme.getIntensite());
            String antmp = seisme.getDate().substring(0,4);
            tmp.add(new XYChart.Data<>(antmp, intensite));
        }
        lineChart.setData(FXCollections.observableArrayList(new XYChart.Series<>(tmp)));
    }
    @FXML
    public void changerFXML() {
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
    private void appliquer(){

    }

}
