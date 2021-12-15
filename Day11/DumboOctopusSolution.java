package Day11;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DumboOctopusSolution {

  private final int MAP_SIZE = 10;

  private Octopus[][] getProblemInput(String fileName) {
    Octopus[][] octopusMap = new Octopus[MAP_SIZE][MAP_SIZE];
    List<String> input = new ArrayList<>();
    try {
      Path path = FileSystems.getDefault().getPath(fileName);
      input = Files.readAllLines(path);
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int y = 0; y < input.size(); y++) {
      for (int x = 0; x < input.get(y).length(); x++) {
        octopusMap[y][x] = new Octopus(input.get(y).charAt(x) - '0');
      }
    }
    return octopusMap;
  }

  private int solve(Octopus[][] octopusMap, int iterations) {
    int count = 0;
    boolean stepComplete = false;
    Octopus[][] beginStepMap = new Octopus[MAP_SIZE][MAP_SIZE];

    for (int k = 0; k < iterations; k++) {
      stepComplete = false;

      for (int i = 0; i < octopusMap.length; i++) {
        for (int j = 0; j < octopusMap[i].length; j++) {
          octopusMap[i][j].value++;
          octopusMap[i][j].flashed = false;
        }
      }

      while (!stepComplete) {
        for (int i = 0; i < octopusMap.length; i++) {
          for (int j = 0; j < octopusMap[i].length; j++) {
            beginStepMap[i][j] = octopusMap[i][j].copy();
          }
        }

        for (int i = 0; i < octopusMap.length; i++) {
          for (int j = 0; j < octopusMap[i].length; j++) {
            if (octopusMap[i][j].value > 9 && octopusMap[i][j].flashed == false) {
              octopusMap[i][j].value = 0;
              octopusMap[i][j].flashed = true;
              count++;
              for (int x = -1; x <= 1; x++)
                for (int y = -1; y <= 1; y++)
                  if (i + x >= 0 && i + x < MAP_SIZE && j + y >= 0 && j + y < MAP_SIZE
                      && (j + y != j || i + x != i))
                    if (octopusMap[i + x][j + y].flashed == false)
                      octopusMap[i + x][j + y].value++;
            }
          }
        }
        stepComplete = hasMapChangedSinceLastStep(beginStepMap, octopusMap);
      }
    }
    return count;
  }

  private int solve(Octopus[][] octopusMap) {
    int iteration = 0;
    boolean stepComplete = false;
    Octopus[][] beginStepMap = new Octopus[MAP_SIZE][MAP_SIZE];

    while (true) {
      stepComplete = false;
      iteration++;

      for (int i = 0; i < octopusMap.length; i++) {
        for (int j = 0; j < octopusMap[i].length; j++) {
          octopusMap[i][j].value++;
          octopusMap[i][j].flashed = false;
        }
      }

      while (!stepComplete) {
        for (int i = 0; i < octopusMap.length; i++) {
          for (int j = 0; j < octopusMap[i].length; j++) {
            beginStepMap[i][j] = octopusMap[i][j].copy();
          }
        }

        for (int i = 0; i < octopusMap.length; i++) {
          for (int j = 0; j < octopusMap[i].length; j++) {
            if (octopusMap[i][j].value > 9 && octopusMap[i][j].flashed == false) {
              octopusMap[i][j].value = 0;
              octopusMap[i][j].flashed = true;
              if (allFlash(octopusMap))
                return iteration;
              for (int x = -1; x <= 1; x++)
                for (int y = -1; y <= 1; y++)
                  if (i + x >= 0 && i + x < MAP_SIZE && j + y >= 0 && j + y < MAP_SIZE
                      && (j + y != j || i + x != i))
                    if (octopusMap[i + x][j + y].flashed == false)
                      octopusMap[i + x][j + y].value++;
            }
          }
        }
        stepComplete = hasMapChangedSinceLastStep(beginStepMap, octopusMap);
      }
    }
  }

  private boolean allFlash(Octopus[][] map1) {
    for (int i = 0; i < map1.length; i++) {
      for (int j = 0; j < map1[i].length; j++) {
        if (map1[i][j].value != 0)
          return false;
      }
    }
    return true;
  }

  private boolean hasMapChangedSinceLastStep(Octopus[][] map1, Octopus[][] map2) {
    for (int i = 0; i < map1.length; i++) {
      for (int j = 0; j < map1[i].length; j++) {
        if (map1[i][j].value != map2[i][j].value)
          return false;
      }
    }
    return true;
  }

  public void problem1() {
    System.out.println("Day 11, Part 1:");
    System.out.println(solve(getProblemInput("Day11/_input.txt"), 100));
  }

  public void problem2() {
    System.out.println("Day 11, Part 2:");
    System.out.println(solve(getProblemInput("Day11/_input.txt")));
  }
}
