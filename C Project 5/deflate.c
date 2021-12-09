/**
* filename --> deflate.c
* author --> asolasa
* This is one of the top level components of the project. This is where an uncompressed input file
is read in and is compressed. The default bits per binary code is 10 bits unless changed in the command line.
The dictionary is also reported unless specified by user. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "dictionary.h"
#include "buffer.h"

/**
*outputs the stderr message if the cmd line arguments are invalid
*/
void invalid(){
    fprintf(stderr, "usage: deflate [-d] [-b bits] infile outfile\n");
    exit(1);
}

/**
* This is where the encoding of uncompressed file input occurs. The output file is a compressed file
in binary. It stores the indexes of the words that are used in the dictionary.
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
                    //printf("Valid position for -d\n");
                    report = true;
                }
                else{
                    invalid();
                }
                //printf("Not invalid so far\n\n");
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
    //int indexes[dict->slots];
    /*
     for(int i = 0; i < buffer->len; i++){
        printf("%d\n", buffer->data[i]);
    }
    */
    //printf("Input: %s\n", buffer->data);

    int idx = 0;
    char sub[buffer->len];
    long indexes[(buffer->len * 8) / bits];
    int count = 0;
    while(idx < buffer->len){
        //printf("Current idx at buffer: %d\n", idx);
        int size = 1;
        bool cont = true;
        while(cont){
            //substring of whatever length
            memcpy(sub, &buffer->data[idx], size);
            //checks if substring is inside dictionary
            if(lookupWord(dict, sub, size) != -1){
                char temp[buffer->len];
                memcpy(temp, &buffer->data[idx], size + 1);
                if(lookupWord(dict, temp, size + 1) == -1){
                    indexes[count] = lookupWord(dict, sub, size);
                    count++;
                    if(idx < buffer->len - 1){
                        addWord(dict, temp, size + 1);
                    }
                    cont = false;
                }
                else{
                    size++;
                }
            } 
        }
        idx += size;
    }
    //char c = 0x00;
    int num = 0;
    int n = count * bits;
    if(n % 8 == 0){
        outBuffer->len = n / 8;
    }
    else{
        outBuffer->len = (n / 8) + 1;
    }
    free(outBuffer->data);
    outBuffer->data = (char *)malloc(outBuffer->len * sizeof(char));
    int numBits = outBuffer->len * 8;
    int minBits = count * bits;
    for(int j = 0; j < numBits; j++){
        int idx = j / bits;
        int shift = (bits - 1) - (j % bits);
        num = indexes[idx];
        num = num >> shift;
        num &= 0x01;
       //c = c << 1;
        if(j < minBits){
            if(num == 0){
                setBit(outBuffer, j, 0);
                //printf("%d", getBit(outBuffer, j));
                //c += 0x00;
            }
            else{
                setBit(outBuffer, j, 1);
                //printf("%d", getBit(outBuffer, j));
                //c += 0x01;
            }
        }
        else{
            setBit(outBuffer, j, 0);
            //printf("%d", getBit(outBuffer, j));
        }
        /*
        if(j % BBITS == BBITS - 1){
            appendByte(outBuffer, c);
            c = 0x00;
        }

        if(j % BBITS == 7){
            printf("\n");
        }
        */
    }
    /*
    printf("\n");
    printf("Number of bits to store: %d\n", count * bits);
    printf("Number of bytes to store: %d\n", outBuffer->len);
    
    for(int j = 0; j < count; j++){
        printf("Index %d: %ld\n", j, indexes[j]);
        //printf("%s\n", dict->list[indexes[j]]);  
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
