/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

/**
 * Clase para almacenar datos de las palabras
 * @author Keons
 */
public class Word {
    private String word;
    private Lista index = new Lista();
    private String file;
    private int repetition = 0;
    
    public Word(String word, int index, String file){
        this.word = word;
        this.index.insertAtLast(index);
        this.file = file;
        repetition++;
    }
    
    /**
     * Metodo que agrega al array de indices un nuevo indice donde se encuentra la plabra
     * @param index indice donde se enecuentra la palabra
     */
    public void addRepetition(int index){
        this.index.insertAtLast(index);
        repetition++;
    }
    
    /**
     * Funcion que verifica que alguno de los indices se encuentre al lado izquierdo (sea menor por 1), al indice de otro palabra
     * @param index indice de la palabra que se quiere saber si esta a la derecha
     * @return el indice de la palabra que esta a la izquierda
     * @return -1, si la plabra no esta a la izquierda
     */
    public int nextTo(int index){
        Nodo current = this.index.getHead();
        while(current != null){
            int actualIndex = (int)current.getData();
            if(actualIndex - index == -1){
                return actualIndex;
            }
        }
        return -1;
    }
    
    /**
     * Funcion para saber la palabra que se encuentra almacenada
     * @return palabra que se encuentra almacenada
     */
    public String getWord() {
        return word;
    }
    
    /**
     * Funcion para saber los indices en los cuales se encuentra la palabra
     * @return indices en los cuales se encuentra la palabra
     */
    public Lista getIndex() {
        return index;
    }    
    /**
     * Funcion para obtener la cantidad de veces que se repite la palabra
     * @return cantidad de veces que se repite la palabra
     */
    public int getRepetition() {
        return repetition;
    }

    /**
     * Funcion para obtener el archivo donde se encuentra la palabra
     * @return archivo donde se encuentra la palabra
     */
    public String getFile() {
        return file;
    }
    
    
    
    
    
    
}
