package com.sae201g3b;

import com.gluonhq.maps.MapLayer;
import javafx.fxml.FXML;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

public class SisMapController {
    @FXML
    private MapView france;
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
 }
