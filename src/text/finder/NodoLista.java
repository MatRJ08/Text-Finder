package text.finder;

/**
 *
 * @author Keons
 */
public class NodoLista extends Nodo {
    
    private NodoLista next;
    private String name;

    public NodoLista(Object data) {
        super(data);
        this.next = null; 
    }


    


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    


    public NodoLista getNext(){
        return this.next;
    }
    public void setNext(NodoLista nodo){
        this.next = nodo;
    } 

}
