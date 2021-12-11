from math import sin


def getProblemInput():
    file = open("Day8/_input.txt", "r")
    return file.read().split(",")[0].split("\n")


def part1(wireInputs):
    count = 0

    for wireInput in wireInputs:
        signalPatternsToDecode = wireInput.split("|")[1].split(" ")

        for signalPattern in signalPatternsToDecode:
            if len(signalPattern) == 2:
                count += 1
            elif len(signalPattern) == 3:
                count += 1
            elif len(signalPattern) == 4:
                count += 1
            elif len(signalPattern) == 7:
                count += 1
    print(count)


def part2(wireInputs):
    codes = [None] * 10
    for wireInput in wireInputs:
        uniqueSingalPatterns = wireInput.split("|")[0].strip().split(" ")
        signalPatternsToDecode = wireInput.split("|")[1].strip().split(" ")

        for signalPattern in uniqueSingalPatterns:
            if len(signalPattern) == 2:
                codes[1] = set(list(signalPattern))
            elif len(signalPattern) == 3:
                codes[7] = set(list(signalPattern))
            elif len(signalPattern) == 4:
                codes[4] = set(list(signalPattern))
            elif len(signalPattern) == 7:
                codes[8] = set(list(signalPattern))
        ag = set.intersection(uniqueSingalPatterns)
        print(ag)


part1(getProblemInput())
part2(getProblemInput())
