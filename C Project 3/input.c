/**
*This source file is responsible for reading in lines from a specified file. 
* It checks the length of every line and if an input line is larger than 1023 
characters, the program will exit. 
*@file input.c
*@author asolasa
*/
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "input.h"

/**
*responsible for reading in the lines from the input file
*@param fp --> the input file
*@param line[] --> the line that is to be evaluated
*@param capacity --> max capacity that an input line can have
*@return --> true if a line was read in and false if there are no more lines to read
*/
bool readLine(FILE *fp, char line[], int capacity ){
    //if(fscanf(fp, "%[^\n]", line) == 1)
    if(fgets(line, 5000, fp)){
        //printf("Got a line\n");
        if(strlen(line) > capacity){
            fprintf(stderr, "Line too long\n");
            exit(1);
        }
        return true;
    }
    return false;
}