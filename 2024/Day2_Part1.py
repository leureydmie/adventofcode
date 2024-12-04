f = open("input2.txt", 'r')
result = 0

def isReportSafe(report):
    refDiff = report[0] - report[1]
    if(refDiff == 0): return False
    refSign = (refDiff) / abs(refDiff)
    for i in range(len(report)-1):
        diff = report[i] - report[i+1]
        if(diff == 0): return False
        if(abs(diff) > 3): return False
        if(diff/abs(diff) != refSign): return False
    return True

for line in f:
    report = []
    for number in line.split(' '):
        report.append(int(number))
    if(isReportSafe(report)):
        result += 1

print(result)