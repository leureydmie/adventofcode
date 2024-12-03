filename = "input1.txt"

# ETAPE 1 - Ouvrir le fichier et charger les données
leftList = []
rightList = []
file = open(filename, 'r')

for line in file:
        s = line.split("   ")
        leftList.append(int(s[0]))
        rightList.append(int(s[1]))

# ETAPE 2 : Trier les listes
sortedLeftList = sorted(leftList)
sortedRightList = sorted(rightList)

# ETAPE 3 - Calculer les scores de similarité
similarityScores = []
for left in sortedLeftList:
    similarityScore = left * sortedRightList.count(left)
    similarityScores.append(similarityScore)

# ETAPE 4 - Sommer les scores de similarité et afficher le résultat
print(sum(similarityScores))