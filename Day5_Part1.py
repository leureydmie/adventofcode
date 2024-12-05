f = open("input5.txt", 'r')

orderDict = dict()
result = 0

def updateOrderDict(line):
    pages = line.split('|')
    firstPage = int(pages[0])
    secondPage = int(pages[1])

    if(firstPage not in orderDict.keys()):
        orderDict[firstPage] = [secondPage]
    else:
        correspondingPages = orderDict[firstPage]
        correspondingPages.append(secondPage)
        orderDict[firstPage] = correspondingPages

def isUpdateInOrder(update):
    for i in range(len(update)):
        currentPage = update[i]

        if(currentPage not in orderDict.keys()):
            return i == (len(update) - 1)

        pagesAfterCurrentPage = orderDict[currentPage]

        # On teste que toutes les pages après sont bien situées dans une règle
        for j in range(i+1, len(update)):
            pageToTest = update[j]
            if(pageToTest not in pagesAfterCurrentPage):
                return False

    return True


for line in f:
    if '|' in line:
        updateOrderDict(line)
        continue
    update = line.split(',')
    if(len(update) < 2):
        continue
    update = list(map(int, update))

    if(isUpdateInOrder(update)):
        middleIndex = round((len(update)-1)/2)
        result += update[middleIndex]

print("--> Le résultat final vaut : " + str(result))