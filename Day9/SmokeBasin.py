import math


def getProblemInput():
    file = open("Day9/_input.txt", "r")
    caveStringInput = file.read().split("\n")
    caveWidth = len(caveStringInput[0])
    caveHeight = len(caveStringInput)
    caveMap = []
    for y in range(caveHeight):
        caveMap.append([])
        for x in range(caveWidth):
            caveMap[y].append(int(caveStringInput[y][x]))
    return caveMap


def part1(caveMap):
    solve(caveMap)


def part2(caveMap):
    solve(caveMap, True)


def solve(caveMap, fillBasins=False):
    lowPointValue = 0
    basinValues = []

    caveWidthX = len(caveMap[0])
    caveHeightY = len(caveMap)

    for y in range(caveHeightY):
        for x in range(caveWidthX):
            if x > 0:
                if caveMap[y][x - 1] < caveMap[y][x] or caveMap[y][x - 1] == caveMap[y][x]:
                    continue
            if x < caveWidthX - 1:
                if caveMap[y][x + 1] < caveMap[y][x] or caveMap[y][x + 1] == caveMap[y][x]:
                    continue
            if y > 0:
                if caveMap[y - 1][x] < caveMap[y][x] or caveMap[y - 1][x] == caveMap[y][x]:
                    continue
            if y < caveHeightY - 1:
                if caveMap[y + 1][x] < caveMap[y][x] or caveMap[y + 1][x] == caveMap[y][x]:
                    continue
            
            if fillBasins:
              basinValues.append(floodFillBasins(caveMap=caveMap, startY=y, startX=x))
            else:
              lowPointValue += (caveMap[y][x] + 1)
    
    if fillBasins:
        basinValues.sort(reverse=True)
        print(basinValues[0] * basinValues[1] * basinValues[2])
    else:
      print(lowPointValue)


def floodFillBasins(caveMap, startY, startX):
    count = 0
    queue = []
    basinVisitHistory = []

    queue.append({
        "y": startY,
        "x": startX,
        "height": caveMap[startY][startX]
    })

    basinVisitHistory.append(queue[0])

    caveWidthX = len(caveMap[0])
    caveHeightY = len(caveMap)

    while len(queue) != 0:
        basinNode = queue.pop()

        if basinNode["x"] > 0:
            if caveMap[basinNode["y"]][basinNode["x"] - 1] != 9:
                if not any(node["x"] == basinNode["x"] - 1 and node["y"] == basinNode["y"] for node in basinVisitHistory):
                    queue.append({"y": basinNode["y"], "x": basinNode["x"] - 1, "height": caveMap[basinNode["y"]][basinNode["x"] - 1]})
                    basinVisitHistory.append({ "y": basinNode["y"], "x": basinNode["x"] - 1, "height": caveMap[basinNode["y"]][basinNode["x"] - 1]})

        if basinNode["x"] < caveWidthX - 1:
            if caveMap[basinNode["y"]][basinNode["x"] + 1] != 9:
              if not any(node["x"] == basinNode["x"] + 1 and node["y"] == basinNode["y"] for node in basinVisitHistory):
                queue.append({"y": basinNode["y"], "x": basinNode["x"] + 1, "height": caveMap[basinNode["y"]][basinNode["x"] + 1]})
                basinVisitHistory.append({"y": basinNode["y"], "x": basinNode["x"] + 1, "height": caveMap[basinNode["y"]][basinNode["x"] + 1]})

        if basinNode["y"] > 0:
            if caveMap[basinNode["y"] - 1][basinNode["x"]] != 9:
              if not any(node["x"] == basinNode["x"] and node["y"] == basinNode["y"] - 1 for node in basinVisitHistory):
                queue.append({"y": basinNode["y"] - 1, "x": basinNode["x"], "height": caveMap[basinNode["y"] - 1][basinNode["x"]]})
                basinVisitHistory.append({"y": basinNode["y"] - 1, "x": basinNode["x"], "height": caveMap[basinNode["y"] - 1][basinNode["x"]]})

        if basinNode["y"] < caveHeightY - 1:
            if caveMap[basinNode["y"] + 1][basinNode["x"]] != 9:
              if not any(node["x"] == basinNode["x"] and node["y"] == basinNode["y"] + 1 for node in basinVisitHistory):
                  queue.append({"y": basinNode["y"] + 1, "x": basinNode["x"], "height": caveMap[basinNode["y"] + 1][basinNode["x"]]})
                  basinVisitHistory.append({"y": basinNode["y"] + 1, "x": basinNode["x"], "height": caveMap[basinNode["y"] + 1][basinNode["x"]]})

        count += 1
    return count


part1(getProblemInput())
part2(getProblemInput())
