#!/usr/bin/env python

import sys
import getopt

class Usage(Exception):
    def __init__(self, msg):
        self.msg = msg

def main(argv=None):
    if argv is None:
        argv = sys.argv
    try:
        try:
            opts, args = getopt.getopt(argv[1:], "h", ["help"])
        except getopt.error, msg:
             raise Usage(msg)
        if len(args) != 1: raise Usage("Must be called with exactly 1 argument.")
        run(args[0])
    except Usage, err:
        print >>sys.stderr, err.msg
        print >>sys.stderr, "for help use --help"
        return 2


def run(fn):
        o = check(fn)
        for x in o:
                print x

def check(fn):
	with open(fn,'r') as f:
		r = f.readlines()
	r = [[int(x) for x in l.split()] for l in r]
	out = [sum(x) for x in zip(*r)[1:]]
	return out

if __name__ == "__main__":
        main()
