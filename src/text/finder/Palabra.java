/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

/**
 *
 * @author Keons
 */
public class Palabra {
    private String word;
    private Lista index = new Lista();
    private int repetition = 0;
    
    public Palabra(String word, int index){
        this.word = word;
        this.index.insertAtLast(index);
        repetition++;
    }
    
    public void addRepetition(int index){
        this.index.insertAtLast(index);
        repetition++;
    }
    
    public boolean nextTo(int index){
        Nodo current = this.index.getHead();
        while(current != null){
            int actualIndex = (int)current.getData();
            if(actualIndex - index == 1){
                return true;
            }
        }
        return false;
    }

    public String getWord() {
        return word;
    }

    public int getRepetition() {
        return repetition;
    }
    
    
    
    
}
