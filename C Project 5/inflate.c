/**
* filename --> inflate.c
* author --> asolasa
* This is one of the top level components of the project. This is where an compressed input file
is read in as a binary file and decoded to get the original file. in binary. The default bits per binary code is 10 bits unless changed in the command line.
The dictionary is also reported unless specified by user. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "dictionary.h"
#include "buffer.h"

void invalid(){
    fprintf(stderr, "usage: deflate [-d] [-b bits] infile outfile\n");
    exit(1);
}
/**
*This is where the decoding of a compressed file happens. 
*@param argc --> number of arguments
*@param argv --> list of command line arguments
*/
int main(int argc , char *argv[]){
    int bits = DEFAULT_BITS;
    char *infile, *outfile;
    bool report = false;
    if(argc == 3){
        infile = argv[1];
        outfile = argv[2];
    }
    else{
        bool check = false;
        for(int i = 1; i < argc; i++){
            if((strcmp(argv[i], "-d") == 0)){
                if(i < argc - 2){
                    report = true;
                }
                else{
                    invalid();
                }
            }
            else if(strcmp(argv[i], "-b") == 0){
                if(i + 1 < argc - 2){
                    check = true;
                    bits = atoi(argv[i + 1]);
                    if(bits < 8 || bits > 32){
                        invalid();
                    }
                }
                else{
                    invalid();
                }
            }
            else{
                if(i == argc - 2){
                    infile = argv[i];
                    if(strcmp(infile, "-d") == 0 || strcmp(infile, "-b") == 0){
                        invalid();
                    }
                }
                else if(i == argc - 1){
                    outfile = argv[i];
                    if(strcmp(outfile, "-d") == 0 || strcmp(outfile, "-b") == 0){
                        invalid();
                    }
                }
                else{
                    if(!check){
                        invalid();
                    }
                }
            }
        }
    }
    
    /*
    printf("Input file: %s\n", infile);
    printf("Output file: %s\n", outfile);
    */
    Buffer *buffer = loadBuffer(infile);
    Buffer *outBuffer = makeBuffer();
    Dictionary *dict = makeDictionary(bits);
    //printf("Bits: %d\n", bits);
   
    //int size = buffer->len / bits;
    //stores the indexes as 32 bit integers
    long indexes[(buffer->len * 8) / bits];
    int count = 0;
    long num = 0x00;
    int idx;
    //printf("# of Bits: %d\n", buffer->len * BBITS);
    //iterates through data buffer and reads in bit by bit to store in integer
    for(int i = 0; i < buffer->len * BBITS; i++){
        //stores index 
        idx = i / bits;
        num = num << 1;
        //printf("Idx: %d\n", i);
        //printf("Bit: %d\n", getBit(buffer, i));
        /*
        if(i % bits == bits - 1){
            printf("\n");
        }
        */
       // printf("Num after shift %X\n", num);
        if(getBit(buffer, i)){
            //printf("Inside true bit\n");
            num += 0x01;
            //printf("Num: %X\n\n", num);
            //shifts to the left by one so that we can add next bit as last bit             
        }
        else if(getBit(buffer, i) == 0){
            //printf("Inside false bit\n");
            num += 0x00;
            //printf("Num: %X\n\n", num);
        }
        //after reading in 10th bit, add the num to the ary and reset the num to 0x00 to read in next 10 bits one by one
        if((i % bits) == (bits - 1)){
            //printf("Inside tenth bit\n");
            //printf("Index: %d\n\n", num);
            indexes[idx] = num;
            count++;
            num = 0x00;
        }
    }
    int find = 0;
    int marker = 0; //the tracker that iterates through buffer to get longest word to add to dictionary while going through index list 
    int len = 0;
    for(int j = 0; j < count; j++){
        //printf("Index %d: %d\n", j, indexes[j]);
        //printf("%s\n", dict->list[indexes[j]]); 
            
        find = indexes[j];
        if(wordCount <= indexes[j]){
            freeBuffer(buffer);
            freeBuffer(outBuffer);
            freeDictionary(dict);
            fprintf(stderr, "Undefined code: %ld\n", indexes[j]);
            exit(1);
        }
        for(int k = 0; k < dict->lengths[find]; k++){
            appendByte(outBuffer, dict->list[find][k]);
            len++;
        }
        if(j != 0){
            char sub[buffer->len];
            int size = 1;
            for(int l = 0; l < len; l++){
                memcpy(sub, &outBuffer->data[marker], size);
                if(lookupWord(dict, sub, size) != -1){
                    char temp[buffer->len];
                    memcpy(temp, &outBuffer->data[marker], size + 1);
                    if(lookupWord(dict, temp, size + 1) == -1){
                        addWord(dict, temp, size + 1);
                        break;
                    }
                    else{
                        size++;
                    }
                }

            }
            marker += size;
        }
        
    }
    //printf("\n");

    /*
    printf("Input: \n");
    for(int i = 0; i < buffer->len; i++){
        printf("%X\n", buffer->data[i]);
    }
    */
    
    //printf("Input: %X\n", buffer->data);
    
    /*
    if(report){
        reportDictionary(dict);
    }
    */
    if(report){
        reportDictionary(dict);
    }    
    saveBuffer(outBuffer, outfile);
    
    freeBuffer(outBuffer);
    freeBuffer(buffer);
    freeDictionary(dict);
    return 0;
}
