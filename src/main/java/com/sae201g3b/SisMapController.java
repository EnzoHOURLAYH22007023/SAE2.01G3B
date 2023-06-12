/**
 * Cette classe est le controller du fichier fxml SisMapView qui est notre page principal
 *
 * @version 1.0
 *
 * @see com.sae201g3b.SisMapApplication
 * @author Enzo HOURLAY
 */
package com.sae201g3b;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sae201g3b.Database.*;

public class SisMapController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private MapView france;
    @FXML
    private TableView tableau;
    @FXML
    private RadioMenuItem menuCarte,menuTab;
    @FXML
    private TableColumn<Seisme,String> colonneId,colonneLatitude,colonneLongitude,colonneIntensite,colonneDate,colonneHeure,colonneNom,colonneRegion,colonneChoc,colonneQualite;
    private ArrayList<MapLayer> mapLayerArrayList = new ArrayList<>();

    @FXML
    private TextField id,region,de,jusqua;
    @FXML
    private Slider intensite;
    private List<Seisme> data;
    private List<Seisme> datafiltrer;
    private Database CSV = new Database();
    private final MapPoint francePoint = new MapPoint(46.227638, 2.213749);
    private final MapPoint francePoint2 = new MapPoint(46.227600, 2.213700);
    public void initialize(){
        /* Ligne nécessaire pour empêcher de l'erreur sur la map Gluon */
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        /* Initialisation de la map sur la France */

        france.setZoom(6);
        france.flyTo(0,francePoint,0.1);
        //france.setDisable(true);

        /* Initialisation de la TableView qui affiche les données des séismes */
        colonneId.setCellValueFactory(new PropertyValueFactory<>("Identifiant"));
        colonneLatitude.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        colonneLongitude.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        colonneIntensite.setCellValueFactory(new PropertyValueFactory<>("Intensite"));
        colonneDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colonneHeure.setCellValueFactory(new PropertyValueFactory<>("Heure"));
        colonneNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colonneRegion.setCellValueFactory(new PropertyValueFactory<>("Region"));
        colonneChoc.setCellValueFactory(new PropertyValueFactory<>("Choc"));
        colonneQualite.setCellValueFactory(new PropertyValueFactory<>("Qualite"));

        ImportCSV();
        data = getData();
        datafiltrer = getDataFiltrer();
        ObservableList<Seisme> listeSeisme = FXCollections.observableArrayList(data);
        tableau.setItems(listeSeisme);

        afficheSeismeCarte();
    }

    public void changeCenter(ActionEvent event){
        if(event.getSource()==menuCarte){
            borderPane.setCenter(france);
        } else if (event.getSource()==menuTab){
            borderPane.setCenter(tableau);
        }
    }

    public void afficheSeismeCarte(){
        for(Seisme seisme: datafiltrer){
            try {
                MapLayer layer = new SeismePoint(new MapPoint(Float.parseFloat(seisme.getLatitude()),
                                                            Float.parseFloat(seisme.getLongitude())),
                                                Float.parseFloat(seisme.getIntensite()));
                mapLayerArrayList.add(layer);
                france.addLayer(layer);
            } catch (IllegalArgumentException e){
                System.out.println("Lat,Lon ou Intensité est null");
            }
        }
        france.setCenter(francePoint2);
        france.flyTo(0.1,francePoint,0.1);
    }

    public void resetPoint(){
        for(MapLayer layer : mapLayerArrayList){
            france.removeLayer(layer);
        }
        mapLayerArrayList = new ArrayList<>();
        datafiltrer = getData();
    }

    @FXML
    public void resetFiltre(){
        id.clear();
        region.clear();
        de.clear();
        jusqua.clear();
        resetPoint();
        afficheSeismeCarte();
    }

    public void appliquerChangement(){
        CSV.resetFiltre();
        datafiltrer = getDataFiltrer();
        String filtre = "";
        String filtre2 = "";
        String idFiltre = "";
        if (id.getText() != ""){
            filtre = id.getText();
            idFiltre = "Identifiant";
            filtrer(filtre,filtre2,idFiltre);
        }
        if (region.getText() != ""){
            filtre = region.getText().toUpperCase();
            idFiltre = "Region";
            filtrer(filtre,filtre2,idFiltre);
        }
        if (jusqua.getText() != ""){
            filtre2 = jusqua.getText();
        }
        if (de.getText() != ""){
            filtre = de.getText();
            idFiltre = "Date";
            filtrer(filtre,filtre2,idFiltre);
        }
        if (intensite.getValue() != 0){
            //a completer
        }
        resetPoint();
        datafiltrer = getDataFiltrer();
        System.out.println(datafiltrer);
        data = getData();

        ObservableList<Seisme> listeSeismeFiltrer = FXCollections.observableArrayList(datafiltrer);
        tableau.setItems(listeSeismeFiltrer);

        afficheSeismeCarte();
    }

    @FXML
    public void changerFXML() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 }
