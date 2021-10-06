import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Prefix {
	
	static Boolean isNum(char a)
	{
		if((a!='+')&&(a!='-')&&(a!='*')&&(a!='/')&&(a!='>')&&(a!='@'))
			return true;
		else
			return false;
	}
	public static int calculate(String expression)
	{
		ArrayList<Integer> integers= new ArrayList<Integer>();
		for(int k=expression.length()-1;k>=0;k--)
		{
	//		System.out.println("Position: "+k); 
			if(isNum(expression.charAt(k)))
			{
					String num="";
					num+=expression.charAt(k);
					integers.add(Integer.parseInt(num));
			}
			else
			{
				//came upon an operator
				int num1=integers.get(integers.size()-1);
		//		System.out.println("Num1: "+num1);
				integers.remove(integers.size()-1);
				int num2= integers.get(integers.size()-1);
			//	System.out.println("Num2: "+num2);
				integers.remove(integers.size()-1);
				int num3 = 1234;
				if(integers.size()!=0)
				{
					 num3=integers.get(integers.size()-1);
				//	 System.out.println("Num3: "+num3);
					//integers.remove(integers.size()-1);
				}
				
				
				switch (expression.charAt(k))
				{
					case '-':
						integers.add(num1-num2);
//						System.out.println("Result"+(num1-num2));
						break;
					case '+':
						integers.add(num1+num2);
//						System.out.println("Result"+(num1+num2));
						break;
					case '/':
						integers.add(num1/num2);
					//	System.out.println("Result"+(num1/num2));
						break;
					case '*':
						integers.add(num1*num2);
//						System.out.println("Result"+(num1*num2));
						break;
					case '>':
						integers.remove(integers.size()-1);
						integers.add(Math.max(Math.max(num1,  num2), Math.max(num2, num3)));
					//	System.out.println("Result "+ Math.max(Math.max(num1,  num2), Math.max(num2, num3)));
						break;
					case '@':
						integers.remove(integers.size()-1);
						if(num1>0)
							integers.add(num2);
						else
							integers.add(num3);
					//	System.out.println("Result"+integers.get(integers.size()-1));
						break;
				}
			}
		}
		return integers.get(integers.size()-1);
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file =  new File("C://Users/abhidaboss/Prefix input.txt"); 
	   Scanner sc = new Scanner(file); 
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
	    	input[i]=input[i].replaceAll("\\s+", "");
	    	System.out.println("Input: "+input[i]);
	    	System.out.println(calculate(input[i]));
	      }
	}

}