package text.finder;

/**
 *
 * @author Keons
 */
public class NodoLista extends Nodo {
    
    private NodoLista next;

    public NodoLista(Object data) {
        super(data);
        this.next = null;
    }

    


    public NodoLista getNext(){
        return this.next;

    }

    public void setNext(NodoLista nodo){
        this.next = nodo;

    } 
}
