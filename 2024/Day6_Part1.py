class Maptrix:
    def __init__(self, pmap):
        self._map = pmap
        self._h = len(pmap[0])
        self._v = len(pmap)

    def isInMap(self, point):
        i = point[0]
        j = point[1]
        return 0 <= i < self._h-1 and 0 <= j < self._v

    def isObstacle(self, point):
        i = point[0]
        j = point[1]
        return self._map[i][j] == '#'

    def markVisited(self, point):
        i = point[0]
        j = point[1]
        self._map[i][j] = 'X'

    def countVisited(self):
        result = 0
        for line in self._map:
            result += line.count('X')
        return result

class Guard:
    def __init__(self, x, y, dir):
        self._x = x
        self._y = y
        self._dir = dir

    def turnRight(self):
        if self._dir == '^':
            self._dir = '>'
        elif self._dir == '>':
            self._dir = 'v'
        elif self._dir == 'v':
            self._dir = '<'
        elif self._dir == '<':
            self._dir = '^'

    def move(self):
        if self._dir == '^':
            self._x -= 1
        elif self._dir == 'v':
            self._x += 1
        elif self._dir == '>':
            self._y += 1
        elif self._dir == '<':
            self._y -= 1

    def getCurrentPos(self):
        return [self._x, self._y]

    def getNextPos(self):
        if self._dir == '^':
            return [self._x - 1, self._y]
        elif self._dir == 'v':
            return [self._x + 1, self._y]
        elif self._dir == '>':
            return [self._x, self._y + 1]
        elif self._dir == '<':
            return [self._x, self._y - 1]


f = open("input6.txt", 'r')
pmap = []
i = 0
guard = None

# ETAPE 1 - Initialisation des données
for line in f:
    line = [*line]
    if '^' in line:
        j = line.index('^')
        guard = Guard(i, j, '^')
    pmap.append(line)
    i += 1
maptrix = Maptrix(pmap)

# ETAPE 2 - Simulation
while maptrix.isInMap(guard.getCurrentPos()):
    currentPos = guard.getCurrentPos()
    maptrix.markVisited(currentPos)
    nextPos = guard.getNextPos()
    if not maptrix.isInMap(nextPos):
        break
    if maptrix.isObstacle(nextPos):
        guard.turnRight()
    else:
        guard.move()

print(maptrix.countVisited())