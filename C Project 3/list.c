/**
List.c is responsible for adding lines that match the pattern to the list, 
or the lines that don't match to the list based on if 
and actually for printing out the final list. 
*@file list.c
*@author asolasa
*/
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "list.h"
char *lines[1000];
int lineNumbers[1000];
int matchCount = 0;
/**
* adds the line to the matching line list
* @param lno --> line number
* @param line[] --> matching line that is to be added to matching lines list
*/
void addLine(int lno, char const line[]){
    lines[matchCount] = line;
    lineNumbers[matchCount] = lno;
    matchCount++;
}
/**
* prints the list of lines that matches the pattern
*@param numberFlag --> determines if the line number should be outputted or not
*/
void printList(bool numberFlag){
    if(numberFlag){
        int maxLine = lineNumbers[matchCount - 1];
        if(maxLine < 10){
            for(int l = 0; l < matchCount; l++){
                if(strlen(lines[l]) > 78){
                    lines[l][80] = '\0';
                    lines[l][79] = '.';
                    lines[l][78] = '.';
                }
                printf("%d %s", lineNumbers[l], lines[l]);
            }
        }
        else if(maxLine <= 99 && maxLine >= 10){
            for(int i = 0; i < matchCount; i++){
                if(strlen(lines[i]) > 78){
                    lines[i][80] = '\0';
                    lines[i][79] = '.';
                    lines[i][78] = '.';
                }
                printf("%2d %s", lineNumbers[i], lines[i]);
            }
        }
        else if(maxLine <= 999 && maxLine >= 100){
            for(int j = 0; j < matchCount; j++){
                if(strlen(lines[j]) > 78){
                    lines[j][80] = '\0';
                    lines[j][79] = '.';
                    lines[j][78] = '.';
                }
                printf("%3d %s", lineNumbers[j], lines[j]);
            }
        }
        else if(maxLine <= 9999 && maxLine >= 1000){
            for(int k = 0; k < matchCount; k++){
                if(strlen(lines[k]) > 78){
                    lines[k][80] = '\0';
                    lines[k][79] = '.';
                    lines[k][78] = '.';
                }
                printf("%4d %s", lineNumbers[k], lines[k]);
            }
        }
    }
    else{
        for(int j = 0; j < matchCount; j++){
            if(strlen(lines[j]) > 78){
                lines[j][80] = '\0';
                lines[j][79] = '.';
                lines[j][78] = '.';
            }
            printf("%s", lines[j]);
        }
    }
}