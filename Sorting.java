

/***
 * 
 * @author jimejose
 *
 */

public class Sorting {
	
	/***
	 * Metodo que inicia que recibe lista de Strings e inicia QuickSort
	 * @see http://grails.asia/sort-string-array-in-java
	 * @param strings
	 */
	public void quickStringSorter(String[] strings) {
		quickStringSorter(strings,0,strings.length-1);
	}
	
	/***
	 * Metodo para hacer las recursiones de quickSort
	 * @param strings
	 * @param s
	 * @param l
	 */
	public void quickStringSorter(String [] strings, int s, int l) {
		if(s>=l) {
			return;
		}
		int pivotIndex=partition(strings,s,l);
		quickStringSorter(strings,s,pivotIndex);
		quickStringSorter(strings,pivotIndex+1,l);
	}
	
	/***
	 * Metodo para la comparacion de valores y particion
	 * @param strings
	 * @param s
	 * @param l
	 * @return
	 */
	public int partition(String[] strings, int s, int l) {
		String pivot= strings[s];
		while(s<l) {
			String value1;
			String value2;
			while((value1=strings[s]).compareTo(pivot)<0) {
				s= s+1;
			}
			while((value2=strings[l]).compareTo(pivot)>0) {
				l= l-1;
			}
			strings[s]=value2;
			strings[l]=value1;
		}
		return s;
	}
	/***
	 * BubbleSort que recibe arr de enteros para ordenar por dia de Creacion
	 * @see https://www.geeksforgeeks.org/java-program-for-bubble-sort/
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
	 * RadixSort recibe una lista numeros enteros y los ordena
	 * @see https://www.ibm.com/developerworks/community/wikis/basic/anonymous/api/wiki/25603b26-9025-4003-a906-3eb0d72ce8e4/page/3f36c66d-2b7c-483d-9d83-bc99037970fb/media?convertTo=html
	 * @param arr
	 */
	
	 public void radixSorter(int[] arr) {
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


