current=-1
with open("temp.txt","w+") as write:
    with open("flightnumbers.csv","r") as file:
        with open("tempo.txt","r") as rules:
            for lines in rules:
                while (current<int(lines)):
                    current+=1
                    write.write(file.readline())
                file.readline()
                current+=1
                
                
