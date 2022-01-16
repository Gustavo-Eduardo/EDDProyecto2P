/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo_02;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.Tablero;

/**
 * FXML Controller class
 *
 * @author oscar
 */
public class PantallaInicioController implements Initializable {
    
    @FXML
    private ToggleGroup tgSimbolo;
    @FXML
    private ToggleGroup tgTurno;
    @FXML
    private RadioButton rbX;
    @FXML
    private RadioButton rbO;
    @FXML
    private RadioButton rbSi;
    @FXML
    private RadioButton rbNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarJuego(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
            Parent root = fxmlLoader.load();
            SecondaryController sc = fxmlLoader.getController();
            
            if(rbSi.isSelected()){
                sc.setTablero(new Tablero("X"));
            } else {
                sc.setTablero(new Tablero("O"));
            }
            
            App.setRoot(root);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
