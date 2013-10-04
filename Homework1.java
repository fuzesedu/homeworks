interface Mergesort {
	int[] getFirstHalfOf(int[] array); // vrati nesetridenou kopii prvni
										// poloviny (horni celou cast) pole
										// array

	int[] getSecondHalfOf(int[] array); // vrati nesetridenou kopii druhe
										// poloviny (dolni celou cast) pole
										// array

	int[] merge(int[] firstHalf, int[] secondHalf); // slije prvky v firstHalf a
													// secondHalf do jednoho
													// pole a vrati ho

	int[] mergesort(int[] array); // vrati setridenou kopii pole array
}

public class Homework1 implements Mergesort {

	public static void main(String[] args) {
		int[] array = { 2, 8, 5, 3, 7, 1, 6, 4 };
		Mergesort mRes = new Homework1();
		int[] res = mRes.mergesort(array);
		print(res);
	}

	@Override
	public int[] getFirstHalfOf(int[] array) {
		int[] res = new int[Math.round(array.length / 2)];
		if (array.length > 1) {
			for (int i = 0; i < res.length; i++) {
				res[i] = array[i];
			}
		} else {
			res = array;
		}
		return res;
	}

	// OK
	@Override
	public int[] getSecondHalfOf(int[] array) {
		int length = Math.round(array.length / 2);// find the middle
		int[] res = new int[length];
		if (length > 1) {
			if (array.length % 2 != 0) {
				length = Math.round(array.length / 2 + 1);
				res = new int[length];
				length--;
				System.out.println("delitelne");
				for (int i = 0; i < length + 1; i++) {
					res[i] = array[length + i];
				}
			} else {
				for (int i = 0; i < length; i++) {
					res[i] = array[length + i]; // copy array into the res;
				}
			}
		} else {
			res = array;
		}
		return res;
	}

	// OK
	@Override
	public int[] merge(int[] firstHalf, int[] secondHalf) {
		int firstLength = firstHalf.length, secondLength = secondHalf.length;
		int length = firstLength + secondLength;
		int[] res = new int[length];
		int i = 0, j = 0;
		for (int k = 0; k < length; k++) {
			if (k <= length && i < firstLength && j < secondLength) {// classic
																		// numbers
				if (firstHalf[i] < secondHalf[j]) {
					res[k] = firstHalf[i];
					i++;
				} else if (firstHalf[i] > secondHalf[j]) {
					res[k] = secondHalf[j];
					j++;
				}
			} else {// for ends of arrays
				if (i < firstLength) {
					res[k] = firstHalf[i];
				} else {
					res[k] = secondHalf[j];
					j++;
				}
			}
		}
		return res;
	}

	@Override
	public int[] mergesort(int[] array) {
		if (array.length == 1)
			return array;

		int length = array.length / 2;
		int[] first = new int[length];
		int[] second = new int[array.length - length];

		for (int i = 0; i < length; i++)
			first[i] = array[i];
		for (int i = length; i < array.length; i++)
			second[i - length] = array[i];
		return merge(mergesort(first), mergesort(second));
	}

	private static void print(int[] a) {
		for (int j = 0; j < a.length; j++) {
			System.out.print(a[j] + " ");
		}
		System.out.println();
	}
}