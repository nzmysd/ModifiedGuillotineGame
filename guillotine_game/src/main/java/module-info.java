module com.game {
    requires javafx.base;
    requires javafx.controls;
    
    opens com.game to javafx.graphics;
}
