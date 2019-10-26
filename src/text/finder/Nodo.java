package text.finder;

/**
 * Clase abstracta de un nodo
 * @author Keons
 */
public class Nodo {
    
    protected Object data;
    
    public Nodo(Object data){
        this.data = data;
    }

    /**
     * Funcion para saber el doto del nodo
     * @return el dato del nodo
     */
    public Object getData(){
        return this.data;

    }

}
