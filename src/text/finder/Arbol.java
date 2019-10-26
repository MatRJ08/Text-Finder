package text.finder;

/**
 *
 * @author Keons
 */
public class Arbol {
    private NodoArbol root = null;
    private int size;
  
    public Arbol(){
        this.root= null;
        this.size = 0;
    }
    /**
     * Función para saber si la arbol está vacía
     * @return true si está vacía o false si no lo está
     */
    public boolean isEmpty(){
        return this.root == null;
    }
    
    
    /**
     * Función que llama a la funcion insertAux
     * @param data 
     * @param index 
     * @param file 
     */
    public void insert(String data,int index, String file){
        root = insertAux(data, root, index, file);
    }
    
    
    
    /**
     * /**
     * Función qe crea un nodo a partir de los datos que se ingresen y lo inserta en el arbol
     * @param data 
     * dato que se desea insertar
     * @param current
     * nodo sobre el cual se desea insertar el data
     * @param index
     * indice de la palabra que se desea insertar
     * @param file
     * archivo en el cual se encuentra la palabra
     * @return el nodo con dato ya agregado
     */
    private NodoArbol insertAux(String data, NodoArbol current, int index, String file){
        if (current == null){            
            current = new NodoArbol(new Word(data, index, file));
            
        } else{      
          Word word = current.getData();
          String sData = word.getWord().toLowerCase() ;
          
          if( sData.compareTo(data.toLowerCase()) > 0) {              
              current.setIzq(insertAux(data, current.getIzq(), index, file));
              
          }else if( sData.compareTo(data.toLowerCase()) < 0){              
              current.setDer(insertAux(data, current.getDer(), index, file));
                     
          }else if( sData.compareTo(data.toLowerCase()) == 0){              
              current.getData().addRepetition(index);              
          }          
        }
        return current;
    }

    /**
     * Funcion para saber la raiz del arbol
     * @return la raiz del arbol
     */
    public NodoArbol getRoot() {
        return root;
    }
    
    
    /**
     * Función para saber el tamaño del arbol
     * @return El tamaño del arbol
     */
    public int getSize() {
        return size;
    }
    
    /**
     * @see https://www.geeksforgeeks.org/search-a-node-in-binary-tree/
     * @param nodo
     * @param word
     * @return Un objeto tipo palabra si la palabra se encuentra
     * @return null si la plabra no se encuentra
     */
    public Word ifNodoExists( NodoArbol nodo, String word){  
        if (nodo == null)  
            return null;  

        if (nodo.getData().getWord().toLowerCase().compareTo(word) == 0)  
            return nodo.getData();  

        // then recur on left sutree / 
        Word res1 = ifNodoExists(nodo.getIzq(), word);  

        // now recur on right subtree / 
        Word res2 = ifNodoExists(nodo.getDer(), word);  

        if(res1 != null)
            return res1;
        else
            return res2;
    }  
    
}
