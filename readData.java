import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class readData {
	public static void main(String[] args) {
		System.out.println("----------------Creating new List that containing COVID19Data---------------");
		List<COVID19Data> covid19List = new ArrayList<COVID19Data>();
		readDataIntoList(covid19List);
		/*
		 for(COVID19Data data:covid19List) { System.out.println(data); }
		 */	

		System.out.println("\n--------Creating new Map that containing key(submission date) and value(COVID19Data)----------");
		Map<String, COVID19Data> covid19Map = new TreeMap<>();
		readDataIntoMap(covid19Map);
		/*
		  for (Map.Entry<String, Integer> entry : covid19Map.entrySet()) {
		  System.out.println("For state "+entry.getKey() +
		  entry.getValue().toString()+". "); }
		 */

		System.out.println("\n--------Creating new Map that containing key(state) and value(totalDeath)---------");
		Map<String, Integer> covid19TotalDeathMap = new TreeMap<>();
		mapWithHighestTotalDeath(covid19TotalDeathMap);
		/*
		  for (Map.Entry<String, Integer> entry : covid19TotalDeathMap.entrySet()) {
		  System.out.println("For state "+entry.getKey() +
		  ", the highest total death is " + entry.getValue().toString()+". "); }
		 */
		
		System.out.println("\n Qestion 1: What date has the highest COVID19 case increment in California?");
		List<COVID19Data> CAList = new ArrayList<COVID19Data>(stateSortList(covid19List, "CA"));
		// for(COVID19Data data:CAList) { System.out.println(data); }
		System.out.println(
				"The date has highest Covid19 case increment for California state is " + compareNewCases(CAList));

		System.out.println("\n Qestion 2: What date has the highest COVID19 case increment in Tennessee?");
		List<COVID19Data> TNList = new ArrayList<COVID19Data>(stateSortList(covid19List, "TN"));
		// for(COVID19Data TNList) { System.out.println(data); }
		System.out.println(
				"The date has highest Covid19 case increment for Tennessee state is " + compareNewCases(TNList));

		System.out.println("\n Qestion 3: What date has the highest COVID19 case increment in Florida?");
		List<COVID19Data> FLList = new ArrayList<COVID19Data>(stateSortList(covid19List, "FL"));
		// for(COVID19Data FLList) { System.out.println(data); }
		System.out.println("The date has highest Covid19 case increment for Tennessee state is " + compareNewCases(FLList));

		System.out.println("\n Question 4: Until 10/07/2020, which state has the highest total death number?");
		System.out.println("State: "+compareTotalDeath(covid19TotalDeathMap));
		
		System.out.println("\n Question 5: How many states have the total death number over 10,000 until 10/07/2020?");
		System.out.println("The total death numbers of "+countTotalDeathOver(covid19TotalDeathMap, 10000)+" states are over 10,000.");
	}

	public static void readDataIntoList(List<COVID19Data> list) {
		try (Scanner fileScan = new Scanner((Readable) new FileReader(new File("covid19data.csv")))) {
			String line = fileScan.nextLine(); // read the column headers

			while (fileScan.hasNext()) {
				line = fileScan.nextLine();
				Scanner lineScan = new Scanner(line);
				// System.out.println(line);
				lineScan.useDelimiter(",");
				String submissionData = lineScan.next();
				String state = lineScan.next();
				int totalCases = lineScan.nextInt();
				int newCases = lineScan.nextInt();
				int totalDeath = lineScan.nextInt();
				int newDeath = lineScan.nextInt();
				COVID19Data data = new COVID19Data(submissionData, state, totalCases, newCases, totalDeath, newDeath);
				
				list.add(data);
			}
			System.out.println("covid19List completed!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void readDataIntoMap(Map<String, COVID19Data> map) {
		try (Scanner fileScan = new Scanner((Readable) new FileReader(new File("covid19data.csv")))) {
			String line = fileScan.nextLine(); // read the column headers

			while (fileScan.hasNext()) {
				line = fileScan.nextLine();
				Scanner lineScan = new Scanner(line);
				// System.out.println(line);
				lineScan.useDelimiter(",");
				String submissionData = lineScan.next();
				String state = lineScan.next();
				int totalCases = lineScan.nextInt();
				int newCases = lineScan.nextInt();
				int totalDeath = lineScan.nextInt();
				int newDeath = lineScan.nextInt();
				COVID19Data data = new COVID19Data(submissionData, state, totalCases, newCases, totalDeath, newDeath);
				map.put(submissionData, data);
			}
			System.out.println("covid19Map completed!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void mapWithHighestTotalDeath(Map<String, Integer> map) {
		try (Scanner fileScan = new Scanner((Readable) new FileReader(new File("covid19data.csv")))) {
			String line = fileScan.nextLine(); // read the column headers

			while (fileScan.hasNext()) {
				line = fileScan.nextLine();
				Scanner lineScan = new Scanner(line);
				// System.out.println(line);
				lineScan.useDelimiter(",");
				String submissionData = lineScan.next();
				String state = lineScan.next();
				int totalCases = lineScan.nextInt();
				int newCases = lineScan.nextInt();
				int totalDeath = lineScan.nextInt();
				int newDeath = lineScan.nextInt();
				COVID19Data data = new COVID19Data(submissionData, state, totalCases, newCases, totalDeath, newDeath);
				
				if (data.getSubmissionDate().equalsIgnoreCase("10/7/2020")) {
					map.put(state, totalDeath);
				}
			}
			System.out.println("covid19TotalDeathMap completed!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// ---------------Methods used for solving question 1-3.
	private static List<COVID19Data> stateSortList(List<COVID19Data> list, String state) {
		Iterator<COVID19Data> iterator = list.iterator();
		List<COVID19Data> stateList = new ArrayList<COVID19Data>();
		while (iterator.hasNext()) {
			COVID19Data data = iterator.next();
			if (data.getState().equalsIgnoreCase(state)) {
				stateList.add(data);
			}
		}
		return stateList;
	}

	private static String compareNewCases(List<COVID19Data> list) {
		Collections.sort(list);
		String result = list.get(list.size() - 1).getSubmissionDate() + ", the number of new cases is "
				+ list.get(list.size() - 1).getNewCases() + ". ";
		return result;
	}

	// -------------Method used for solving question 4.
	private static <K,V extends Comparable<V>> String compareTotalDeath(Map<String, Integer> map) {
		Entry<String, Integer> maxEntry = null;
	    for (Entry<String, Integer> entry : map.entrySet()) {
	        if (maxEntry == null || entry.getValue().compareTo((Integer) maxEntry.getValue()) > 0) {
	            maxEntry = entry;
	        }
	    }
	    String result=maxEntry.getKey()+" has the highest total death number and the number of total death is "+maxEntry.getValue()+". ";
	    return result;
	}

	//--------------Method used for solving Question 5.
	private static int countTotalDeathOver(Map<String,Integer> map, int number) {
	    int count = 0;
	    Collection<Integer> values = map.values();
	    Iterator<Integer> iterator = values.iterator();
	    while(iterator.hasNext()) {
	        Integer totalDeath = iterator.next();
            if(totalDeath>number) {
                count++;
              }
            }
	    return count;
	    }
	}

/*
Sample Output:
----------------Creating new List that containing COVID19Data---------------
covid19List completed!

--------Creating new Map that containing key(submission date) and value(COVID19Data)----------
covid19Map completed!

--------Creating new Map that containing key(state) and value(totalDeath)---------
covid19TotalDeathMap completed!

 Qestion 1: What date has the highest COVID19 case increment in California?
The date has highest Covid19 case increment for California state is 7/22/2020, the number of new cases is 12807. 

 Qestion 2: What date has the highest COVID19 case increment in Tennessee?
The date has highest Covid19 case increment for Tennessee state is 7/13/2020, the number of new cases is 3314. 

 Qestion 3: What date has the highest COVID19 case increment in Florida?
The date has highest Covid19 case increment for Tennessee state is 7/12/2020, the number of new cases is 15135. 

 Question 4: Until 10/07/2020, which state has the highest total death number?
State: NYC has the highest total death number and the number of total death is 23874. 

 Question 5: How many states have the total death number over 10,000 until 10/07/2020?
The total death numbers of 5 states are over 10,000.
*/
