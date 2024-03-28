module com.blacksky.food_paradise {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.blacksky.food_paradise to javafx.fxml;
    exports com.blacksky.food_paradise;
    exports com.blacksky.food_paradise.controllers;
    opens com.blacksky.food_paradise.controllers to javafx.fxml;
    exports com.blacksky.food_paradise.models;
    opens com.blacksky.food_paradise.models to javafx.fxml;
}