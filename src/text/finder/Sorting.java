
package text.finder;


/***
 * 
 * @author jimejose
 *
 */

public class Sorting {
	
	/***


	 * QuickSort para Ordenar por Nombre.
	 * @param arr
	 * @param low
	 * @param high
	 */
	public void quickSorter(int[] arr,int low, int high) {
		int from=low;
		int to=high;
		int pivot=arr[(from+to)/2];
		int temp;
		
		do {
			while(arr[from]<pivot) {
				from++;
			}
			while(arr[to]>pivot) {
				to--;
			}
			if(from<=to) {
				temp=arr[from];
				arr[from]=arr[to];
				arr[to]=temp;
				from++;
				to++;
			}
		}while(from<=to);
		
		if(low<to) {
			quickSorter(arr,low,to);
			}
		if(from<high) {
			quickSorter(arr,from,high);
			}
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


