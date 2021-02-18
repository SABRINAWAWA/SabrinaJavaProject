import javafx.scene.shape.Line;

@FunctionalInterface
public interface LineInfoDisplayer {

	String getInfo(Line line);

	public static enum InfoType {
		DISTANCE, MIDPOINT, VERTHORZ, SLOPE;
	}

	public static LineInfoDisplayer createLineInfoDisplayer(InfoType type) {
		switch (type) {
		case DISTANCE:
			System.out.println("distance");
			
			return (Line line) -> {
				String distance = "";
				if (line.getStartX() != 0 && line.getStartY() != 0 && line.getEndX() != 0 && line.getEndX() != 0) {
					distance = String.format("%.1f", Math.abs(Math.sqrt(Math.pow((line.getStartX() - line.getEndX()), 2)
							+ Math.pow((line.getStartY() - line.getEndY()), 2))));
				}
				return distance;
			};

		case MIDPOINT:
			System.out.println("midpoint");
			
			return (Line line) -> {
				String coord="";
				if (line.getStartX() != 0 && line.getStartY() != 0 && line.getEndX() != 0 && line.getEndX() != 0) {
					coord = "(" + Double.toString((line.getStartX() + line.getEndX()) / 2) + ", "
							+ Double.toString((line.getStartY() + line.getEndY()) / 2) + ")";
				}
				return coord;
			};

		case VERTHORZ:
			System.out.println("vertical or horizontal?");
			return (Line line) -> {
				if (line.getStartX() == line.getEndX()) {
					return "Vertical Line: True; Horizontal Line: False";
				} else if (line.getStartY() == line.getEndY()) {
					return "Vertical Line: False; Horizontal Line: True";
				} else {
					return "Vertical Line: False; Horizontal Line: False";
				}
			};
			
		case SLOPE:
			System.out.println("Slope");
			return (Line line) -> {
				String slope = "";
				if (line.getStartX() != 0 && line.getStartY() != 0 && line.getEndX() != 0 && line.getEndX() != 0) {
					slope = String.format("%.1f", (line.getStartY() - line.getEndY())/(line.getStartX() - line.getEndX()));
				}
				return slope;
			};
		}
		
		return null;
	}

}
