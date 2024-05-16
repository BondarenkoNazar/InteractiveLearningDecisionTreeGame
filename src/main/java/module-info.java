module webpagesbuilder.finaltask {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens finaltask to javafx.fxml;
    exports finaltask;
    exports finaltask.UI;
    opens finaltask.UI to javafx.fxml;
}