package Day4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GiantSquidSolution {

  private List<BingoBoard> bingoBoards;
  private List<BingoBoard> winningBingoBoards;
  private List<Integer> bingoNumbers;

  public GiantSquidSolution() {
    bingoBoards = new ArrayList<>();
    bingoNumbers = new ArrayList<>();
    winningBingoBoards = new ArrayList<>();
  }

  private void setProblemInput(String fileName) {
    bingoBoards.clear();
    bingoNumbers.clear();
    winningBingoBoards.clear();
    parseInputFileContent(readInputFile(fileName));
  }

  private List<String> readInputFile(String fileName) {
    List<String> input = new ArrayList<>();
    try {
      Path path = FileSystems.getDefault().getPath(fileName);
      input = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return input;
  }

  private void parseInputFileContent(List<String> input) {
    int row = 0;

    for (String x : input.get(0).split(",")) {
      bingoNumbers.add(Integer.parseInt(x));
    }

    for (int line = 1; line < input.size(); line++) {
      if (input.get(line).equals("")) {
        bingoBoards.add(new BingoBoard());
        row = 0;
      } else {
        var boardRow = input.get(line).trim().split("\\s+");
        for (int x = 0; x < boardRow.length; x++) {
          bingoBoards.get(bingoBoards.size() - 1).bingoBoardSpaces[row][x] =
              new BingoBoardSpace(Integer.parseInt(boardRow[x]));
        }
        row++;
      }
    }
  }

  private void markBingoBoards(int number) {
    for (BingoBoard bingoBoard : bingoBoards) {
      if (winningBingoBoards.contains(bingoBoard) == false) {
        for (int x = 0; x < BingoBoard.BOARDSIZE; x++) {
          for (int y = 0; y < BingoBoard.BOARDSIZE; y++) {
            if (bingoBoard.bingoBoardSpaces[x][y].number == number)
              bingoBoard.bingoBoardSpaces[x][y].marked = true;
          }
        }
      }
    }
  }

  private void setWinningBingoBoards(int number) {
    int rowStreak = 0;
    int colStreak = 0;

    for (BingoBoard bingoBoard : bingoBoards) {
      if (winningBingoBoards.contains(bingoBoard) == false) {
        for (int x = 0; x < BingoBoard.BOARDSIZE; x++) {
          for (int y = 0; y < BingoBoard.BOARDSIZE; y++) {
            if (bingoBoard.bingoBoardSpaces[x][y].marked == true) {
              rowStreak++;
            }
            if (bingoBoard.bingoBoardSpaces[y][x].marked == true) {
              colStreak++;
            }
          }
          if (rowStreak == BingoBoard.BOARDSIZE || colStreak == BingoBoard.BOARDSIZE) {
            bingoBoard.winningNumber = number;
            winningBingoBoards.add(bingoBoard);
          }
          rowStreak = 0;
          colStreak = 0;
        }
      }
    }
  }

  private void solve(boolean waitForLastWinningBoard) {
    int winningBoards = 0;

    for (int bingoNumber : bingoNumbers) {
      markBingoBoards(bingoNumber);
      setWinningBingoBoards(bingoNumber);
      if (waitForLastWinningBoard == false && winningBoards >= 1)
        break;
      else if (waitForLastWinningBoard == true && winningBoards == bingoBoards.size())
        break;
    }
    if (waitForLastWinningBoard)
      winningBingoBoards.get(winningBingoBoards.size() - 1).printDetails();
    else
      winningBingoBoards.get(0).printDetails();
  }

  public void problem1() {
    setProblemInput("Day4/_input.txt");
    solve(false);
  }

  public void problem2() {
    setProblemInput("Day4/_input.txt");
    solve(true);
  }
}
