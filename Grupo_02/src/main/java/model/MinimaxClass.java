/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import heap.Heap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import tree.Tree;

/**
 *
 * @author gel-2
 */
public class MinimaxClass {

    Tablero actual;
    Comparator<Tablero> minUtilityCmp = ((t1, t2) -> {
       return t1.utility(t1.getTurno()) - t2.utility(t2.getTurno());  // La utilidad del tablero es la utilidad del jugador al que le toque su turno
    });
    Comparator<Tree<Tablero>> maxUtilityCmp = ((t1, t2) -> {       
       return childrenMinUtility(t1) - childrenMinUtility(t2);
    });

    public MinimaxClass(Tablero actual) {

        this.actual = actual;

    }

    public Tablero minimax() {
        
        Tablero jugada = null;

        Tree<Tablero> arbolDeEstados = new Tree(actual);
        arbolDeEstados.getRoot().setChildren(generarEstados(actual));
        Heap<Tree<Tablero>> maxUtilityHeap = new Heap(maxUtilityCmp, true); // max Heap 

        for (Tree<Tablero> hijo : arbolDeEstados.getRoot().getChildren()) {
            maxUtilityHeap.offer(hijo);            
        }

        return maxUtilityHeap.poll().getRoot().getContent();

    }

    public int childrenMinUtility(Tree<Tablero> tablero) {
        
        List<Tree<Tablero>> children = generarEstados(tablero.getRoot().getContent());      
        tablero.getRoot().setChildren(children);
        Heap<Tablero> minUtilityHeap = new Heap(minUtilityCmp, false); // min Heap
        
        for (Tree<Tablero> hijo : tablero.getRoot().getChildren()) {
            Tablero tableroHijo = hijo.getRoot().getContent();
            minUtilityHeap.offer(tableroHijo);            
        }
        
        Tablero minUtilityTablero = minUtilityHeap.poll();

        return minUtilityTablero.utility(minUtilityTablero.getTurno());

    }

    public List<Tree<Tablero>> generarEstados(Tablero tableroActual) {

        List<Tree<Tablero>> posiblesJugadas = new ArrayList();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boolean isEmpty = tableroActual.getCasillas()[i][j].equals("");
                if (isEmpty) {
                    Tablero jugada = tableroActual.getMove(i, j);
                    Tree<Tablero> hijo = new Tree(jugada);
                    posiblesJugadas.add(hijo);
                }
            }
        }

        return posiblesJugadas;

    }

}
