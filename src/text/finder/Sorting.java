package sorters;

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


