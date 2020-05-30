
with open("flightnumbers.csv","r") as input:
	with open("tempo.txt","w+") as output:
		for el in input:
			b=True
			with open("temp.txt","r") as rules:
				for rule in rules:
					if rule.strip() in el:
						b=False
						break
			if b:
				output.write(el)