package text.finder;

/***
 * 
 * @author jimejose
 *
 */

public class Sorting {
	
	/***
	 * QuickSort para Ordenar por Nombre. Still On Work
	 */
	public Nodo quickSorter(Lista arr) {
		if(arr.getHead()==null|| arr.getHead()==null) {
			return arr.getHead();
		}
		return quicky(arr.getHead(),null);
		}
	
	private Nodo quicky(Nodo start, Nodo end) {
		if(start ==null||start==end|| start.getNext()==end) {
			return start;
		}
		
		Lista[] resultados= partition(start,end);
		Nodo resultadosIzq=quicky(resultados[0].getHead(), resultados[1].getHead());
		Nodo resultadosDer=quicky(resultados[1].getHead().getNext(),end);
	}
	
	private Lista partition(Nodo start, Nodo end) {
		if(start==null|| start==end || start.getNext()==end) {
		}
		Nodo nodoDummy=new Nodo(0);
		nodoDummy.setNext(start);
		for(Nodo j = start; j != null && j.getNext() != null && j.getNext() != end; j = j.getNext()) {
			while(j.getNext() != null && j.getNext().getData() <= start.getData()) {
				Nodo temp= j.getNext();
				j.setNext(j.getNext().getNext());
				temp.setNext(nodoDummy.getNext());
				nodoDummy.setNext(temp);
				}
			}
		return new Lista[]= {nodoDummy.getNext(),start};
	}
	
	/***
	 * BubbleSort para ordenar por dia de Creacion
	 * @param arr
	 */
	
	public void bubbleSorter(int[] arr) {
		int temp;
		for(int i=1;i < arr.length;i++){
			for (int j=0 ; j < arr.length- 1; j++){
				if (arr[j] > arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
                }
            }
        }
	}
	
	 public void radixSort(int[] arr) {
			if(arr.length== 0) 
				return;
			int [][] numberpoll=new int[arr.length][2];
			int [] q =new int[0x100];
			int a,b,c,d,f=0;
			for (c=0; c<4; c++) {
				for( a=0; a<(numberpoll.length-1);a++) 
					numberpoll[a][1]= a+1;
				numberpoll[a][1]= -1;
				for(a=0 ;a<q.length; a++) {
					q[a]=-1;
				}
				for( f=a=0; a<arr.length; a++) {
					b= ((0xFF<<(c<<3))&arr[a])>>(c<<3);
					if(q[b]==-1) {
						d=q[b]=f;
					}else {
						d= q[b];
						while(numberpoll[d][1]!=-1) {
							d=numberpoll[d][1];
						}
						numberpoll[d][1]= f;
						d= numberpoll[d][1];
					}
					f=numberpoll[f][1];
					numberpoll[d][0]=arr[a];
					numberpoll[d][1]=-1;
				}
				for(d=q[a=b=0]; a<0x100; a++) {
					for(d=q[a]; d!=-1 ; d=numberpoll[d][1]) {
						arr[b++]= numberpoll[d][0];
					}
				}
			}
		}
	
	/***
	 * Obtener values del Array
	 * @param arr
	 */
	public void getArray(int[] arr) {
		int[] aux=arr;
		
		int i=0;
		while(aux !=null) {
			
			System.out.print("[ " + aux[i] + " ]" + "--> ");
			i++;
			}
		}
	}


