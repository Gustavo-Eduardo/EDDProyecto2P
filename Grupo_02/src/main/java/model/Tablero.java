
package model;

public class Tablero {
            
    String[][] casillas;
    String turno;
    String winner = null;
    
    
    public Tablero(String primero) {
        
        casillas = new String[3][3];
        turno = primero;
                
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                casillas[i][j] = "";
                                                        
    }
    
    public Tablero getMove(int posX, int posY){
                        
        Tablero copiaTablero = new Tablero(getOtherPlayer(turno));
        copiaTablero.clonarCasillas(this);
        copiaTablero.getCasillas()[posX][posY] = turno;                
        
        return copiaTablero;
        
    }
    
    
    public void updateWinner(){
        
        // filas        
        for(int i=0; i<3; i++){
            boolean fileHaveWinner = casillas[i][0].equals(casillas[i][1]) && casillas[i][0].equals(casillas[i][2]) && !casillas[i][0].equals("");
            if(fileHaveWinner)                
                winner = casillas[i][0];
        }
        // columnas        
        for(int j=0; j<3; j++){
            boolean columnHaveWinner = casillas[0][j].equals(casillas[1][j]) && casillas[0][j].equals(casillas[2][j]) && !casillas[0][j].equals("");
            if(columnHaveWinner)
                winner = casillas[0][j];
        }
        // diagonales
        boolean diagonal1HaveWinner = casillas[0][0].equals(casillas[1][1]) && casillas[1][1].equals(casillas[2][2]) && !casillas[0][0].equals("");
        boolean diagonal2HaveWinner = casillas[2][0].equals(casillas[1][1]) && casillas[1][1].equals(casillas[0][2]) && !casillas[2][0].equals("");
        if(diagonal1HaveWinner)
            winner = casillas[0][0];
        if(diagonal2HaveWinner)
            winner = casillas[0][2];
        
        //draw
        if(winner == null && isFull())
            winner = "empate";
                        
    }
    
    public int P(String player){
        
        int posiblesJugadas = 0;
        String otherPlayer = getOtherPlayer(player);

        // filas        
        for(int i=0; i<3; i++){            
            boolean esFilaDisponible = !casillas[i][0].equals(otherPlayer) && !casillas[i][1].equals(otherPlayer) && !casillas[i][2].equals(otherPlayer);
            if(esFilaDisponible)
                posiblesJugadas++;            
        }
        //columnas
        for(int i=0; i<3; i++){ 
            boolean esColumnaDisponible = !casillas[0][i].equals(otherPlayer) && !casillas[1][i].equals(otherPlayer) && !casillas[2][i].equals(otherPlayer);
            if(esColumnaDisponible)
                posiblesJugadas++;            
        }
        //diagonales
        boolean esDiagonal1Disponible = !casillas[0][0].equals(otherPlayer) && !casillas[1][1].equals(otherPlayer) && !casillas[2][2].equals(otherPlayer);
        boolean esDiagonal2Disponible = !casillas[2][0].equals(otherPlayer) && !casillas[1][1].equals(otherPlayer) && !casillas[0][2].equals(otherPlayer);
        if(esDiagonal1Disponible)
            posiblesJugadas++;
        if(esDiagonal2Disponible)
            posiblesJugadas++;
        
        return posiblesJugadas;
                                    
    }
    
    public int utility(String player){
        
        return P(player) - P(getOtherPlayer(player));
        
    }
    
    public String getOtherPlayer(String player){
        
        String otherPlayer = "";
                
        if(player.equals("X"))
            otherPlayer = "O";
        if(player.equals("O"))
            otherPlayer = "X";
        
        return otherPlayer;
        
    }
    
    public boolean isFull(){
        int contador = 0;
        boolean full = false;
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if(!casillas[i][j].equals("")){
                    contador++;
                }                
            }
        }
        if(contador == 9){
            full = true;
        }
                
        return full;
        
    }
    
    public void clonarCasillas(Tablero otro){
        
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                getCasillas()[i][j] = otro.getCasillas()[i][j];
        
    }

    public String[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(String[][] casillas) {
        this.casillas = casillas;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public String getWinner() {
        return winner;
    }
    
            
}
