/**
* filename --> dictionary.c
* author --> asolasa
* This is where the dictionary structure and behaviors are implemented. A dictionary stores
unique words of different lengths. In its initial form, it stores all one byte words from 0 to 256.
The behaviors include printing a word, looking up a word, adding a word, and reporting the 
dictionary.
*/
#include "dictionary.h"
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include "dictionary.h"

// Documented in the header.
int wordCount = 256;

/**
*prints out a word. It prints out printable characters in character form and nonprintable ones
in hexadecimal form 
*@param word --> word to be printed out
*@param len --> number of bytes in word
*/
void printWord( char *word, int len )
{
  // Print each byte of the word, either as a symbol or in hex.
  for ( int i = 0; i < len; i++ ) {
    // Is this character visible?
    if ( word[ i ] > ' ' && word[ i ] <= '~' )
      printf( " %c", word[ i ] );
    else
      printf( "%02X", (unsigned char) word[ i ] );
  }
}

/**
* creates an empty dictionary that is default set
to 256 slots with the ascii characters
* @param bits --> number of bits set per binary code
*/
Dictionary *makeDictionary(int bits){
  Dictionary *dict;
  dict = (Dictionary *)malloc(sizeof(Dictionary));
  dict->slots = (int) pow(2, bits);
  dict->lengths = (int *)malloc(dict->slots *sizeof(int));
  dict->list = (char **)malloc(dict->slots * sizeof(char *));
  char byteVal = 0x00;
  for(int i = 0; i < 256; i++){
    dict->list[i] = (char *)malloc(1 * sizeof(char));
    dict->list[i][0] = byteVal;
    dict->lengths[i] = 1;
    byteVal++;
  }
  return dict;
}

/**
*frees all the memory in the dictionary
* @param d --> dictionary to free
*/
void freeDictionary(Dictionary *d){
  for(int i = 0; i < wordCount; i++){
    free(d->list[i]);
  }
  free(d->list);
  free(d->lengths);
  free(d);
}

/**
*outputs the dictionary starting index 256
* @param d --> dictionary
*/
void reportDictionary(Dictionary *d){
  for(int i = 256; i < wordCount; i++){
    char *word = d->list[i];
    printf("%4d ", i);
    printWord(word, d->lengths[i]);
    printf("\n");
  }
}

/**
*looks up a word and returns the index of word in dictionary
* @param d --> dictionary
* @param word --> word to find
* @param len --> length of word 
* @return index of the word that is searched up, if it's not in dictionary, -1 is 
returned
*/
int lookupWord(Dictionary *d, char *word, int len){
  if(len == 1){
    for(int i = 0; i < 256; i++){
     // printf("Word 0: %c\n", word[0]);
      //printf("Dictionary word: %c\n", d->list[i][0]);
      if(word[0] == d->list[i][0]){
          return i;
      }
    }
  }
  else{
      //checking if longer byte words are equal to searched word
      for(int k = 256; k < wordCount; k++){
          int matchCount = 0;
          //char *s = d->list[k];
          //only runs this if the length of dictionary word is the same 
          if(d->lengths[k] == len){
              for(int j = 0; j < len; j++){
                  if(d->list[k][j] == word[j]){
                      matchCount++;
                  }
              }
              //if all bytes match, it is the right word
              if(matchCount == len){
                  return k;
              }
          }
      }
  }
  return -1;
}
/**
*adds a word to the dictionary
* @param d --> the dictionary
* @param *word --> the word string
* @param len --> length of the word//number of bytes in word 
*/
void addWord(Dictionary *d, char *word, int len){
  //d->list[wordCount] = (char *)malloc(len * sizeof(char));
  //d->lengths[wordCount] = len;
  if(wordCount < d->slots){
      d->list[wordCount] = (char *)malloc(len * sizeof(char));
      d->lengths[wordCount] = len;
      for(int i = 0; i < len; i++){
          d->list[wordCount][i] = word[i];
      }
       wordCount++;
  }
<<<<<<< HEAD
}
=======
}
>>>>>>> 430f748c22e2a1519ea92d58e7cf8966903572e9
