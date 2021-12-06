def getProblemInput():
    file = open("Day6/_example.txt", "r")
    currentFishPopulation = file.read().split(",")
    return list(map(int, currentFishPopulation))

def part1(currentFishPopulation, numberOfIterations):
    newFishPopulation = []
    for x in range(numberOfIterations):
        for y in range(len(currentFishPopulation)):
            if currentFishPopulation[y] == 0:
                newFishPopulation.append(8)
                currentFishPopulation[y] = 6
            else:
                currentFishPopulation[y] = currentFishPopulation[y] - 1
        currentFishPopulation.extend(newFishPopulation)
        newFishPopulation.clear()
    print(len(currentFishPopulation))

def part2(currentFishPopulation, numberOfIterations):
    birthRates = [0] * 9
    for number in currentFishPopulation:
        birthRates[number] = birthRates[number] + 1

    for x in range(numberOfIterations):
        value = birthRates[0]
        for y in (range(len(birthRates) - 1)):
            birthRates[y] = birthRates[y + 1]
        birthRates[-1] = value
        birthRates[6] = birthRates[6] + birthRates[8]
    print(sum(birthRates))

part1(getProblemInput(), 18)
part2(getProblemInput(), 256)
