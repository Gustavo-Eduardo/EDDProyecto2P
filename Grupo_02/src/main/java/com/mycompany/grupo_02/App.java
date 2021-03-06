package com.mycompany.grupo_02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import model.Tablero;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("PantallaInicio.fxml"));
            Parent root = fxmlLoader.load();                                 
            scene = new Scene(root,600,400);
            stage.setScene(scene);
            stage.setTitle("Tres en Raya");
            stage.show();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        launch();
    }

}