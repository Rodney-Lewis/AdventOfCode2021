package Day2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DiveSolution {

  List<String> input;
  List<String> commands = new ArrayList<>();
  List<Integer> units = new ArrayList<>();

  private void readFileInput(String fileName) {
    try {
      Path path = FileSystems.getDefault().getPath(fileName);
      input = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    parseFileInput();
  }

  private void parseFileInput() {
    commands.clear();
    units.clear();
    for (String line : input) {
      commands.add(line.split(" ")[0]);
      units.add(Integer.parseInt(line.split(" ")[1]));
    }
  }

  void problem1() {
    int depth = 0;
    int horizontalPosition = 0;

    readFileInput("Day2/_input.txt");
    for (int i = 0; i < commands.size(); i++) {
      switch (commands.get(i)) {
        case "forward":
          horizontalPosition = horizontalPosition + units.get(i);
          break;
        case "down":
          depth = depth + units.get(i);
          break;
        case "up":
          depth = depth - units.get(i);
          break;
      }
    }
    System.out.println(String.format("Part 1 depth X horizontal pos: %d", (depth * horizontalPosition)));
  }

  void problem2() {
    int depth = 0;
    int horizontalPosition = 0;
    int aim = 0;

    readFileInput("Day2/_input.txt");
    for (int i = 0; i < commands.size(); i++) {
      switch (commands.get(i)) {
        case "forward":
          depth = depth + (aim * units.get(i));
          horizontalPosition = horizontalPosition + units.get(i);
          break;
        case "down":
          aim = aim + units.get(i);
          break;
        case "up":
          aim = aim - units.get(i);
          break;
      }
    }
    System.out.println(String.format("Part 2 depth X horizontal pos: %d", (depth * horizontalPosition)));
  }
}
