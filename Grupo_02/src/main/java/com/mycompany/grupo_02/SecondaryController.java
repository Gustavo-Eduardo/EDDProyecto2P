package com.mycompany.grupo_02;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MinimaxClass;
import model.Tablero;

public class SecondaryController implements Initializable{
    
    private Tablero tablero;
    private boolean vsCPU = true;
    private String first = "X";
    @FXML FlowPane panelJuego;
    
    public void draw(){
        panelJuego.getChildren().clear();
        for(int i=0; i<3; i++){
            VBox rows = new VBox();
            for(int j=0; j<3; j++){
                HBox row = new HBox();
                StackPane casilla = new StackPane();
                Rectangle elemento = new Rectangle(100,100);
                int posX = i;
                int posY = j;
                Text contenido = new Text(tablero.getCasillas()[i][j]);                
                elemento.setStroke(Color.BLACK);
                elemento.setFill(Color.WHITE);
                
                casilla.setOnMouseClicked(e -> {
                    if(contenido.getText().equals("")){
                        casilla.setDisable(true);
                        tablero = tablero.getMove(posX, posY);
                        draw();
                        afterMove();
                        if(vsCPU){
                            MinimaxClass miniMaxMove = new MinimaxClass(tablero);
                            tablero = miniMaxMove.minimax();
                            draw();
                            afterMove();
                        }
                        
                    }                    
                });                               
                
                
                casilla.getChildren().addAll(elemento, contenido);
                row.getChildren().add(casilla);
                rows.getChildren().add(row);
            }
            panelJuego.getChildren().add(rows);
            
        }
        
    }
    
    public void afterMove() {
        tablero.updateWinner();
        if (tablero.getWinner() != null){
            if(tablero.getWinner().equals("empate"))
                mostrarAlerta("EMPATARON");
            else
                mostrarAlerta(tablero.getWinner() + " " + "ha ganado");
            panelJuego.setDisable(true);
            
        }
    }
    
    protected void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(mensaje);
        alert.show();
    }
    
    public void setTablero(Tablero t){
        tablero = t;
        if(first.equals(tablero.getTurno()) && vsCPU){
            MinimaxClass miniMaxMove = new MinimaxClass(tablero);
            tablero = miniMaxMove.minimax();           
        }
            
        draw();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    
}