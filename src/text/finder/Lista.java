package text.finder;

/**
 *
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
     * Función qe crea un nodo a partir de los datos que se ingresen y lo pone al inicio de la lista
     * @param data 
     */
    public void insertFirst(Object data){
        if (head==null){
            head = new NodoLista(data);
            head.setNext(null);
        }
        else{
          NodoLista temp = head;
          NodoLista newNodo = new NodoLista(data);
          newNodo.setNext(temp);
          head = newNodo;
          
        }
        this.size++;

    }
    
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
    
    public void insertAtLast(Object data, String name){
        if (head==null){
            head = new NodoLista(data);
            head.setName(name);
            head.setNext(null);
        }
        else{
            NodoLista temp = head;
            NodoLista newNodo = new NodoLista(data);
            newNodo.setNext(null);
            newNodo.setName(name);
            while(temp.getNext() != null)
            {
                temp = temp.getNext(); 
            }
            temp.setNext(newNodo);
        }
    }
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
   
    /**
     * Función que elimina el primer nodo de una lista
     * @return El nodo que se eliminó o null si la lista estaba vacía
     */
    public NodoLista deleteFirst(){

        if (this.head != null) {
            NodoLista temp = this.head;
            this.head = this.head.getNext();
            this.size--;
            return temp;

        }else{
             return null;

        }
    }
    
    /***
     * @see http://codigolibre.weebly.com/blog/listas-simples-en-java
     * @param data
     * @return
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
     * Método para imprimir en consola una lista
     * @param list  Lista que se quiere imprimir
     */
    public void printList(Lista list) { 
        
        NodoLista current = list.head; 
   
        System.out.print("Lista: "); 
        while (current != null) { 
            System.out.print(current.getData() + " "); 
   
            current = current.getNext(); 
        } 
    }
    
    public void deleteList(){
        this.head = null;
    }

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
