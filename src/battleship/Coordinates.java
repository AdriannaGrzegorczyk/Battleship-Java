package battleship;

import java.util.Objects;

public class Coordinates {

    int rowIndex;
    int columnIndex;


    public Coordinates(int rowIndex, int columnIndex){
        this.rowIndex = rowIndex;
        this.columnIndex=columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }


    public String convertToAlphabetic (){

        String rowIndex = (String.valueOf((char)('A'+ this.rowIndex)));
        String columnIndex = Integer.toString(this.columnIndex+1);

        return rowIndex + columnIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return rowIndex == that.rowIndex && columnIndex == that.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }
}
