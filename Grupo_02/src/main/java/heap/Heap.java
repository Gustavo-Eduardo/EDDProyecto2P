/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heap;

import java.util.Comparator;

/**
 *
 * @author gel-2
 */
public class Heap<E> {

    private Comparator<E> cmp;
    private int effectiveSize;
    private int capacity;
    private E[] elements;
    private boolean isMax;
    
    public Heap(Comparator<E> cmp, boolean isMax){
        this.cmp = cmp;
        capacity = 100;
        this.isMax = isMax;
        this.elements = (E[]) new Object[capacity];
        effectiveSize = 0;
        
    }

    public void setCmp(Comparator<E> cmp) {
        this.cmp = cmp;
    }

    public boolean isMax() {
        return isMax;
    }

    public void setIsMax(boolean isMax) {
        this.isMax = isMax;
    }
    
    private int leftChildPos(int pos){
        int left = pos*2 + 1;
        return left < effectiveSize ? left:-1;
    }
    
    private int rightChildPos(int pos){
        int right = pos*2 + 2;
        return right < effectiveSize ? right:-1;
    }
    
    private int parentPos(int pos){
        if(pos == 0){
            return -1;
        } else {
            return pos%2==0 ? pos/2 - 1: (pos-1)/2;
        }              
    }
    
    private int lastRootPos(){
        int lastPos = effectiveSize - 1; 
        return lastPos%2==0 ? lastPos/2 - 1: (lastPos-1)/2;
    }
    
    public boolean isEmpty(){
        return effectiveSize == 0;
    }
    
    private boolean nodeIsLeaf(int pos){
        return leftChildPos(pos) + rightChildPos(pos) == -2;
    }
    
    private void addCapacity(){
        capacity = capacity * 2;
        E[] newElements = (E[]) new Object[capacity];
        for(int i=0; i<effectiveSize; i++)
            newElements[i] = elements[i];
        elements = newElements;
    }
    
    
    public void offer(E element){
        int pos = effectiveSize;
        int parentPos = parentPos(pos);
        if(effectiveSize == capacity)
            addCapacity();
        
        elements[effectiveSize] = element;
        effectiveSize++;
        
        boolean up = true;
        
        while(up){
            if(parentPos==-1)
                up=false;
            if(isMax){
                if(parentPos!=-1 && cmp.compare(elements[pos], elements[parentPos]) > 0){
                    intercambiar(pos, parentPos);
                    pos = parentPos;
                    parentPos = parentPos(pos);
                }
                else {
                    up=false;
                }
            } else {
                if(parentPos!=-1 && cmp.compare(elements[pos], elements[parentPos]) < 0){
                    intercambiar(pos, parentPos);
                    pos = parentPos;
                    parentPos = parentPos(pos);
                }
                else {
                    up=false;
                }
            }                   
        }
        
    }
    
    public E poll(){
        E maxValue;
        if(!isEmpty()){
            maxValue = elements[0];
            intercambiar(0, (effectiveSize-1));
            effectiveSize--;
            ajustar(0);
            return maxValue;
        }
        return null;
    }
    
    private void ajustar(int pos){
        
        int priorPos = pos;
        
        if(nodeIsLeaf(pos))
            return;
        else {
            if(isMax){
                priorPos = getPosMax(pos);                
            } else {
                priorPos = getPosMin(pos);                                
            }           
            if(priorPos != pos){
                intercambiar(pos, priorPos);
                ajustar(priorPos);
            }            
        }                   
    }
    
    private int getPosMax(int pos){  //retorna la posición del máximo entre un nodo y sus hijos
        int maxPos = pos;
        int right = rightChildPos(pos);
        int left = leftChildPos(pos);
        if(left!=-1 && cmp.compare(elements[left], elements[maxPos]) > 0)
            maxPos = left;
        if(right!=-1 && cmp.compare(elements[right], elements[maxPos]) > 0)
            maxPos = right;
        return maxPos;
    }
    
    private int getPosMin(int pos){ //retorna la posición del mínimo entre un nodo y sus hijos
        int minPos = pos;
        int right = rightChildPos(pos);
        int left = leftChildPos(pos);
        if(left!=-1 && cmp.compare(elements[left], elements[minPos]) < 0)
            minPos = left;
        if(right!=-1 && cmp.compare(elements[right], elements[minPos]) < 0)
            minPos = right;
        return minPos;
    }
    
    private void intercambiar(int pos1, int pos2){
        E e1 = elements[pos1];        
        elements[pos1] = elements[pos2];
        elements[pos2] = e1;
    }
    
    public void makeHeap(){        
        for(int i=lastRootPos(); i>=0; i--){
            ajustar(i);
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(int i=0; i<effectiveSize; i++)
            s+= elements[i] + "\n";
        return s;
    }
    
    
    
}

