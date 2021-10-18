/** Maximum output line length. */
#define OUTPUT_LIMIT 80
#define FAILURE 1
#define MAX_MATCH 1000
void addline(int, char const []);
void printList(bool);
extern char *lines[1000];
extern int matchCount;
extern int lineNumbers[1000];