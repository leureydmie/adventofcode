f = open("input4.txt", 'r')
XMAS_WORD = "XMAS"

result = 0

wordgrid = []
v_dim = 0
h_dim = 0

# ETAPE 1 - Remplir la grille et parcours ligne par ligne
for line in f:
    result += line.count("XMAS")
    result += line.count("SAMX")
    wordgrid.append(line)
    v_dim += 1

h_dim = len(wordgrid[0])

# ETAPE 2 - Parcours colonne par colonne
for i in range(len(wordgrid)):
    column = ""
    for line in wordgrid:
        column += line[i]
    result += column.count("XMAS")
    result += column.count("SAMX")

# ETAPE 3 - Parcours en diagonale et diagonale opposée
def getDiagonal(i, j):
    result = ""
    c_i = i
    c_j = j
    while(c_i < v_dim and c_j < h_dim-1):
        result += (wordgrid[c_i])[c_j]
        c_i += 1
        c_j += 1
    print("--> Diagonale normale : colonne : " + str(i + 1) + " ligne : " + str(j + 1) + " valeur : " + result)
    return result

def getOppositeDiagonal(i,j):
    result = ""
    c_i = i
    c_j = j
    while(c_i < v_dim and c_j >= 0):
        result += (wordgrid[c_i])[c_j]
        c_i += 1
        c_j -= 1
    print("--> Diagonale opposée : colonne : " + str(i+1) + " ligne : " + str(j+1) + " valeur : " + result)
    return result

for i in range(1,v_dim):
    diagonal = getDiagonal(i, 0)
    oppositeDiagonal = getOppositeDiagonal(i, h_dim-2)
    result += diagonal.count("XMAS")
    result += diagonal.count("SAMX")
    result += oppositeDiagonal.count("XMAS")
    result += oppositeDiagonal.count("SAMX")

for j in range(h_dim-1):
    diagonal = getDiagonal(0, j)
    oppositeDiagonal = getOppositeDiagonal(0, j)
    result += diagonal.count("XMAS")
    result += diagonal.count("SAMX")
    result += oppositeDiagonal.count("XMAS")
    result += oppositeDiagonal.count("SAMX")

print("La solution vaut : " + str(result))