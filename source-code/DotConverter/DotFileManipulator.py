#!/bin/python

import re
import sys

if len(sys.argv) < 3:
    print("\nUSAGE : " + sys.argv[0] +
          " <input refactored DOT file> <output file name>")
    print ("\nNOTE: Input file to this is the output of reforcorDot.sh")
    exit(-1)
inputFile = sys.argv[1]
outputFile = sys.argv[2]
#  Read dot file and store it as a list
f = open(sys.argv[1])    # file name
data = [x.strip() for x in f.readlines()]
f.close()
contents = " ".join(data)

# System level APIs pattern
pattern = re.compile(r'(java|javax|com|org|sun|sunw|return|goto|->)')

#  Identify head and tail
# head = str(contents.split('{')[0]) + '{'
head = "digraph \"\" {"
# contents = " ".join(contents.split('{')[1:])
# tail = '}' + str(contents.split('}')[-1])
tail = "}"
# contents = contents.split('}')[:-1]
# contents = " ".join([x.strip() for x in contents])

#  parse each node to find if they are using system APIs
contents = contents.replace('\\\"', '\'')
finalContents = ""

# split based on ';'. Each line ending with a ';' is an edge
contents = contents.split(';')
for edge in contents:
    # print("-------> " + edge)
    if edge.strip() != "":
        nodes = edge.split('->')
        n1 = re.findall(pattern, nodes[0])
        n2 = re.findall(pattern, nodes[1])
        if len(n1) > 0 and len(n2) > 0:
            finalContents += edge + ";\n"
        elif len(n1) > 0:
            finalContents += nodes[0] + "->"
        elif len(n2) > 0:
            finalContents += nodes[1] + ";\n"
        else:
            continue

# Final Modified digraph with sysem APIs
print (head + "\n" + finalContents + tail)
# print("_________________________________________________________")
# print ("\n".join(data))
