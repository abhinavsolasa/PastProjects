/**
 * @file base.c
 * @author Abhinav Solasa asolasa
 * This program is responsible for reading and writing the 
 expression and its corresponding value in the respective number
 base. It does all the conversions. 
 */
#include "base.h"
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include "operation.h"
#include <ctype.h>
#include <limits.h>

// This preprocessor syntax makes it so we can override the value of BASE with
// a compiler option.  For some desired base, n, we can compile with: -DBASE=n
// Don't change these three lines of preprocessor directives.
#ifndef BASE
/** Base this program uses for input and output. */
#define BASE 7
#endif

/**
* this method skips the spaces until a non-whitespace 
character pops up
* @return --> the code of the next non-whitespace character
*/
int skipSpace() {
    char x; 
    while(isspace(x = getchar()) != 0 && (x != '\n') && (x != EOF));
    ungetc(x, stdin);
    return (int) x;  
}

/**
* converts the given character to a digit
* @param ch --> the character to convert to an integer
* @return --> the integer version of the character 
*/
int char_to_digit(char ch){
    //printf("Inside char_to_digit function\n");
    if(ch >= '0' && ch <= '9'){
        //printf("Inside the 0 to 9 character range condition\n");
        int digit = ch - '0';
        //printf("The Digit:  %d\n", digit);
        if(digit >= BASE){
            exit(FAIL_INPUT);
        }
        return digit;
    } 
    else if(ch >= 'A' && ch <= 'Z'){
        int value;
        value = ch - 'A';
        value += 10;
        if(value >= BASE){
            exit(FAIL_INPUT);
        }
        return value;
    }
    exit(FAIL_INPUT);
}

/**
* converts given integer to character version
* @param d --> the integer to be converted to a character
* @return  --> returns the character version of the digit
*/
char digit_to_char(int d){
    char alphabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    if(d >= 0 && d < 10){
        return d + '0';
    }
    else if(d >= 10 && d < BASE){
        return alphabet[d - 10];
    }
    exit(0);
}

/**
* reads in a chain of numbers to return the overall number
* @return the corresponding value in base 10
*/
long readValue(){
  //printf("We are inside readValue Function \n");
    // Value we've parsed so far.
  long value = 0;
  char ch;
  bool negative = false;
  // Get the next input character.
  //int count = 0; 
  ch = getchar();
  //printf("First character: %c\n", ch);
  if(ch == '-'){
      //printf("Negative character\n");
      char next = getchar();
      //printf("Next: %c\n", next);
      if((next <= '9'  && next >= '0') || (next <= 'Z'  && next >= 'A')){
          //printf("This number is negative\n");
          negative = true;  
          //printf("Negative Value\n");
      }
      ungetc(next, stdin);
      ch = getchar();
      //printf("Unget character: %c", ch);
  }
  // Keep reading as long as we're seeing digits.
  while ((ch <= '9'  && ch >= '0') || (ch <= 'Z'  && ch >= 'A') ) {
     // printf("We are inside while loop for digits \n");
    // Convert from ASCII code for the next digit into the value
    // of that digit.  For example 'A' -> 10 or '7' -> 7
    int d = char_to_digit( ch );
    
    //printf("Integer value of character:  %d\n", d);
    // Slide all digits we've read so far one place value to the
    // left.
    
    value = times(value, BASE);
    // Add this digit as a new, low-order digit.
    value = plus(value, d);  
    /**
    if(negative){
        if(value > 0){
            exit(100);
        }
    }
    else if(!negative){
        if(value < 0){
            exit(100);
        }
    }
    **/
    // Get the next input character.
    ch = getchar();
  }
  if(negative){
      value *= -1;
  }  
  /*
  if(ch == '\n'){
      printf("There is a newline character!! ");
  }
  */

  // ch was one character past the end of the number.  Put it back on
  // the input stream so it's there for other code to parse (see notes
  // below about ungetc()).
  ungetc(ch, stdin);
  //ch = getchar();
  return value;
}

/**
* @param value --> the final result of the expression that is to be displayed
*/
void writeValue(long value){
    /*
    long rem = 0, place = 1;
    long conv = 0;
    while (val)
    {
       rem = val % BASE;
       val = val / BASE;
       conv = conv + (rem * place);
       place *= 10;
    }
    */
    bool negative = false;
    bool min = false;

    if(value == 0){
        return;
    }
    else{
        if(value < 0){
            negative = true;
            
            //printf("Value before negating: %ld\n", value);
            if(value == LONG_MIN){
                value += 1; 
                min = true;
            }
            value = value * -1;
            //printf("Value: %ld\n", value);
        }
        
        int d = value % BASE;
        if(min){
            d += 1;
        }
        //printf("Digit: %d\n", d);
        char ch = digit_to_char( d );
        //printf("Digit: %c\n", ch);
        writeValue(value/BASE);          
        printf("%c", ch);
    }
    
    // Convert it to a character, e.g, 13 -> 'D' or 3 -> '3'
    

    // Slide remaining digits to the right.
    //value = value / BASE;
    
    // Print out the next digit (note, this will give us the digits
    // backward).    
    
}



/*
long tenToBase(long n){
    
    long num = 0; 
    long quotient = n;
    int rem = 0;
    int i = 0;
    while(quotient != 0){
        rem = quotient % BASE;
        quotient = quotient / BASE;   
        num = (rem * pow(10, i)) + num;
        i++;
    }
    while ( value != 0 ) {
        // Get the next digit on the right.
        int d = value % BASE;
    
        // Convert it to a character, e.g, 13 -> 'D' or 3 -> '3'
        ch = digit_to_char( d );
    
        // Print out the next digit (note, this will give us the digits
        // backward).
        print("%c", ch);
    
        // Slide remaining digits to the right.
        value = value / BASE;
    
  }
    return value;
}
*/