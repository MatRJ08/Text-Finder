package text.finder;

/**
 * Clase concreta del nodo para usarlo en arboles binarios de busqueda
 * @author Keons
 */
public class NodoArbol extends Nodo{
    
    private NodoArbol der;
    private NodoArbol izq;

    
    public NodoArbol(Word data) {
        super(data);
        this.der = null;
        this.izq = null;
    }
    
    
    @Override
    public Word getData(){
        return (Word)data;
    }
    
    /**
     * Funcion para saber el hijo derecho del nodo
     * @return hijo derecho del nodo
     */
    public NodoArbol getDer(){
        return this.der;

    }  

    /**
     * metodo para definir el hijo derecho del nodo
     * @param nodo nodo a insertar en hijo derecho
     */
    public void setDer(NodoArbol nodo){
        this.der = nodo;

    } 
    
    /**
     * Funcion para saber el hijo izquierdo del nodo
     * @return hijo izquierdo del nodo
     */
    public NodoArbol getIzq() {
        return izq;
    }

    /**
     * metodo para definir el hijo izquierdo del nodo
     * @param izq nodo a insertar en hijo izquierdo
     */
    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }

    

    
}
