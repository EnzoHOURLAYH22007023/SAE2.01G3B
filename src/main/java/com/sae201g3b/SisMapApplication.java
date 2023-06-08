package com.sae201g3b;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.sae201g3b.ImportationCSV.filtrer;

public class SisMapApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        ArrayList<Seisme> data = ImportationCSV.ImportCSV();
        String idFiltre = "Date";
        String dateDepart = "1079/07/17";
        String dateArrive = "1155/01/18";
        filtrer(dateDepart,dateArrive,idFiltre);
        //FXMLLoader fxmlLoader = new FXMLLoader(SisMapApplication.class.getResource("SisMapView.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("SisMapApplication");
        //stage.setScene(scene);
        //stage.show();
    }
}
