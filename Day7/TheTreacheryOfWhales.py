import math


def getProblemInput():
    file = open("Day7/_input.txt", "r")
    crabPositions = file.read().split(",")
    return list(map(int, crabPositions))


def part1(crabPositions):
    print(solve(crabPositions, False))


def part2(crabPositions):
    print(solve(crabPositions, True))


def calculateFuelCost(num1, num2):
    difference = abs(num1 - num2)
    return 0.5 * difference ** 2 + 0.5 * difference


def solve(crabPositions, useNonLinearFuelCosts):
    numberOfPotentialCrabPositions = max(crabPositions) + 1
    min = math.inf
    for x in range(numberOfPotentialCrabPositions):
        value = 0
        for crabPos in crabPositions:
            if useNonLinearFuelCosts:
                value += calculateFuelCost(crabPos, x)
            else:
                value += abs(crabPos - x)
        if(value < min):
            min = value
    return (min)


part1(getProblemInput())
part2(getProblemInput())
