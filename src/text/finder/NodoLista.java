package text.finder;

/**
 * Clase concreta del nodo para usarlo en listas enlazadas
 * @author Keons
 */
public class NodoLista extends Nodo {
    
    private NodoLista next;
    private String name;
    private int size;

    public NodoLista(Object data) {
        super(data);
        this.next = null; 
    }


    

    /**
     * funcion para saber el nombre que se le dio al dato del nodo
     * @return nombre que se le dio al dato del nodo
     */
    public String getName() {
        return name;
    }
    /**
     * Metodo para definir el nombre del dato del nodo
     * @param name nombre del dato del nodo
     */
    public void setName(String name) {
        this.name = name;
    }
    

    /**
     * funcion para obtener el nodo siguiente
     * @return nodo siguiente
     */
    public NodoLista getNext(){
        return this.next;
    }
    /**
     * metodo para definir el nodo siguiente
     * @param nodo nodo siguiente
     */
    public void setNext(NodoLista nodo){
        this.next = nodo;
    } 

}
