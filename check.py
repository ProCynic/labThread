def check(fn):
	with open(fn,'r') as f:
		r = f.readlines()
	r = [[int(x) for x in l.split()] for l in r]
	out = [sum(x) for x in zip(*r)[1:]]
	return out

#o = check('data2')
#for x in o:
#	print x
