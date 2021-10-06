//Abhinav Solasa 
//1/30/19
//Enloe
//Int-5
//Contest #2
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner; 
public class Diff {

	public static String common(String first, String second)
	{
		String com="";
		for(int j=0;j<first.length();j++)
		{
			if(second.indexOf(first.charAt(j))!=-1)
			{
				com+=first.charAt(j);
				second=second.substring(second.indexOf(first.charAt(j))+1);
			}
		}
		StringBuilder string = new StringBuilder(com);
		for(int c=0;c<com.length();c++)
		{
			for(int d=c+1;d<com.length();d++)
			{
				if(com.charAt(c) > com.charAt(d))
				{
					
					char temp = com.charAt(c);
					string.setCharAt(c, com.charAt(d));
					string.setCharAt(d,temp);
					com=new String(string);
				}
			}
		}
		return com;
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("S://Public/CS/int2.txt");
		Scanner sc= new Scanner(file);
	    String s1, s2;
	    String[] input= new String[5];
	    int counter=0;
	    while (sc.hasNext()) {
		    if (sc.hasNextLine()) {
		    input[counter]=sc.nextLine();
		    } 
		    else {
		        sc.next();
		    }
		    counter++;
	    }
	    for(int i=0;i<5;i++)
	    {
	    	s1=input[i].substring(0, input[i].indexOf(' '));
	    	s2=input[i].substring(input[i].indexOf(' ')+1);
	   // 	s1=new String ("potter");
	   // 	s2=new String ("stopper");
	    	String lr1, lr2, rl1, rl2;
	    	lr1=new String(common(s1,s2));
	    	lr2=new String(common(s2,s1));
	    	String reverse1="", reverse2="";
	    	int count=0;
	    	StringBuilder rev = new StringBuilder(s1);
	    	for(int b=s1.length()-1;b>-1;b--)
	    	{
	    		char temp=s1.charAt(count);
	    		rev.setCharAt(count, s1.charAt(b));
	    		rev.setCharAt( b, temp);
	    		reverse1=new String (rev);
	    		count++;
	    	}
	    	StringBuilder rev2 = new StringBuilder(s2);
	    	count=0;
	    	for(int b=s2.length()-1;b>-1;b--)
	    	{
	    		char temp=s2.charAt(count);
	    		rev2.setCharAt(count, s2.charAt(b));
	    		rev2.setCharAt( b, temp);
	    		reverse2=new String (rev2);
	    		count++;
	    	}
	  //  	System.out.println("Reverse1 : "+reverse1);
	 //   	System.out.println("Reverse2 : "+reverse2);
	    	rl1=new String(common(reverse1,reverse2));
	    	rl2=new String(common(reverse2, reverse1));
	    	String answer=new String(common(common(lr1,lr2), common(rl1, rl2)));
	    	if(common(common(lr1,lr2), common(rl1, rl2)).equals(""))
	    		System.out.println("Answer: None");
	    	
	    	else
	    	{
	    		StringBuilder ans = new StringBuilder(answer);
	    		for(int n=1;n<answer.length();n++)
	    			if(answer.charAt(n)==answer.charAt(n-1))
	    			{
	    				ans.setCharAt(n,'0');
	    			}
	    		answer="";
	    		for(int p=0;p<ans.length();p++)
	    		{
	    			if(ans.charAt(p)!='0')
	    				answer+=ans.charAt(p);
	    		}
		    	System.out.println("Answer: "+answer);
	    	}
//
//	    	System.out.println("Common: "+rl1);
//	    	System.out.println("Common2: "+rl2);
//	    	System.out.println("Common3: "+lr1);
//	    	System.out.println("Commmon 4: "+lr2);
//	    	
	    	 	
	    }
	}
}
	    

