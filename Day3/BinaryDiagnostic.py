def part1():
    file = open("Day3/_input.txt", "r")
    problemInput = file.read().split('\n')
    zeroCountList = [0] * len(problemInput[0])
    oneCountList = [0] * len(problemInput[0])
    finalBitGamma = ""
    finalBitEpsilon = ""

    for binaryString in problemInput:
        for x, binaryCharacter in enumerate(binaryString):
            if binaryCharacter == '0':
                zeroCountList[x] = zeroCountList[x] + 1
            elif binaryCharacter == '1':
                oneCountList[x] = oneCountList[x] + 1

    for x in range(len(zeroCountList)):
        if zeroCountList[x] > oneCountList[x]:
            finalBitGamma = finalBitGamma + "0"
            finalBitEpsilon = finalBitEpsilon + "1"
        elif zeroCountList[x] < oneCountList[x]:
            finalBitGamma = finalBitGamma + "1"
            finalBitEpsilon = finalBitEpsilon + "0"
    print(int(finalBitGamma, 2) * int(finalBitEpsilon, 2))

def part2():
    file = open("Day3/_input.txt", "r")
    problemInput = file.read().split('\n')

    oxygenGeneratorInput = []
    oxygenGeneratorInput.extend(problemInput)
    nextOxygenGeneratorInput = []
    zeroCountList = [0] * len(problemInput[0])
    oneCountList = [0] * len(problemInput[0])

    for x in range(len(zeroCountList)):
        for binaryString in oxygenGeneratorInput:
            if binaryString[x] == '0':
                zeroCountList[x] = zeroCountList[x] + 1
            elif binaryString[x] == '1':
                oneCountList[x] = oneCountList[x] + 1
        if zeroCountList[x] > oneCountList[x]:
            for binaryString in oxygenGeneratorInput:
                if binaryString[x] == "0":
                    nextOxygenGeneratorInput.append(binaryString)
        elif zeroCountList[x] < oneCountList[x] or zeroCountList[x] == oneCountList[x]:
            for binaryString in oxygenGeneratorInput:
                if binaryString[x] == "1":
                    nextOxygenGeneratorInput.append(binaryString)
        oxygenGeneratorInput.clear()
        oxygenGeneratorInput.extend(nextOxygenGeneratorInput)
        nextOxygenGeneratorInput.clear()
        if len(oxygenGeneratorInput) == 1:
            break

    zeroCountList.clear()
    oneCountList.clear()
    zeroCountList = [0] * len(problemInput[0])
    oneCountList = [0] * len(problemInput[0])

    C02ScrubberInput = []
    C02ScrubberInput.extend(problemInput)
    nextC02ScrubberInput = []

    for x in range(len(zeroCountList)):
        for binaryString in C02ScrubberInput:
            if binaryString[x] == '0':
                zeroCountList[x] = zeroCountList[x] + 1
            elif binaryString[x] == '1':
                oneCountList[x] = oneCountList[x] + 1
        if zeroCountList[x] < oneCountList[x] or zeroCountList[x] == oneCountList[x]:
            for binaryString in C02ScrubberInput:
                if binaryString[x] == "0":
                    nextC02ScrubberInput.append(binaryString)
        elif zeroCountList[x] > oneCountList[x]:
            for binaryString in C02ScrubberInput:
                if binaryString[x] == "1":
                    nextC02ScrubberInput.append(binaryString)
        C02ScrubberInput.clear()
        C02ScrubberInput.extend(nextC02ScrubberInput)
        nextC02ScrubberInput.clear()
        if len(C02ScrubberInput) == 1:
            break
    print(int(oxygenGeneratorInput[0], 2) * int(C02ScrubberInput[0], 2))

part1()
part2()
