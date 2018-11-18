from random import randint
for x in range (0,60):
    f = open ('../../res/descriptores/descriptor'+str(x+1)+'.txt','w')
    for y in range (0,9):
        if y == 0 :
            f.write('test'+str(x+1)+'.txt'+'\n')
        elif y == 8:
            f.write('prueba'+str(randint(1,36))+'.txt')
        elif y % 2 == 0:
            f.write('test'+str(randint(1,36))+'.txt')
        else:
            f.write('prueba'+str(randint(1,36))+'.txt'+'\n')
    f.close