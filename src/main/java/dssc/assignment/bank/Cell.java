package dssc.assignment.bank;

public class Cell {

    private static final String ZERO_CELL =
                    " _ " +
                    "| |" +
                    "|_|";
    private static final String ONE_CELL =
                    "   " +
                    "  |" +
                    "  |";
    private static final String TWO_CELL =
                    " _ " +
                    " _|" +
                    "|_ ";
    private static final String THREE_CELL =
                    " _ " +
                    " _|" +
                    " _|";
    private static final String FOUR_CELL =
                    "   " +
                    "|_|" +
                    "  |";
    private static final String FIVE_CELL =
                    " _ " +
                    "|_ " +
                    " _|";
    private static final String SIX_CELL =
                    " _ " +
                    "|_ " +
                    "|_|";
    private static final String SEVEN_CELL =
                    " _ " +
                    "  |" +
                    "  |";
    private static final String EIGHT_CELL =
                    " _ " +
                    "|_|" +
                    "|_|";
    private static final String NINE_CELL =
                    " _ " +
                    "|_|" +
                    " _|";
    private String cellAsText;

    public Cell(String cellAsText) {
        this.cellAsText = cellAsText;
    }

    @Override
    public String toString() {
        switch (cellAsText) {
            case ZERO_CELL:
                return "0";
            case ONE_CELL:
                return "1";
            case TWO_CELL:
                return "2";
            case THREE_CELL:
                return "3";
            case FOUR_CELL:
                return "4";
            case FIVE_CELL:
                return "5";
            case SIX_CELL:
                return "6";
            case SEVEN_CELL:
                return "7";
            case EIGHT_CELL:
                return "8";
            case NINE_CELL:
                return "9";
            default:
                return "?";
        }
    }

    public String getCellAsText(){
        return cellAsText;
    }

    public int distanceCell(Cell other){
        int distance = 0;
        for (int i=0; i<9; i++){
            distance += cellAsText.substring(i,i+1).equals(other.getCellAsText().substring(i,i+1)) ? 0 : 1;
        }
        return distance;
    }
}
