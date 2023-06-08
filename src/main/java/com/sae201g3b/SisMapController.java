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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class SisMapController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private MapView france;
    @FXML
    private TableView tableau;
    @FXML
    private Button btnCarte,btnTab;
    @FXML
    private TableColumn<Seisme,Float> Identifiant,X,Y,Latitude,Longitude,Intensite;
    @FXML
    private TableColumn<Seisme,String> Date,Heure,Nom,Region,Choc,Qualite;
    public void initialize(){
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        MapPoint francePoint = new MapPoint(46.227638, 2.213749);
        france.setZoom(6);
        france.flyTo(0,francePoint,0.1);
        //france.setDisable(true);

        Identifiant.setCellValueFactory(new PropertyValueFactory<>("Identifiant"));
        X.setCellValueFactory(new PropertyValueFactory<>("X"));
        Y.setCellValueFactory(new PropertyValueFactory<>("Y"));
        Latitude.setCellValueFactory(new PropertyValueFactory<>("Latitude"));
        Longitude.setCellValueFactory(new PropertyValueFactory<>("Longitude"));
        Intensite.setCellValueFactory(new PropertyValueFactory<>("Intensite"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Region.setCellValueFactory(new PropertyValueFactory<>("region"));
        Choc.setCellValueFactory(new PropertyValueFactory<>("choc"));
        Qualite.setCellValueFactory(new PropertyValueFactory<>("qualite"));

        List<Seisme> CSV = ImportationCSV.ImportCSV();
        ObservableList<Seisme> listeSeisme = FXCollections.observableArrayList(CSV);
        tableau.setItems(listeSeisme);

        for(Seisme seisme: CSV){
            MapLayer layer = new CustomCircleMarkerLayer(new MapPoint(seisme.getLatitude(),seisme.getLongitude()));
            france.addLayer(layer);
        }

        /*String filtre = "\"PYRENEES OCCIDENTALES\"";
        listeSeisme = FXCollections.observableArrayList(Filtre.filtrer(filtre));
        tableau.setItems(listeSeisme);*/
    }

    public void changeCenter(ActionEvent event){
        if(event.getSource()==btnCarte){
            borderPane.setCenter(france);
        } else if (event.getSource()==btnTab){
            borderPane.setCenter(tableau);
        }
    }

 }
