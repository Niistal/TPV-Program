

module dambat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires javafx.graphics;

    opens dambat to javafx.fxml;
    opens dambat.controllers.kategories to javafx.fxml;
    opens dambat.controllers.products to javafx.fxml;
    exports dambat;
    exports dambat.controllers.kategories;
    exports dambat.models;
    exports dambat.controllers.products;
}
