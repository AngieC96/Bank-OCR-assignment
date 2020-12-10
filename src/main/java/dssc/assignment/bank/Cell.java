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
        if (cellAsText.equals(ZERO_CELL)){
            return "0";
        } else if (cellAsText.equals(ONE_CELL)){
            return "1";
        } else if (cellAsText.equals(TWO_CELL)){
            return "2";
        } else if (cellAsText.equals(THREE_CELL)){
            return "3";
        } else if (cellAsText.equals(FOUR_CELL)){
            return "4";
        } else if (cellAsText.equals(FIVE_CELL)){
            return "5";
        } else if (cellAsText.equals(SIX_CELL)){
            return "6";
        } else if (cellAsText.equals(SEVEN_CELL)){
            return"7";
        } else if (cellAsText.equals(EIGHT_CELL)){
            return "8";
        } else if (cellAsText.equals(NINE_CELL)) {
            return "9";
        } else {
            return "?";
        }
    }
}
