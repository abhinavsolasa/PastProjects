/**
  */
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/** Width of each line of text in the box. */
#define LINE_WIDTH 60

/** Symbol used to draw the border around the box. */
#define BORDER '*'

/**
 * reads in the file input and outputs the lines inside the box
 * @return true if a line is outputted and false if no line is to be outputted
  */
bool paddedLine()
{
	//char c[255];
	//FILE *fp;
    	//fp = fopen("input-t1.txt", "%s");
 	//fscanf(fp, "%s", c);
    	//printf("Input file: %s", c);
	//scanf("%s", c);
	int charCount = 0;
	char x;
	putchar(BORDER);
	while((x = getchar()) != '\n' && x != EOF) {
		if(charCount < LINE_WIDTH) {
			putchar(x);
			charCount++;
		}
		/**
		if(finished){
			putchar(' ');
		}
		else {
			char x;
			x = c[i];
			if(x == '\n'){
				putchar(' ');
				finished = true;
			}
			else{
				putchar(x);
			}
		}
		*/

	}
	
	if(charCount == 0 && x == EOF)
	{
		return false;
	}
	
	if(charCount < LINE_WIDTH){
		for(int i = 1; i <= LINE_WIDTH - charCount; i++){
			putchar(' ');
		}
	}	
	
	putchar(BORDER);
	printf("\n");
	
	return true;
}

/**
* outputs the top and bottom border of asterisks
* @param ch --> the character used in the border
* @param count --> the # of copies of the character in the border 
*/
void lineOfChars( char ch, int count )
{
	for(int i = 0; i < count; i++){
		printf("%c", ch);
	}
	printf("\n");
}

/**
*Lines are read in from a file and the line is outputted in a padded box
  */
int main()
{	
	lineOfChars(BORDER, LINE_WIDTH + 2);
	while(paddedLine());
	lineOfChars(BORDER, LINE_WIDTH + 1);
	return 0;
}
