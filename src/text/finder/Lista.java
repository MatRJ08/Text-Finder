package text.finder;

/**
 * Lista enlazada simple
 * @author Keons
 */
public class Lista {
    private NodoLista head;
    private int size;
  
    public Lista(){
        this.head= null;
        this.size = 0;
    }
    /**
     * Función para saber si la lista está vacía
     * @return true si está vacía o false si no lo está
     */
    public boolean isEmpty(){
        return this.head == null;
    }
    
    /**
     * Metodo que inserta un nodo al final de la lista
     * @param data dato que se desea agregar a la lista
     */
    public void insertAtLast(Object data){
        if (head==null){
            head = new NodoLista(data);
            head.setNext(null);
        }
        else{
            NodoLista temp = head;
            NodoLista newNodo = new NodoLista(data);
            newNodo.setNext(null);
            while(temp.getNext() != null)
            {
                temp = temp.getNext(); 
            }
            temp.setNext(newNodo);
        }
    }
    
    /**
     * Metodo que inserta un nodo al final de la lista
     * @param data dato que se desea agregar a la lista
     * @param name nombre que se le desea dar al dato guardado
     */
    public void insertAtLast(Object data, String name){
        if (head==null){
            head = new NodoLista(data);
            head.setExtraValues(name);
            head.setNext(null);
        }
        else{
            NodoLista temp = head;
            NodoLista newNodo = new NodoLista(data);
            newNodo.setNext(null);
            newNodo.setExtraValues(name);
            while(temp.getNext() != null)
            {
                temp = temp.getNext(); 
            }
            temp.setNext(newNodo);
        }
    }
    
    
    /**
     * Metodo que elimina un nodo de la lista
     * @param data dato que se desea eliminar, bien puede ser el data del nodo o el name
     */
    public void delete(Object data) {
        NodoLista toDelete= buscar(data);
        if (toDelete != null) {            
            if(toDelete.getNext() != null){
                NodoLista siguiente = toDelete.getNext().getNext();
                toDelete.setNext(siguiente);  
                System.out.println("borrado"); 
            }else
                head = null;
            
        }else
            System.out.println("no borrado");
    
    }
   
    
    /***
     * Funcion que busca si el dato se encuentra en la lista
     * @see <a href= "http://codigolibre.weebly.com/blog/listas-simples-en-java"></a>
     * @param data
     * Dato a buscar en la lista
     * @return el nodo donde se encuentra el dato
     * @return null si el dato no se encuentra en la lista
     */
    public NodoLista buscar(Object data){ 
        NodoLista aux = head;
        while(aux != null){
            String sdata = (String)data;
            if (data == aux.getData() || sdata == aux.getName()){ 
            	return aux;
            }
            else{
                aux = aux.getNext();
            }
        }
        return null;
    }   
    
    /**
     * Funcion para saber cueal es el head de la lista
     * @return el head de la lista
     */
    public NodoLista getHead() {
        return head;
    }
    
    /**
     * Función para saber el tamaño de la lista
     * @return El tamaño de la lista
     */
    public int getSize() {
        return size;
    }
    
}
