/**
 * Cette classe est le controller du fichier fxml SisMapView qui est notre page principal
 *
 * @version 1.0
 *
 * @see com.sae201g3b.SisMapApplication
 * @author Enzo HOURLAY
 */
package com.sae201g3b.ViewModels;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import com.sae201g3b.Models.Database;
import com.sae201g3b.Seisme;
import com.sae201g3b.SeismePoint;
import com.sae201g3b.SisApplicationModel;
import javafx.beans.binding.ListBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;

import java.io.IOException;
import java.util.ArrayList;

public class DashboardController extends SisApplicationModel {
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField id,region,de,jusqua;
    private ArrayList<MapLayer> mapLayerArrayList = new ArrayList<>();
    @FXML
    private RangeSlider intensite;
    @FXML
    private MapView regionCarte;
    @FXML
    private LineChart lineChart;
    @FXML
    private Label nbTuple;
    private static ListBinding<XYChart.Series<String,Number>> chart;
    public void initialize(){
        /* Ligne nécessaire pour empêcher de l'erreur sur la map Gluon */
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        regionCarte.setZoom(3);
        regionCarte.flyTo(0,new MapPoint(46.227638, 2.213749),0.1);

        Database CSV = super.getCSV();

        afficheSeismeCarte();

        nbTuple.textProperty().bind(CSV.dataProperty().sizeProperty().asString());

        chart = new ListBinding<>() {
            {
                super.bind(CSV.dataProperty());
            }
            @Override
            protected ObservableList computeValue() {
                ObservableList<XYChart.Data<String,Number>> tmp = FXCollections.observableList(new ArrayList<>());
                for (Seisme seisme : CSV.getData()) {
                    Float intensite = Float.parseFloat(seisme.getIntensite());
                    String antmp = seisme.getDate().substring(0,4);
                    tmp.add(new XYChart.Data<>(antmp, intensite));
                }
                return FXCollections.observableArrayList(new XYChart.Series<>(tmp));
            }
        };
        lineChart.dataProperty().bind(chart);
    }
    public void afficheSeismeCarte(){
        for(Seisme seisme: super.getCSV().getData()){
            try {
                MapLayer layer = new SeismePoint(new MapPoint(Float.parseFloat(seisme.getLatitude()),
                        Float.parseFloat(seisme.getLongitude())),
                        Float.parseFloat(seisme.getIntensite()));
                mapLayerArrayList.add(layer);
                regionCarte.addLayer(layer);
            } catch (IllegalArgumentException e){
                System.out.println("Lat,Lon ou Intensité est null");
            }
        }
        //regionCarte.setCenter(francePoint2);
        //regionCarte.flyTo(0.1,francePoint,0.1);
    }

    public void resetPoint(){
        for(MapLayer layer : mapLayerArrayList){
            regionCarte.removeLayer(layer);
        }
        mapLayerArrayList = new ArrayList<>();
    }

    @FXML
    public void resetFiltreControlleur(){
        super.resetFiltreControlleur();
        resetPoint();
        afficheSeismeCarte();
    }

    @FXML
    public void appliquerChangement(){
        super.setId(id);
        super.setRegion(region);
        super.setDe(de);
        super.setJusqua(jusqua);
        super.setIntensite(intensite);
        super.appliquerChangement();
        resetPoint();

        afficheSeismeCarte();
    }

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
    public void changerFXMLImport() {
        /**
         * A VOIR
         *
         * @author      Alexandre Crespin
         */
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportCSV.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
