package Day1;

import java.io.IOException;
import java.util.List;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

class SonarSweepSolution {

  List<String> input;

  void readFileInput(String fileName) {
    try {
      Path path = FileSystems.getDefault().getPath(fileName);
      input = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void problem1() {
    int counter = 0;
    readFileInput("Day1/input.txt");

    for (int i = 1; i < input.size(); i++) {
      if (Integer.parseInt(input.get(i - 1)) < Integer.parseInt(input.get(i))) {
        counter++;
      }
    }
    System.out.println(String.format("Part 1 increases: %d", counter));
  }

  void problem2() {
    int counter = 0;
    int current = 0;
    int previous = 0;
    readFileInput("Day1/input.txt");

    for (int i = 1; i < input.size() - 2; i++) {
      previous = Integer.parseInt(input.get(i - 1)) + Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(i + 1));
      current = Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(i + 1)) + Integer.parseInt(input.get(i + 2));
      if (previous < current) {
        counter++;
      }
    }
    System.out.println(String.format("Part 2 increases: %d", counter));
  }
}
