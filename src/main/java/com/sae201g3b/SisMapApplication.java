/*
 * Nom de classe : SisMapController
 *
 * Description   : Controller du menu principal SisMapView.fxml
 *
 * Version       : 1.0
 *
 * Date          : 08/06/2023
 *
 * Author        : Alexandre Crespin,Enzo HOURLAY, Sabri KHADRAOUI, Yazid-Raoul Maoulida Attoumani
 */
package com.sae201g3b;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SisMapApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
		String filtre = "\"PYRENEES OCCIDENTALES\"";
        Filtre.filtrer(filtre);
        FXMLLoader fxmlLoader = new FXMLLoader(SisMapApplication.class.getResource("SisMapView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("SisMapApplication");
        stage.setScene(scene);
        stage.show();
    }
}
