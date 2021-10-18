all: match

match: match.o list.o pattern.o input.o
	gcc match.o list.o pattern.o input.o -o match

input.o: input.c input.h
	gcc -Wall -std=c99 -c input.c -o input.o

list.o: list.c list.h
		gcc -Wall -std=c99 -c list.c -o list.o

pattern.o: pattern.c pattern.h
		gcc -Wall -std=c99 -c pattern.c -o pattern.o

match.o: match.c input.h pattern.h list.h
	gcc -Wall -std=c99 -c match.c -o match.o

clean:
	rm -f match.o pattern.o list.o input.o
	rm -f match