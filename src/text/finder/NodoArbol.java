package text.finder;

/**
 *
 * @author Keons
 */
public class NodoArbol extends Nodo{
    
    private NodoArbol der;
    private NodoArbol izq;
    private int indice;

    
    public NodoArbol(String data) {
        super(data);
        this.der = null;
        this.izq = null;
    }
    
    
    @Override
    public String getData(){
        return (String)data;
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

    /**
     *
     * @return
     */
    public int getIndice() {
        return indice;
    }

    /**
     *
     * @param indice
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }

    
}
