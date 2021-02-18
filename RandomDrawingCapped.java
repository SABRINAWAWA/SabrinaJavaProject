import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomDrawingCapped<T> implements RandomDrawingInterface<T> {
	int size;
	boolean allowDuplicates;
	int maximumSize;
	List<T> drawingList;
	Set<T> drawingUniqueList;

	public RandomDrawingCapped(boolean allowDuplicates, int maximumSize) {
		this.allowDuplicates = allowDuplicates;
		this.size = 0;
		this.maximumSize = maximumSize;
		if (!allowDuplicates) {
			drawingList = new ArrayList<T>();
			drawingUniqueList = new HashSet<T>();
		} else {
			drawingList = new ArrayList<T>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addEntry(Object entry) {
		if (size < maximumSize) {
			if (allowDuplicates == true) {
				drawingList.add((T) entry);
				size++;
				return true;
			} else if (allowDuplicates == false) {
				drawingList.add((T) entry);
				if (drawingUniqueList.add((T) entry) == true) {
					size++;
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	@Override
	public T selectWinner(boolean removeWinner) {
		T winner = null;
		Random draw = new Random();
		if (allowDuplicates == true) {
			int randomWinner = draw.nextInt(drawingList.size());
			winner = drawingList.get(randomWinner);
			if (removeWinner == true) {
				drawingList.remove(randomWinner);
				size--;
			}
		} else {
			int randomWinner = draw.nextInt(drawingUniqueList.size());
			int currentIndex = 0;
			Iterator<T> iterator = drawingUniqueList.iterator();
			while (iterator.hasNext()) {

				T items = (T) iterator.next();
				if (currentIndex == randomWinner && removeWinner == true) {
					winner = items;
					drawingUniqueList.remove(items);
					size--;
					return winner;
				} else if (currentIndex == randomWinner && removeWinner == false) {
					winner = items;
					return winner;
				}
				currentIndex++;
			}
		}
		return winner;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void displayEntries() {
		if (allowDuplicates = true) {
			System.out.println(drawingList.toString());
		} else {
			System.out.println(drawingUniqueList.toString());
		}
	}

}