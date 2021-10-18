/**
 * @file ballistics.c
 * @author Abhinav Solasa asolasa
 * This program reads in an input of an initial velocity of a projectile in parabolic motion. 
 * Based on the initial velocity, the program creates the table of the projectile motion 
 * using 4 variables: time, v0, angle, and distance. These 4 variables are outputted in the table 
 * as 4 different columns. 
  */
#include <stdio.h>
#include <stdlib.h>
#include <math.h>


/**
 * this function returns the total flight time of the projectile from
 * launch moment to landing 
 * @param angle --> the angle in which the projectile is launched
 * @param v0 --> the initial velocity
 * @return time --> returns the total time the projectile was in air
  */
double flightTime( int angle, double v0 )
{
	double radians = (double) angle * (M_PI / 180);
    	double time;
    	time = v0 * sin(radians)/4.905;
    	return time;
}

/**
 * outputs a row of data of each value
 * @param angle --> the angle in which the projectile is launched
 * @param v0 --> the initial velocity
 * @param t --> the air time of the projectile
  */
void tableRow( int angle, double v0, double t )
{
	printf("%10d", angle);
	printf(" | ");
	printf("%10.3f", v0);
	printf(" | ");
	printf("%10.3f", t);
	printf(" | ");
	double radians = angle * (M_PI / 180);
    	double distance = v0 * t * cos(radians);
    	printf("%10.3f", distance);
    	printf("\n");
}

/**
 * the main function displays a table of four columns displaying the 
 * angle, v0, time, and distance of the projectile. v0 stays constant. 
  */
int main()
{
	printf("V0: ");
	double v0;
	scanf("%lf", &v0);
	printf("\n");
	printf("%10s", "angle");
    	printf(" | ");
    	printf("%10s", "v0"); 
	printf(" | ");
	printf("%10s", "time");
	printf(" | ");
	printf("%10s", "distance");
	printf("\n");
	for(int j = 1; j <= 49; j++) {
		if(j == 12 || j == 25 || j == 38) {
			printf("+");
		}
		else {
        		printf("-");
		}
    	}
    	printf("\n");
	for(int i = 0; i <= 90; i += 5) {
        
        	tableRow(i, v0, flightTime(i, v0));
   	}
	return 0;
}
