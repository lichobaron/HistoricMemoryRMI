
for x in range (0,36):
    f = open ('../../res/fuente/prueba'+str(x+1)+'.txt','w')
    for y in range (0,8):
        if y == 7 :
            f.write('prueba'+str(x+1)+'-'+str(y+1))
        else:
            f.write('prueba'+str(x+1)+'-'+str(y+1)+'\n')
    f.close
