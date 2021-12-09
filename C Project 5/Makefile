cc = gcc

all: deflate inflate

deflate: deflate.o buffer.o dictionary.o
	gcc deflate.o buffer.o dictionary.o -o deflate -lm
	
inflate: inflate.o buffer.o dictionary.o
	gcc inflate.o buffer.o dictionary.o -o inflate -lm

deflate.o: deflate.c buffer.h dictionary.h
	gcc -Wall -std=c99 -g -c deflate.c -o deflate.o 

inflate.o: inflate.c buffer.h dictionary.h
	gcc -Wall -std=c99 -g -c inflate.c -o inflate.o 

buffer.o: buffer.c buffer.h 
	gcc -Wall -std=c99 -g -c buffer.c -o buffer.o 
 
dictionary.o: dictionary.c dictionary.h
	gcc -Wall -std=c99 -D_GNU_SOURCE -g -c dictionary.c -o dictionary.o

clean:
	rm -f *.0
	rm -f deflate inflate