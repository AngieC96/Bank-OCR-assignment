package dssc.assignment.bank;

import java.util.ArrayList;
import java.util.List;

public class Entry {

    List<Cell> cells = new ArrayList<>();

    public Entry(String firstLine, String secondLine, String thirdLine) throws StringIndexOutOfBoundsException {
        try {
            while (!firstLine.isEmpty()) {
                Cell cell = new Cell(firstLine.substring(0, 3) + secondLine.substring(0, 3) + thirdLine.substring(0, 3));
                cells.add(cell);
                firstLine = firstLine.substring(3);
                secondLine = secondLine.substring(3);
                thirdLine = thirdLine.substring(3);
            }
        }
        catch (StringIndexOutOfBoundsException e){
            System.err.println("Line too short!");
        }


    }

    public Entry(List<Cell> listOfCells) {
        this.cells = listOfCells;
    }

    @Override
    public String toString() {
        StringBuilder entryAsString = new StringBuilder();
        cells.forEach(cell -> entryAsString.append(cell.toString()));
        return entryAsString.toString();
    }

    public List<Cell> getCells() {
        return cells;
    }
}
