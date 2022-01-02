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
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
            Parent root = fxmlLoader.load();
            SecondaryController sc = fxmlLoader.getController();
            sc.setTablero(new Tablero("X"));                                    
            scene = new Scene(root,600,600);
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