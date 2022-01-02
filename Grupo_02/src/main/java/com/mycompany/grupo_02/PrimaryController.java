package com.mycompany.grupo_02;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button primaryButton;
    @FXML
    private void switchToSecondary() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PantallaFin.fxml"));
        Parent root = fxmlLoader.load();
        App.setRoot(root);
    }
    
}
