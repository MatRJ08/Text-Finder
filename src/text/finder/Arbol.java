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
     */
    public void insert(String data){
        root = insertAux(data, root);
    }
    
    
    /**
     * Función qe crea un nodo a partir de los datos que se ingresen y lo inserta en el arbol
     * @param data 
     */
    private NodoArbol insertAux(String data, NodoArbol nodo){
        if (nodo == null){            
            nodo = new NodoArbol(data);
            
        } else{            
          String sData = (String)nodo.getData().toLowerCase();
          
          if( sData.compareTo(data.toLowerCase()) > 0) {              
              nodo.setIzq(insertAux(data, nodo.getIzq()));
              
          }else if( sData.compareTo(data.toLowerCase()) < 0){              
              nodo.setDer(insertAux(data, nodo.getDer()));
              
          }          
        }
        return nodo;
    }
    
    
   
    /**
     * Función que elimina un nodo del arbol
     * @return El nodo que se eliminó o null si la arbol estaba vacía
     */
//    public NodoArbol deleteFirst(){
//
//        if (this.root != null) {
//            NodoArbol temp = this.root;
//            this.root = this.root.getNext();
//            this.size--;
//            return temp;
//
//        }else{
//             return null;
//
//        }
//    }
    
    /**
     * Método para imprimir en consola una arbol
     * @param tree  Arbol que se quiere imprimir
     */
//    public void printList(Arbol tree) { 
//        
//        NodoArbol current = tree.root; 
//   
//        System.out.print("Arbol: "); 
//        while (current != null) { 
//            System.out.print(current.getData() + " "); 
//   
//            current = current.getNext(); 
//        } 
//    }
    /**
     * 
     * @return root
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
    
}
