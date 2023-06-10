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

import java.util.ArrayList;
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
    private TableColumn<Seisme,Float> colonneId,colonneLatitude,colonneLongitude,colonneIntensite;
    @FXML
    private TableColumn<Seisme,String> colonneDate,colonneHeure,colonneNom,colonneRegion,colonneChoc,colonneQualite;
    private ArrayList<MapLayer> mapLayerArrayList = new ArrayList<>();
    public void initialize(){
        /* Ligne nécessaire pour empêcher de l'erreur sur la map Gluon */
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        /* Initialisation de la map sur la France */
        MapPoint francePoint = new MapPoint(46.227638, 2.213749);
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


        Database CSV = new Database();
        CSV.ImportCSV();
        List<Seisme> data = CSV.getDataFiltrer();
        ObservableList<Seisme> listeSeisme = FXCollections.observableArrayList(data);
        tableau.setItems(listeSeisme);
        reset();
        afficheSeismeCarte(data);

    }

    public void changeCenter(ActionEvent event){
        if(event.getSource()==btnCarte){
            borderPane.setCenter(france);
        } else if (event.getSource()==btnTab){
            borderPane.setCenter(tableau);
        }
    }

    public void afficheSeismeCarte(List<Seisme> data){
        List<Seisme> dataTmp = data;
        for(Seisme seisme: dataTmp){
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
    }

    public void reset(){
        for(MapLayer layer : mapLayerArrayList){
            france.removeLayer(layer);
        }
        mapLayerArrayList = new ArrayList<>();
    }

 }
