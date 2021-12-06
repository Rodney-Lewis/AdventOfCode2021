package Day4;

import java.util.Arrays;

public class BingoBoard {
  final static int BOARDSIZE = 5;
  BingoBoardSpace[][] bingoBoardSpaces;
  int winningNumber;

  public BingoBoard() {
    bingoBoardSpaces = new BingoBoardSpace[BOARDSIZE][BOARDSIZE];
    winningNumber = 0;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.deepHashCode(bingoBoardSpaces);
    result = prime * result + winningNumber;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BingoBoard other = (BingoBoard) obj;
    if (!Arrays.deepEquals(bingoBoardSpaces, other.bingoBoardSpaces))
      return false;
    if (winningNumber != other.winningNumber)
      return false;
    return true;
  }

  public void printDetails() {
    System.out.println("Card details: ");
    printBingoCard();
    printMarkedBingoCard();
    System.out.println("Winning number: " + winningNumber);
    System.out.println("Final board value: " + getFinalBoardValue());
    System.out.println();
  }

  private int getFinalBoardValue() {
    int sumOfUnmarkedNumbers = 0;
    for (int x = 0; x < BOARDSIZE; x++) {
      for (int y = 0; y < BOARDSIZE; y++) {
        if (bingoBoardSpaces[x][y].marked == false) {
          sumOfUnmarkedNumbers = sumOfUnmarkedNumbers + bingoBoardSpaces[x][y].number;
        }
      }
    }
    return sumOfUnmarkedNumbers * winningNumber;
  }

  private void printBingoCard() {
    for (int x = 0; x < BOARDSIZE; x++) {
      for (int y = 0; y < BOARDSIZE; y++) {
        System.out.print(bingoBoardSpaces[x][y].number);
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private void printMarkedBingoCard() {
    for (int x = 0; x < BOARDSIZE; x++) {
      for (int y = 0; y < BOARDSIZE; y++) {
        if (bingoBoardSpaces[x][y].marked == true)
          System.out.print(1);
        else
          System.out.print(0);
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }
}
