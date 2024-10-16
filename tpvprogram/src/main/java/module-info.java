module dambat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;    // para postgres
    
    opens dambat to javafx.fxml;
    exports dambat;
}
