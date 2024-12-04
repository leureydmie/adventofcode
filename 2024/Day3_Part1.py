f = open("input3.txt", 'r')

def parseLine(line):
    result = 0
    list = line.split('mul')
    for item in list:
        if(item[0] != '('): continue
        if(')' not in item): continue
        index = item.find(')')
        operands = item[1:index]
        if(',' not in operands): continue
        listOperands = operands.split(',')
        try:
            result += int(listOperands[0]) * int(listOperands[1])
        except:
            continue
    return result

result = 0
for line in f:
    result += parseLine(line)

print(result)