/**
* filename --> buffer.c
* author --> asolasa
* This is the buffer component of the project. This is where we initialize and create a buffer structure.
Other methods such as setBit and getBit are also implemented here. These are important methods in being able to 
modify the buffer and access certain bits inside the buffer.
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "buffer.h"

int bits = 10;
int n;

/**
*create a buffer by initializing space and allocating memory for the buffer struct 
*@return an initialized empty buffer 
*/
Buffer *makeBuffer(){
    Buffer *buf = (Buffer *)malloc(sizeof(Buffer));
    buf->cap = 256;
    buf->data = (char *)malloc(buf->cap * sizeof(char));
    buf->len = 0;
    
    return buf;
}

/**
*frees all the memory in the buffer
* @param buffer --> pointer to the buffer that we have to free 
*/
void freeBuffer(Buffer *buffer){
    free(buffer->data);
    free(buffer);
}

/**
*appends a character byte to the buffer data array* 
* @param buffer --> the buffer where the byte will be appended to 
* @param byteVal --> byte that is to be added 
*/
void appendByte(Buffer *buffer, char byteVal){
    if(buffer->len >= buffer->cap){
        buffer->cap *= 2;
        buffer->data = (char *)realloc(buffer->data, buffer->cap * sizeof(char));
    }
    buffer->data[buffer->len] = byteVal;
    buffer->len++;
}

/**
* accesses a bit in a specific index in the buffer
* @param idx --> index we are checking
* @param buffer --> buffer where we are reading in the bits from 
* @return true if the bit at the index is a 1 and false if it is a 0.
*/
bool getBit(Buffer const *buffer, int idx){
    int cNum = idx / BBITS;
    int movedIdx = 7 - (idx % BBITS);
    char ch = buffer->data[cNum];
    return ((ch >> movedIdx) & 0x01);
}

/**
*sets a certain bit at a specific index to specified bool value 
* @param buffer --> the buffer struct where data is stored
* @param idx --> idx of bit to be changed
* @param bitVal --> value that bit should be changed to
*/
void setBit(Buffer const *buffer, int idx, bool bitVal){
    int cNum = idx / BBITS;
    char ch = buffer->data[cNum];
    int movedIdx = 7 - (idx % BBITS);
    char result;
  //  int k = BBITS - (idx % BBITS);
    if(bitVal){
        result = ((ch >> movedIdx) & 0x01 ); //set everything except last bit to 0
        result |= 0x01; // or last bit to 1
        result = (result << movedIdx); //shifts last bit back to original idx
        ch |= result;
        //ch |= 1 << k;
    }
    else{
        char mask = 0x01;
        mask = (mask << movedIdx);
        ch = ch & ~(mask);
    }
    buffer->data[cNum] = ch;
}

/**
*reads in characters from a file 
* @param filename --> file where the bytes are read in from 
* @return a buffer loaded with the file contents in bytes
*/
Buffer *loadBuffer(char const *filename){
    Buffer *buf = makeBuffer(); 
    FILE *fp = fopen(filename, "rb");
    if(!fp){
        freeBuffer(buf);
        perror(filename);
        exit(1);
    }
    char ch;
    //buf->len = fread(buf->data, sizeof(char), , fp);
    while((ch = fgetc(fp)) != EOF){
        /*
        buf->data[buf->len] = ch;
        buf->len++;
        if(buf->len >= buf->cap){
            buf->cap *= 2;
            buf->data = (char *)realloc(buf->data, buf->cap * sizeof(char));
        }
        */
        appendByte(buf, ch);
    }
    fclose(fp);
    return buf;
}
/**
* writes the contents of the buffer to a file
* @param buffer --> buffer that is to be written
* @param filename --> file where the buffer will be written to 
*/
void saveBuffer(Buffer *buffer, char const *filename){
    FILE *fp = fopen(filename, "wb");
    fwrite(buffer->data, sizeof(char), buffer->len, fp); 
    //buffer->len
    fclose(fp);
}
