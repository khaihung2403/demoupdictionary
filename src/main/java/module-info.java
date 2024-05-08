
/*module Controllor {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens Controllor to javafx.fxml;
    exports Controllor;
}*/
module Controllor {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires freetts;
    requires java.desktop;

    // Xuất gói org.example.dictionaryapp
    exports org.example.dictionaryapp;

    // Mở gói Controllor cho javafx.fxml
    opens Controllor to javafx.fxml;
}