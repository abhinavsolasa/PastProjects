#ifndef _BUFFER_H_
#define _BUFFER_H_

#include <stdbool.h>

/** Number of bits in a byte. */
#define BBITS 8

/** Representation of a resizable array of bytes, with functions to
    make it easy to access individual bits. */
typedef struct {
  /** Resizable array of bytes stored in this buffer. */
  char *data;

  /** Number of bytes currently stored in the data array. */
  int len;

  /** Capacity of the data array, measured in bytes. */
  int cap;
} Buffer;

void setBit(Buffer const *buffer, int idx, bool bitVal);
bool getBit(Buffer const *buffer, int idx);
void appendByte(Buffer *buffer, char byteVal);
void freeBuffer(Buffer *buffer);
Buffer *makeBuffer();
Buffer *loadBuffer(char const *filename);
void saveBuffer(Buffer *buffer, char const *filename);
extern int bits;
extern int n;

#endif
