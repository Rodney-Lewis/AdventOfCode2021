package Day5;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HydrothermalVentsSolution {

  HydrothermalVentsSolution() {}

  private List<Line> getProblemInput(String fileName, boolean allowDiagonalLines) {
    return parseInputFileContent(readInputFile(fileName), allowDiagonalLines);
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

  private List<Line> parseInputFileContent(List<String> input, boolean allowDiagonalLines) {
    String[] pointFromString = new String[2];
    List<Line> lines = new ArrayList<>();
    Point p1;
    Point p2;

    for (String string : input) {
      pointFromString = string.split((" -> "))[0].split(",");
      p1 = new Point(Integer.parseInt(pointFromString[0]), Integer.parseInt(pointFromString[1]));

      pointFromString = string.split((" -> "))[1].split(",");
      p2 = new Point(Integer.parseInt(pointFromString[0]), Integer.parseInt(pointFromString[1]));

      lines.add(new Line(p1, p2, allowDiagonalLines));
    }
    return lines;
  }

  private int solve(List<Line> lines) {
    int index = 0;
    int intersections = 0;
    List<Point> ventPoints = new ArrayList<>();

    for (Line line : lines) {
      for (Point point : line.points) {
        index = ventPoints.indexOf(point);
        if (index != -1) {
          ventPoints.get(index).value++;
        } else {
          ventPoints.add(point);
        }
      }
    }

    for (Point point : ventPoints) {
      if (point.value > 1) {
        intersections++;
      }
    }
    return intersections;
  }

  public void problem1() {
    System.out.println("Day 5, Part 1:");
    System.out.println(solve(getProblemInput("Day5/_input.txt", false)));
  }

  public void problem2() {
    System.out.println("Day 5, Part 2:");
    System.out.println(solve(getProblemInput("Day5/_input.txt", true)));
  }
}
