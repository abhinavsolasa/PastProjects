/**
*This is where top component of the pattern matching occurs.
*The list of lines is read in from a file and matching lines
to the pattern are printed out.
* @file match.c
* @author asolasa
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "input.h"
#include "pattern.h"
#include "list.h"
#define MAX_INPUT_LENGTH 1023

int main(int argc, char *argv[]){
    bool numFlag = false;
    bool inversion = false;
    if(argc < 3){
        fprintf(stderr, "usage: match [-n] [-v] pattern file\n");
        exit(1);
    }
    if(argc > 5){
        fprintf(stderr, "usage: match [-n] [-v] pattern file\n");
        exit(1);
    }
    if(argc == 5){
        numFlag = true;
        inversion = true;
    }
    else if(argc == 4){
        if(strcmp(argv[1], "-n") == 0){
            numFlag = true;
        }
        else if(strcmp(argv[1], "-v") == 0){
            inversion = true;
        }
    }
    char const *pattern;
    pattern = argv[argc - 2];
    char *fileName;
    fileName = argv[argc - 1];
    if(!validPattern(pattern)){
        //printf("Invalid pattern: %s\n", pattern);
        fprintf(stderr, "Invalid pattern: %s\n", pattern);
        exit(1);
    }
    
    /*
    FILE *fp = fopen(fileName, "r");
    if(!fp){
        //printf("Can't open file: %s", fileName);
        fprintf(stderr, "Can't open file: %s\n", fileName);
        exit(1);
    }
    */
    char inputLines[1000][5000];
    int lineCounter = 0;
    
    char *testLine = "big-blue-car.exe";
    bool match = matchPattern(pattern, testLine);
    printf("Matched?: %d", match);
    
    /*
    int matchCounter = 0;
    while(readLine(fp, inputLines[lineCounter], 1023)){
        char const *curr;
        curr = inputLines[lineCounter];
        
        if(inversion){
            if(!matchPattern(pattern, curr)){
                if(matchCounter == 1000){
                    fprintf(stderr, "Too many matches\n");
                    exit(1);
                }
                addLine(lineCounter + 1, curr); 
                matchCounter++;
            }
        }
        else{
            if(matchPattern(pattern, curr)){
                if(matchCounter == 1000){
                    fprintf(stderr, "Too many matches\n");
                    exit(1);
                }
                addLine(lineCounter + 1, curr);
                matchCounter++;
            }
        }
        //printf("%d %s", lineCounter + 1, curr);
        lineCounter++;
    }
    printList(numFlag);
    fclose(fp);
    */
    return 0;
}