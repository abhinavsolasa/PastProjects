/**
 * @file calc.c
 * @author Abhinav Solasa asolasa
 * This program is where the main function is located.
This is where everything runs.
 */
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "base.h"
#include "operation.h"

int main(){
    char ch;
    bool validInput = false;
    long value = 0;
    skipSpace();
    //printf("Past first skip space \n");
    //printf("%c\n", x);
   // ungetc(x, stdin);
    value = readValue();
    int count = 0;
    while((ch = getchar()) != '\n' && (ch != EOF)){
            //printf("Inside main while loop\n");
            count++;
            if(ch == '\n'){
                printf("Valid Input \n");
                validInput = true;
            }
            else{
                ungetc(ch, stdin);
                skipSpace();
                char operation = getchar();
                //printf("Operation: %c\n", operation);
                if(operation != '+' && operation != '-' && 
                    operation != '/' && operation != '*'){
                    exit(FAIL_INPUT);
                }
                skipSpace();
                
                char check = getchar();
                if(check == '+' || check == '*' 
                    || check == '/'){
                    exit(FAIL_INPUT);
                }
                ungetc(check, stdin);
                
                long secondVal = readValue(); 
                //printf("Second Value: %ld\n", secondVal);
                if(operation == '+'){
                value = plus(value, secondVal);
                //value += secondVal;
                }
                else if(operation == '-'){
                    //printf("Subtraction\n");
                    value = minus(value, secondVal);
                    //value -= secondVal;
                }
                else if(operation == '*'){
                value = times(value, secondVal);
                //value *= secondVal;
                }
                else if(operation == '/'){
                    value = divide(value, secondVal);
                }
                //printf("Updated Value: %ld\n", value);
                skipSpace();
                //printf("Past last skipSpace\n");
            }                      
    }
    
    if(ch == '\n'){
        //printf("Valid input\n");
        validInput = true;
    }
    if(!validInput){
        //printf("Invalid input and exit status 102\n");
        exit(FAIL_INPUT);
    }
    /*
    if(value < 0){
        printf("%c", '-');
    }
    */
    //printf("About to output %ld\n", value);
    if(value < 0){
        printf("%c", '-');
    }
    if(value == 0){
        printf("%c", '0');
    }
    else{
        writeValue(value);
    }
    printf("%c", '\n');
    return EXIT_SUCCESS;
}