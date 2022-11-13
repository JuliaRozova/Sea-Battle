package SeaBattle;

import java.util.Random;

public class Cell {
    int line;
    int column;

    public static Cell getRandomCell(int[][] seaField){
        int lineNumber = seaField.length;
        int columnNumber = seaField[0].length;

        int line = new Random().nextInt(0, lineNumber);
        int column = new Random().nextInt(0, columnNumber);

        if(seaField[line][column] == 0) return new Cell(line, column);
        else return getRandomCell(seaField);
    }
    Cell(int line, int column){
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("[line is %d, column is %d]", line, column);
    }

    public boolean isSuitableForTheShip(int[][] seaField){
        int lineNumber = seaField.length;
        int columnNumber = seaField[0].length;

        if((this.line < 0) || (this.column < 0) || (this.line >= lineNumber) || (this.column >= columnNumber)){
            return false; //точка вне поля
        } else if (seaField[this.line][this.column] == 0) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(!isEmptyOrOutOfField(new Cell((this.line - 1 + i), (this.column - 1 + j)), seaField)) {
                        return false; //точка в поле, она равна 0, но какая-то из точек вокруг заполнена
                    }
                }
            }
            return true; //точка в поле и равна 0, и все точки вокруг либо пустые, либо вне поля
        }
        return false; //точка в поле, но не равна 0
    }

    public static boolean isEmptyOrOutOfField(Cell cell, int[][] seaField){
        int lineNumber = seaField.length;
        int columnNumber = seaField[0].length;

        if ((cell.line < 0) || (cell.column < 0) || (cell.line >= lineNumber) || (cell.column >= columnNumber)){
            return true;
        } else if (seaField[cell.line][cell.column] == 0) return true;
        return false;
    }

    public static Cell nStepsRightCell(Cell cell, int steps){
        return new Cell(cell.line, (cell.column + steps));
    }

    public static Cell nStepsDownCell(Cell cell, int steps){
        return new Cell((cell.line + steps), cell.column);
    }
    // Cell.rightCell(actCell, 1)   // Cell.rightCell(actCell)                                   // actCell.rightCell()
    // Cell.rightCell(actCell, 2)   // Cell.rightCell(Cell.rightCell(actCell))                   // actCell.rightCell().rightCell()
    // Cell.rightCell(actCell, 3)   // Cell.rightCell(Cell.rightCell(Cell.rightCell(actCell)))   // actCell.rightCell().rightCell().rightCell()
    // actCell.rightCell(1)

    public Cell nStepsRightCell(int steps){
        return new Cell(this.line, (this.column + steps));
    }

    public Cell nStepsDownCell(int steps){
        return new Cell((this.line + steps), this.column);
    }

}
