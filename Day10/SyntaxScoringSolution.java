package Day10;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class SyntaxScoringSolution {

  private List<String> getProblemInput(String fileName) {
    List<String> input = new ArrayList<>();
    try {
      Path path = FileSystems.getDefault().getPath(fileName);
      input = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return input;
  }

  private Long solve(List<String> lines, Boolean completeLines) {
    Long value = 0L;
    Bracket bracket;
    List<Long> incompleteValues = new ArrayList<>();
    Deque<Bracket> chunks = new ArrayDeque<>();
    List<Bracket> brackets = new ArrayList<>();
    boolean incompleteLine;
    brackets.add(new Bracket('(', ')', 3, 1));
    brackets.add(new Bracket('[', ']', 57, 2));
    brackets.add(new Bracket('{', '}', 1197, 3));
    brackets.add(new Bracket('<', '>', 25137, 4));

    for (String line : lines) {
      newline: for (int x = 0; x < line.length(); x++)
        for (int y = 0; y < brackets.size(); y++)
          if (brackets.get(y).getPushCharacter() == line.charAt(x))
            chunks.push(brackets.get(y));
          else if (brackets.get(y).getPopCharacter() == line.charAt(x)) {
            if (chunks.peek().getPushCharacter() == brackets.get(y).getPushCharacter())
              chunks.pop();
            else {
              if (!completeLines)
                value += brackets.get(y).getCorruptedValue();
              chunks.clear();
              break newline;
            }
          }
      if (completeLines) {
        incompleteLine = false;
        while (!chunks.isEmpty() && completeLines) {
          incompleteLine = true;
          bracket = chunks.pop();
          value = value * 5;
          value = value + bracket.getAutoCompleteValue();
        }
        if (incompleteLine) {
          incompleteValues.add(value);
          value = 0L;
        }
      }
    }
    if (!completeLines)
      return value;
    else {
      incompleteValues.get(incompleteValues.size() / 2);
      Collections.sort(incompleteValues);
      return incompleteValues.get(incompleteValues.size() / 2);
    }
  }

  public void problem1() {
    System.out.println("Day 10, Part 1:");
    System.out.println(solve(getProblemInput("Day10/_input.txt"), false));
  }

  public void problem2() {
    System.out.println("Day 10, Part 2:");
    System.out.println(solve(getProblemInput("Day10/_input.txt"), true));
  }
}
