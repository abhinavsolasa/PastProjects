#ifndef _DICTIONARY_H_
#define _DICTIONARY_H_

/** Minimum number of bits for a dictionary index. */
#define MINIMUM_BITS 8

/** Default number of bits to use for each code. */
#define DEFAULT_BITS 10

/**
   Print out a word consisting of an arbitrary sequence of bytes
   (maybe including some non-ASCII codes and zeros).  It prints
   each byte using two characters, printing a symbol for visible
   characters and a 2-digit hexadecimal value for other bytes.
   @param word Array containing the bytes of the word.
   @param len Number of bytes in the word.
*/
void printWord( char *word, int len );

/**
*represents a Dictionary structure. It stores a list of words, stores the number of slots available and
a list for the lengths of each word
*/
typedef struct {
  char **list;
  int slots;
  int *lengths;
} Dictionary;


Dictionary *makeDictionary(int bits);
void freeDictionary(Dictionary *d);
void reportDictionary(Dictionary *d);
int lookupWord(Dictionary *d, char *word, int len);
void addWord(Dictionary *d, char *word, int len);
extern int wordCount;
#endif
