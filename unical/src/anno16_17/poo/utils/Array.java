package anno16_17.poo.utils;

public final class Array{
	private Array(){}

	public static int ricercaLineare(int [] v, int x){
		//-1 se non trova
		for (int i = 0; i < v.length; i++)
			if (v[i] == x) return i;
		return -1;
	}

	public static int ricercaBinaria(int[] v, int size, int x){
		//v ordinato in modo crescente
		int inf = 0; int sup = v.length-1;
		while (inf <= sup){
			int med = (inf+sup)/2;
			if (v[med] == x) return med;
			if (v[med] > x) sup = med-1;
			else inf = med+1;
		}
		return -1;
	}

	public static void bubbleSort(int[] v){
		boolean scambi = true; int lim = v.length-1; int ius = -1;
		while (scambi == true){
			scambi = false;
			for (int i = 0; i < lim; i++){
				if (v[i] > v[i+1]){
					int temp = v[i]; v[i] = v[i+1]; v[i+1] = temp;
					scambi = true; ius = i;
				}
			}
			lim = ius;
		}
	}//bubbleSort ottimizzato

	public static void insertionSort(int[] v){
		for (int i = 0; i < v.length; i++){
			int x = v[i];
			int j = i;
			while (j > 0 && v[j-1] > v[j]){
				v[j] = v[j-1]; j--;
			}
			v[j] = x;
		}
	}

	public void selectionSort(Comparable[] v){
		for (int j = v.length; j > 0; j--){
			int im = 0;
			for (int i = 1; i <= j; i++){
				if ((v[i].compareTo(v[im])> 0)) im = i;
			}
			Comparable tmp = v[j];
			v[j] = v[im]; v[im] = tmp;
		}
	}
}


