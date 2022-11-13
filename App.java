package SeaBattle;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        int[][] actualField = generateStandardField();
        printSeaField(actualField);
    }

    public static void setTheShip(int[][] seaField, int shipLength){
        Cell cell = Cell.getRandomCell(seaField);
        boolean goRight = new Random().nextBoolean();
        boolean isRightChecked = false;
        boolean isDownChecked = false;

        while(!isRightChecked || !isDownChecked) {

            if (goRight && !isRightChecked) {
                isRightChecked = true;
                for (int i = 0; i < shipLength; i++) {
                    if (!cell.nStepsRightCell(i).isSuitableForTheShip(seaField)) {
                        goRight = false;
                        break;
                    }
                }
                if (goRight) {
                    //поставить корабль вправо можно
                    for (int i = 0; i < shipLength; i++) {
                        seaField[cell.line][cell.column + i] = 1;
                    }
                    return;
                }
            }

            if (!goRight && !isDownChecked) {
                isDownChecked = true;
                for (int i = 0; i < shipLength; i++) {
                    if (!cell.nStepsDownCell(i).isSuitableForTheShip(seaField)) {
                        goRight = true;
                        break;
                    }
                }
                if (!goRight) {
                    //поставить корабль можно
                    for (int i = 0; i < shipLength; i++) {
                        seaField[cell.line + i][cell.column] = 1;
                    }
                    return;
                }
            }
        }
        setTheShip(seaField, shipLength);
    }



    public static void printSeaField(int[][] seaField){
        for (int i = 0; i < seaField.length; i++) {
            for (int j = 0; j < seaField[i].length; j++) {
                System.out.print(seaField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[][] generateStandardField() {
        int[][] standardField = new int[10][10];
        for (int i = 0; i < 1; i++) setTheShip(standardField,4);
        for (int i = 0; i < 2; i++) setTheShip(standardField,3);
        for (int i = 0; i < 3; i++) setTheShip(standardField,2);
        for (int i = 0; i < 4; i++) setTheShip(standardField,1);
        return standardField;
    }
}

