import java.util.ArrayList;

public class CustomPQ<T extends Comparable<T>> {
	//This is based on a minimum heap, not maximum heap. Poll gets the lowest priority element.
	//has a public update() method that Java PQ lacks
	// might not work (untested) lol
	private ArrayList<T> array;

	public CustomPQ() {
		array = new ArrayList<T>();
	}

	void update() {
		for (int i = array.size() / 2; i >= 0; i--) {
			sink(i);
		}
	}

	void add(T element) {
		array.add(element);
		update();
	}

	T poll() {
		swap(0, array.size()-1);
		T returnelement = array.remove(array.size()-1);
		update();
		return returnelement;
	}
	
	boolean isEmpty() {
		return array.isEmpty();
	}
	private void sink(int i) {
		int left = 2 * i + 1;
		int right = left + 1;
		int smaller = left;
		if (left > array.size() - 1) {
			return;
		}
		if (!(right > array.size() - 1) && array.get(right).compareTo(array.get(left)) < 0) {
			smaller = right;
		}
		if (array.get(smaller).compareTo(array.get(i)) < 0) {
			swap(i, smaller);
			sink(smaller);
		}
	}

	private void swap(int i, int j) {
		T temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}
}
