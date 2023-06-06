module com.sae201g3b {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.sae201g3b to javafx.fxml;
    exports com.sae201g3b;

}