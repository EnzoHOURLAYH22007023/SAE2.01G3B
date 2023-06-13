module com.sae201g3b {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.gluonhq.maps;

    opens com.sae201g3b to javafx.fxml;
    exports com.sae201g3b;
    exports com.sae201g3b.Models;
    opens com.sae201g3b.Models to javafx.fxml;
    exports com.sae201g3b.ViewModels;
    opens com.sae201g3b.ViewModels to javafx.fxml;
}