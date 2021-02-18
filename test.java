
public class test {
	public static void main(String[] args) {
		LinkedFrontBackLimitedSizeList<Integer> list=new LinkedFrontBackLimitedSizeList<Integer>(5);
		 list.addBack(1);
		 list.addBack(1);
		 list.addBack(1);
		 list.addBack(1);
		 System.out.println(list.toString());
	}
}
