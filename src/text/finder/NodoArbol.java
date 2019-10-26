package text.finder;

/**
 *
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
     *
     * @return
     */
    public NodoArbol getDer(){
        return this.der;

    }  

    /**
     *
     * @param nodo
     */
    public void setDer(NodoArbol nodo){
        this.der = nodo;

    } 
    
    /**
     *
     * @return
     */
    public NodoArbol getIzq() {
        return izq;
    }

    /**
     *
     * @param izq
     */
    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }

    

    
}
