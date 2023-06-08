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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class SisMapController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private MapView france;
    @FXML
    private TableView tableau;
    @FXML
    private Button btnCarte,btnTab;
    private CustomCircleMarkerLayer pointLayer;
    public void initialize(){
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        /* La fonction est appelée à chaque rafraichissement de la carte */
        MapPoint francePoint = new MapPoint(46.227638, 2.213749);
        System.out.println(francePoint.getLatitude());
        france.setZoom(6);
        france.flyTo(0,francePoint,0.1);
        france.setDisable(true);

        pointLayer = new CustomCircleMarkerLayer(francePoint);
        france.addLayer(pointLayer);
    }

    public void changeCenter(ActionEvent event){
        if(event.getSource()==btnCarte){
            borderPane.setCenter(france);
        } else if (event.getSource()==btnTab){
            borderPane.setCenter(tableau);
        }
    }

 }
