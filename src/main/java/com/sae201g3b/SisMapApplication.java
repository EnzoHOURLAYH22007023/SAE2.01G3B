package com.sae201g3b;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SisMapApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String filtre = "\"PYRENEES OCCIDENTALES\"";
        Filtre.filtrer(filtre);
        //FXMLLoader fxmlLoader = new FXMLLoader(SisMapApplication.class.getResource("SisMapView.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("SisMapApplication");
        //stage.setScene(scene);
        //stage.show();
    }
}
