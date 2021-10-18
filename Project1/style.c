/**
 * @file style.c
 * @author Abhinav Solasa asolasa
 * This program generates a completely randomized paragraph
   with a specified number of lines. Every line has a set of
   words with randomized characters and word lengths.
 */
#include <stdio.h>
#include <stdlib.h>

#define SEVENTY 72



/**
 * prints a word with a specified length of
 * randomized lowercase letters
 * preconditions: x >= 0
 * @param x --> length of the word
 */
void printWord(int x)
{
    for ( int i = 0; i < x; i++ )
    {
            // Print a random lower-case letter.
            printf( "%c", 97 + rand() % 26 );
    }
}

/**
 * prints a line of words up to a specified length full of
 randomized letters
 * @return returns the number of words in the line
 */
int printLine() 
{
	int count = 0, pos = 0, space = 0;
	int len = 1 + rand() % 10;
	// Print a line of words up to a limited length.
	while ( pos + len + space < SEVENTY ) {
		if ( space > 0 ) {
			printf(" ");
                }
		printWord(len);
		pos += len + space;
		len = 1 + rand() % 10;
		space = 1;
		count += 1;
	}
	printf("\n");
	return count;
}

/**
 * this method uses the print line method to count the total
 # of words in the paragraph full of randomized characters.
 * preconditions: n is positive
 * @param n is the number of randomized lines to be
 printed in the paragraph
 * @return the total number of words in the paragraph
 */
int printParagraph(int n)
{
	int total = 0;
	for ( int i = 0; i < n; i++ ){
		total += printLine();
	}

	return total;
}

/**
 * prints a paragraph of 10 lines. Each line has randomized characters and words.
*/
int main()
{
	int w = printParagraph(10);
	printf("Words: %d\n",w);
	return 0;
}
