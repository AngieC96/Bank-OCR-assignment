package dssc.assignment.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return switch (cellAsText) {
            case ZERO_CELL -> "0";
            case ONE_CELL -> "1";
            case TWO_CELL -> "2";
            case THREE_CELL -> "3";
            case FOUR_CELL -> "4";
            case FIVE_CELL -> "5";
            case SIX_CELL -> "6";
            case SEVEN_CELL -> "7";
            case EIGHT_CELL -> "8";
            case NINE_CELL -> "9";
            default -> "?";
        };
    }

    private String fromIntToCell(int number) {
        return switch (number) {
            case 0 -> ZERO_CELL;
            case 1 -> ONE_CELL;
            case 2 -> TWO_CELL;
            case 3 -> THREE_CELL;
            case 4 -> FOUR_CELL;
            case 5 -> FIVE_CELL;
            case 6 -> SIX_CELL;
            case 7 -> SEVEN_CELL;
            case 8 -> EIGHT_CELL;
            case 9 -> NINE_CELL;
            default -> "";
        };
    }

    public String getCellAsText(){
        return cellAsText;
    }

    public int distanceFrom(Cell otherCell){
        int distance = 0;
        for (int i = 0; i < 9; i++){
            distance += cellAsText.substring(i, i+1).equals(otherCell.getCellAsText()
                    .substring(i, i+1)) ? 0 : 1;
        }
        return distance;
    }

    public List<Integer> distancesFrom0to9Cells() {
        List<String> cellsAsText = new ArrayList<>(List.of(ZERO_CELL, ONE_CELL, TWO_CELL, THREE_CELL,
                                                 FOUR_CELL, FIVE_CELL, SIX_CELL, SEVEN_CELL,
                                                 EIGHT_CELL, NINE_CELL));
        return cellsAsText.stream().map(Cell::new).map(this::distanceFrom).collect(Collectors.toList());
    }

    public List<Cell> nearestCells(){
        List<Integer> distances = this.distancesFrom0to9Cells();
        List<Cell> closestCells = new ArrayList<>();
        for (int i = 0; i < distances.size(); i++){
            if (distances.get(i) == 1){
                closestCells.add(new Cell(fromIntToCell(i)));
            }
        }
        return closestCells;
    }
}
