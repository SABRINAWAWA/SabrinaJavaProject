/**
 * @author Jessica Masters
 *
 * An interface for a random drawing, such as a lottery.
 * The drawing may or may not allow duplicate entries.
 * Winners are drawn at random.
 *  
 * 
 */
public interface RandomDrawingInterface <T> {
	
	/**
	 * Adds an entry to the random drawing. The entry may or may not be accepted.
	 * 
	 * @param entry The entry to be added to the drawing
	 * @return true if the entry can be added, false otherwise
	 */
	public abstract boolean addEntry(T entry);
	
	/**
	 * Selects a winner from the random drawing.
	 *
	 * @param removeWinner true if the selected winner should be removed, false otherwise
	 * @return the randomly selected winner
	 */
	public abstract T selectWinner(boolean removeWinner);
	
	/**
	 * Gets the number of entries in the drawing
	 * 
	 * @return the number of entries in the drawing
	 */
	public abstract int size();
	
	/**
	 * Displays to the screen all entries in the drawing
	 */
	public abstract void displayEntries();
	

}
