import java.util.*;

public class RandomDrawingDriver {

	public static void main(String[] args) {
		boolean allowDuplicates = true;
		boolean removeWinner = true;
		
		System.out.println("\n**************************TESTING GENERIC CLASS**************************\n");
		RandomDrawing<String> nameDrawing = new RandomDrawing<String>(allowDuplicates);
		fillRandomStringBox(nameDrawing);
		System.out.println("Random Name Drawing with Duplicates Allowed. Entries Are:");
		nameDrawing.displayEntries();
		System.out.println("\tBefore draw, size is       65: " + nameDrawing.size());
		String nameWinner = nameDrawing.selectWinner(removeWinner);
		System.out.print("\tAfter draw, size should be 64: " + nameDrawing.size());
		System.out.println("\tRandom winner    (with removal) was: " + nameWinner);
		System.out.print("\tAfter draw, size should be 64: " + nameDrawing.size());
		nameWinner = nameDrawing.selectWinner(!removeWinner);
		System.out.println("\tRandom winner (without removal) was: " + nameWinner);
		
		
		nameDrawing = new RandomDrawing<String>(!allowDuplicates);
		fillRandomStringBox(nameDrawing);
		
		System.out.println("\nRandom Name Drawing with Duplicates Not Allowed. Entries Are:");
		nameDrawing.displayEntries();
		System.out.println("\tBefore draw, size is       62: " + nameDrawing.size());
		nameWinner = nameDrawing.selectWinner(!removeWinner);
		System.out.print("\tAfter draw, size should be 62: " + nameDrawing.size());
		System.out.println("\tRandom winner (without removal) was: " + nameWinner);
		nameWinner = nameDrawing.selectWinner(removeWinner);
		System.out.print("\tAfter draw, size should be 61: " + nameDrawing.size());
		System.out.println("\tRandom winner    (with removal) was: " + nameWinner);
		
		
		RandomDrawing<Integer> numberDrawing = new RandomDrawing<Integer>(allowDuplicates);
		fillRandomIntegerBox(numberDrawing, 100);
		System.out.println("\nRandom Number Drawing with Duplicates Allowed: Entries Are:");
		numberDrawing.displayEntries();
		System.out.println("\tBefore draw, size is       100: " + numberDrawing.size());
		Integer numberWinner =  numberDrawing.selectWinner(removeWinner);
		System.out.print("\tAfter draw, size should be 99: " + numberDrawing.size());
		System.out.println("\tRandom winner    (with removal) was: " + numberWinner);
		numberWinner =  numberDrawing.selectWinner(!removeWinner);
		System.out.print("\tAfter draw, size should be 99: " + numberDrawing.size());
		System.out.println("\tRandom winner (without removal) was: " +numberWinner);
		
	
		// SUGGESTION: CREATE A RANDOMBOX THAT HOLDS SOME OTHER TYPE- ANY CLASS YOU'VE GOT!
		
		// UNCOMMENT WHEN YOU WRITE YOUR STATIC METHOD
		
		System.out.println("\n**************************TESTING GENERIC METHOD**************************");
		nameDrawing = new RandomDrawing<String>(allowDuplicates);
		fillRandomStringBox(nameDrawing);
		System.out.println("\nRandom Name Drawing:");
		List<String> nameWinners = selectMultipleUniqueWinners(nameDrawing, 5);
		Collections.sort(nameWinners); // for display purposes only
		System.out.println("\tList of winners should contain 5 unique winners: ");
		System.out.println("\twinners = " + nameWinners + "\tsize = " + nameWinners.size());
		System.out.println("\tAfter draw, size should be  60: " + nameDrawing.size());
		
		System.out.println("\nRandom Number Drawing:");
		List<Integer> numberWinners = selectMultipleUniqueWinners(numberDrawing, 4);
		Collections.sort(numberWinners); // for display purposes only
		System.out.println("\tList of winners should contain 4 unique winners: ");
		System.out.println("\twinners = " + numberWinners + "\tsize = " + numberWinners.size());
		System.out.println("\tAfter draw, size should be    95: " + numberDrawing.size());
		
		System.out.println("\nSmall Contest Drawing:");
		RandomDrawing<String> smallDrawing = new RandomDrawing<String>(allowDuplicates);
        smallDrawing.addEntry("Entry1");
        smallDrawing.addEntry("Entry2");
        smallDrawing.addEntry("Entry3");
		List<String> smallDrawingWinners = selectMultipleUniqueWinners(smallDrawing, 3);
		Collections.sort(smallDrawingWinners); // for display purposes only
		System.out.println("\tList of winners should contain 3 unique winners: ");
		System.out.println("\twinners = " + smallDrawingWinners + "\tsize = " + smallDrawingWinners.size());
		System.out.println("\tAfter draw, size should be     0: " + smallDrawing.size());


		System.out.println("\nSmall Contest Drawing with Repeats:");
        smallDrawing.addEntry("Entry1");
        smallDrawing.addEntry("Entry2");
        smallDrawing.addEntry("Entry3");
		Random generator = new Random();
		for(int i=0; i<27; i++) {
			int entryNumber = generator.nextInt(3)+1;
			smallDrawing.addEntry("Entry"+entryNumber);
		}
		smallDrawingWinners = selectMultipleUniqueWinners(smallDrawing, 3);
		Collections.sort(smallDrawingWinners); // for display purposes only
		System.out.println("\tList of winners should contain 3 unique winners: ");
		System.out.println("\twinners = " + smallDrawingWinners + "\tsize = " + smallDrawingWinners.size());
		System.out.println("\tAfter draw, size should be     27: " + smallDrawing.size());

	
		System.out.println("\nNot Enough Entries Drawing: Code should take some logical action but should NOT return a list with duplicate winners or enter an infinite loop.");
		RandomDrawing<String> insufficientContest = new RandomDrawing<String>(allowDuplicates);
		insufficientContest.addEntry("Entry1");
		insufficientContest.addEntry("Entry2");
        insufficientContest.addEntry("Entry3");
		List<String> insufficientWinners = selectMultipleUniqueWinners(insufficientContest, 4);
		System.out.println(insufficientWinners);
        // IMPORTANT NOTE: Depending on your solution, your program might end here.
        // If it does, you should then comment out this section above so you can test the next section. 

		System.out.println("\nEmpty Drawing: Code should take some logical action but should NOT enter an infinite loop.");
		RandomDrawing<Integer> emptyContest = new RandomDrawing<Integer>(allowDuplicates);
		List<Integer> emptyWinners = selectMultipleUniqueWinners(emptyContest, 3);
		System.out.println(emptyWinners);
	    // IMPORTANT NOTE: Depending on your solution, your program might end here.
		
		
		System.out.println("\nRegular credit testing complete!");
		
		// UNCOMMENT TO TEST YOUR EXTRA CREDIT
		
		System.out.println("\n**************************TESTING EXTRA CREDIT**************************");

		System.out.println("\nSmall Capped Contest Drawing with Repeats:");
		RandomDrawingInterface<Integer> smallCappedDrawing = new RandomDrawingCapped<Integer>(allowDuplicates, 10);
		generator = new Random();
		boolean allValuesAdded = true;
		for(int i=0; i<10; i++) {
			int entryNumber = generator.nextInt(10);
			boolean valueAdded = smallCappedDrawing.addEntry(entryNumber);
			if(!valueAdded) {
				allValuesAdded = false;
			}
		}
		System.out.println("All values added? should be true : " + allValuesAdded);
		System.out.println("\tBefore draw, size is         10: " + smallCappedDrawing.size());
		smallCappedDrawing.selectWinner(removeWinner);
		System.out.println("\tAfter draw, size should be    9: " + smallCappedDrawing.size());
		
		allValuesAdded = true;
		for(int i=0; i<10; i++) {
			int entryNumber = generator.nextInt(10);
			boolean valueAdded = smallCappedDrawing.addEntry(entryNumber);
			if(!valueAdded) {
				allValuesAdded = false;
			}
		}
		System.out.println("All values added? should be false : " + allValuesAdded);
		System.out.println("\tBefore draw, size is          10: " + smallCappedDrawing.size());
		smallCappedDrawing.selectWinner(removeWinner);
		System.out.println("\tAfter draw, size should be     9: " + smallCappedDrawing.size());

		System.out.println("\nLarge Capped Contest Drawing without Repeats:");
		RandomDrawingInterface<Integer> cappedLargeDrawing = new RandomDrawingCapped<Integer>(!allowDuplicates, 100);
		fillRandomIntegerBox(cappedLargeDrawing, 500);
		System.out.println("\tBefore draw, size is         100: " + cappedLargeDrawing.size());
		
	}

