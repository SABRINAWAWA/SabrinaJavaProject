public class LinkedFrontBackLimitedSizeList<T extends Comparable<? super T>> 
     implements FrontBackLimitedSizeListInterface<T>, 
     Comparable<LinkedFrontBackLimitedSizeList<T>> { 

	private Node head, tail;
	private int numberOfEntries;
	private int maxSize;

	public LinkedFrontBackLimitedSizeList(int i) {
		initializeDataField();
		maxSize = i;
	}

	private void initializeDataField() {
		head = null;
		tail = null;
		numberOfEntries = 0;
	}

	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[numberOfEntries];
		int index = 0;
		Node currentNode = head;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			currentNode = currentNode.next;
			index++;
		}
		return result;
	}

	@Override
	public String toString() {
		String result = "";
		Node current = head;
		if (isEmpty()) {
			return "[]";
		} else {
			while (current != null) {
				result += current.getData();
				if (current.next != null) {
					result += ", ";
				}
				current = current.next;
			}
			return "[" + result + "] " + "\t head=" + head.data + "\t tail=" + tail.data;
		}
	}

	@Override
	public boolean addFront(T newEntry) {
		Node newNode = new Node(newEntry);
		if (numberOfEntries == maxSize) {
			return false;
		} else if (numberOfEntries == 0) {
			head = tail = newNode;
			tail.next = null;
			head.next = null;
			numberOfEntries++;
			return true;
		} else {
			newNode.next = head;
			head = newNode;
			numberOfEntries++;
			return true;
		}
	}

	@Override
	public boolean addBack(T newEntry) {
		Node newNode = new Node(newEntry);
		if (numberOfEntries == maxSize) {
			return false;
		} else if (numberOfEntries == 0) {
			head = newNode;
			tail = newNode;
			numberOfEntries++;
			return true;
		} else {
			tail.next = newNode;
			tail = newNode;
			numberOfEntries++;
			return true;
		}
	}

	@Override
	public T removeFront() {
		if (isEmpty()) {
			return null;
		} else {
			T deleteItem = head.data;
			head = head.next;
			numberOfEntries--;
			return deleteItem;
		}
	}

	@Override
	public T removeBack() {
		if (isEmpty()) {
			return null;
		} else {
			T deleteItem = tail.data;
			tail = getNodeAt(numberOfEntries - 1);
			tail.next = null;
			numberOfEntries--;
			return deleteItem;
		}
	}

	private Node getNodeAt(int givenPosition) {
		Node currentNode = head;
		for (int i = 1; i < givenPosition; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}

	@Override
	public void clear() {
		initializeDataField();
	}

	@Override
	public T getEntry(int givenPosition) {
		Node currentNode = head;
		if (givenPosition > numberOfEntries || givenPosition < 0) {
			return null;
		} else {
			for (int i = 0; i < givenPosition; i++) {
				currentNode = currentNode.next;
			}
		}
		return currentNode.data;
	}

	@Override
	public int indexOf(T anEntry) {
		int index = 0;
		Node currentNode = head;
		while (currentNode != null) {
			if (currentNode.data.equals(anEntry)) {
				return index;
			}
			index++;
			currentNode = currentNode.next;
		}
		return -1;

	}

	@Override
	public int lastIndexOf(T anEntry) {
		int index = -1;
		Node currentNode = head;
		if (!isEmpty()) {
			for (int i = 0; i < numberOfEntries; i++) {
				if (currentNode.data.equals(anEntry)) {
					index = i;
				}
				currentNode = currentNode.next;
			}
			return index;
		} else {
			return -1;
		}
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = head;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.data)) {
				found = true;
			} else {
				currentNode = currentNode.next;
			}
		}
		return found;
	}

	@Override
	public int size() {
		return numberOfEntries;
	}

	@Override
	public boolean isEmpty() {
		if (numberOfEntries == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (numberOfEntries == maxSize) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(LinkedFrontBackLimitedSizeList<T> object) {
		Node currentNode=head;
		Node objNode=object.head;
		if(this.numberOfEntries==0&&object.numberOfEntries==0) {
			return 0;
		}
		while(currentNode!=null&&objNode!=null&&currentNode.data.equals(objNode.data)) {
			currentNode=currentNode.next;
			objNode=objNode.next;
		}
		if (currentNode!=null&&objNode!=null ) {
			return currentNode.data.compareTo(objNode.data);
		}
		if(currentNode!=null&&objNode==null) {
			return 1;
		}
		if (currentNode==null&&objNode!=null) {
			return -1;
		}
		return 0;
	}
	
	public class Node {
		public T data;
		public Node next;

		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		}

		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}

	}

	
}
