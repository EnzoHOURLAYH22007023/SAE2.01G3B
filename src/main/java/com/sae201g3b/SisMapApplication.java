package com.sae201g3b;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SisMapApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        String filtre = "160001";
        String idFiltre = "Identifiant";
        String dateDepart = "1079/07/17";
        String dateArrive = "1155/01/18";
        Filtre.filtrer(dateDepart,dateArrive);
        //FXMLLoader fxmlLoader = new FXMLLoader(SisMapApplication.class.getResource("SisMapView.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("SisMapApplication");
        //stage.setScene(scene);
        //stage.show();
    }
}