	// YOUR GENERIC METHOD HERE
	

	public static <T> List<T> selectMultipleUniqueWinners(RandomDrawingInterface<T> object, int numberOfWinners) {
		List<T> winners=new ArrayList<T>();;
		T winner;
		if (object.size()<numberOfWinners) {
			System.out.println("Not Enough Entries Drawing: The number of Winners is greater than number of candidates in the list. So everyone in the list wins");
			for (int i=0; i<object.size();i++) {
				winner=object.selectWinner(true);
				winners.add(winner);
				}
			
		}else if (numberOfWinners<=0) {
			System.out.println("Invalid Number Warming: The number of Winners is invalid, please try again");
		}else if(object.size()==0){
			System.out.println("Empty Drawing: The list is empty. No one wins!");
		}else {
				for (int i=0; i<numberOfWinners;i++) {
					winner=object.selectWinner(true);
					winners.add(winner);
				}
		}
		return winners;
	}
	
	public static void fillRandomStringBox(RandomDrawingInterface<String> wordBox) {
		wordBox.addEntry("Adam Zapel"); wordBox.addEntry("Al Dente"); wordBox.addEntry("Al Fresco"); wordBox.addEntry("Al K. Seltzer"); wordBox.addEntry("Alf A. Romeo"); wordBox.addEntry("Amanda Lynn"); wordBox.addEntry("Anita Job"); wordBox.addEntry("Anna Conda"); wordBox.addEntry("Anna Graham"); wordBox.addEntry("Anna Prentice "); wordBox.addEntry("Anna Sasin"); wordBox.addEntry("Anne Teak"); wordBox.addEntry("B.A. Ware"); wordBox.addEntry("Barb Dwyer"); wordBox.addEntry("Barb E. Dahl"); wordBox.addEntry("Barbara Seville"); wordBox.addEntry("Barry Cade"); wordBox.addEntry("Bea Minor"); wordBox.addEntry("Dee Major"); wordBox.addEntry("Beau Tye"); wordBox.addEntry("Bill Board"); wordBox.addEntry("Cara Van"); wordBox.addEntry("Chris P. Bacon"); wordBox.addEntry("Constance Noring"); wordBox.addEntry("Crystal Ball"); wordBox.addEntry("Crystal Glass"); wordBox.addEntry("Earl Lee Riser"); wordBox.addEntry("Easton West "); wordBox.addEntry("Ferris Wheeler"); wordBox.addEntry("Flint Sparks"); wordBox.addEntry("Franklin Stein "); wordBox.addEntry("Gene Poole"); wordBox.addEntry("Heidi Clare"); wordBox.addEntry("Helen Back"); wordBox.addEntry("Helen Wiells"); wordBox.addEntry("Holly McRell"); wordBox.addEntry("Holly Wood"); wordBox.addEntry("Jack Pott"); wordBox.addEntry("Joe Kerr"); wordBox.addEntry("Joy Rider"); wordBox.addEntry("Justin Case"); wordBox.addEntry("Justin Time"); wordBox.addEntry("Kandi Apple"); wordBox.addEntry("Laura Norder"); wordBox.addEntry("Leigh King "); wordBox.addEntry("Luke Warm"); wordBox.addEntry("Marsha Mellow"); wordBox.addEntry("Marshall Law"); wordBox.addEntry("Marty Graw"); wordBox.addEntry("Olive Branch"); wordBox.addEntry("Paige Turner"); wordBox.addEntry("Pepe Roni"); wordBox.addEntry("Price Wright"); wordBox.addEntry("Rocky Beach"); wordBox.addEntry("Sandy Beach"); wordBox.addEntry("Sal A. Mander"); wordBox.addEntry("Stanley Cupp"); wordBox.addEntry("Tom Morrow"); wordBox.addEntry("Warren Peace"); wordBox.addEntry("Will Power"); wordBox.addEntry("X. Benedict");
		wordBox.addEntry("Pete Repeaty"); wordBox.addEntry("Pete Repeaty"); wordBox.addEntry("Pete Repeaty"); wordBox.addEntry("Pete Repeaty");
	}
	
	public static void fillRandomIntegerBox(RandomDrawingInterface<Integer> numbersBox, int numberOfNumbers) {
		Random generator = new Random();
		int maxNumber = numberOfNumbers * 10;
		for(int i=0; i<numberOfNumbers; i++) {
			numbersBox.addEntry(generator.nextInt(maxNumber));
		}
	}
	
}
