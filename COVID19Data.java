import java.util.Comparator;

public class COVID19Data implements Comparable<COVID19Data>{
	private String submissionDate, state;
	private int totalCases, newCases, totalDeath, newDeath;
	
	public COVID19Data(String submissionDate1, String state1, int totalCases1,int newCases1, int totalDeath1, int newDeath1) {
		this.submissionDate=submissionDate1;
		this.state=state1;
		this.totalCases=totalCases1;
		this.newCases=newCases1;
		this.totalDeath=totalDeath1;
		this.newDeath=newDeath1;
	} 
	
	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}

	@Override
	public String toString() {
		return "submissionDate=" + submissionDate + ", state=" + state + ", totalCases=" + totalCases
				+ ", newCases=" + newCases + ", totalDeath=" + totalDeath + ", newDeath=" + newDeath;
	}

	public int getNewCases() {
		return newCases;
	}

	public void setNewCases(int newCases) {
		this.newCases = newCases;
	}

	public int getTotalDeath() {
		return totalDeath;
	}

	public void setTotalDeath(int totalDeath) {
		this.totalDeath = totalDeath;
	}

	public int getNewDeath() {
		return newDeath;
	}

	public void setNewDeath(int newDeath) {
		this.newDeath = newDeath;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof COVID19Data)) {
			return false;
		}
		COVID19Data other = (COVID19Data) obj;
		if (newCases != other.newCases) {
			return false;
		}
		if (newDeath != other.newDeath) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (submissionDate == null) {
			if (other.submissionDate != null) {
				return false;
			}
		} else if (!submissionDate.equals(other.submissionDate)) {
			return false;
		}
		if (totalCases != other.totalCases) {
			return false;
		}
		if (totalDeath != other.totalDeath) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(COVID19Data otherData) {
		return Integer.compare(this.getNewCases(), otherData.getNewCases());
	}
	
	
	public static final Comparator TOTAL_DEATH_COMPARATOR = new totalDeathComparator();
	
	private static class totalDeathComparator implements Comparator<COVID19Data> {
		public int compare(COVID19Data a, COVID19Data b) {
		return Integer.compare(a.getTotalDeath(), b.getTotalDeath());
		}
	}
	
}
	
