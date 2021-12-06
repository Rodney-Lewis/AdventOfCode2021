package Day5;

import java.util.ArrayList;
import java.util.List;

public class Line {
  List<Point> points;

  Line(Point p1, Point p2) {
    points = new ArrayList<>();
    DrawLine(p1, p2, false);
  }

  Line(Point p1, Point p2, boolean allowDiagonalLines) {
    points = new ArrayList<>();
    DrawLine(p1, p2, allowDiagonalLines);
  }

  private void DrawLine(Point p1, Point p2, boolean drawDiagonalLines) {
    if (p1.x == p2.x) {
      var differenceInY = Math.abs(p1.y - p2.y);
      points.add(p1);
      if (p1.y > p2.y) {
        for (int i = 1; i < differenceInY; i++) {
          points.add(new Point(p1.x, p1.y - i));
        }
      } else if (p1.y < p2.y) {
        for (int i = 1; i < differenceInY; i++) {
          points.add(new Point(p1.x, p1.y + i));
        }
      }
      points.add(p2);
    } else if (p1.y == p2.y) {
      var differenceInX = Math.abs(p1.x - p2.x);
      points.add(p1);
      if (p1.x > p2.x) {
        for (int i = 1; i < differenceInX; i++) {
          points.add(new Point(p1.x - i, p1.y));
        }
      } else if (p1.x < p2.x) {
        for (int i = 1; i < differenceInX; i++) {
          points.add(new Point(p1.x + i, p1.y));
        }
      }
      points.add(p2);
    } else {
      if (drawDiagonalLines) {
        int x = p1.x;
        int y = p1.y;

        while (x != p2.x && y != p2.y) {
          points.add(new Point(x, y));
          if (x < p2.x) {
            x++;
          } else if (x > p2.x) {
            x--;
          }
          if (y < p2.y) {
            y++;
          } else if (y > p2.y) {
            y--;
          }
        }
        points.add(p2);
      }
    }
  }
}
