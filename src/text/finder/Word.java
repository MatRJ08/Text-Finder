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
    
    public void addRepetition(int index){
        this.index.insertAtLast(index);
        repetition++;
    }
    
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

    public String getWord() {
        return word;
    }

    public Lista getIndex() {
        return index;
    }
    
    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }
    
    public int getRepetition() {
        return repetition;
    }

    public String getFile() {
        return file;
    }
    
    
    
    
    
    
}
