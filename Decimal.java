import java.util.ArrayList;

public class Decimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				int num1=9;
				int num2=56;
				ArrayList <Integer> digits= new ArrayList<Integer>();
				int numbers [] = new int[10];
				for(int i=0;i<numbers.length;i++)
				{
					numbers[i]=0;
				}
				double quotient= ((double) num1)/num2;
				System.out.println("Actual quotient: "+ quotient);
				boolean finished=false;
				quotient*=10;
//				System.out.println("One iteration: "+quotient);
				digits.add((int)(quotient));
//				System.out.println("First digit: " +digits.get(0));
				quotient-=(int)(quotient);
//				System.out.println("subtracton: "+quotient);
				int count=1;
				do
				{
					quotient*=10;
					int temp=(int)(quotient);
					quotient-=(int)(quotient);
//					System.out.println(quotient);
					for(int i=0;i<digits.size();i++)
					{
						if(temp==digits.get(i))
						{
							finished=true;
//							System.out.println("True");
						}
					}
//					System.out.println("Temp: "+temp);
					if(finished==false)
					{
						digits.add(temp);
//						System.out.println("Digit: "+temp);
					}
					count++;
				
				}while(finished==false);
				
//				System.out.println("Number of times: "+ (count-1));
				double answer=0;
				double exponent=-1;
				double part;
				System.out.println("Number of times: "+digits.size());
				for(int i=0;i<digits.size();i++)
				{
					double j=i+1;
//					System.out.println(digits.get(i));
					part=digits.get(i)*(Math.pow(10, -j));
//					System.out.println("Part: "+part);
					answer+=part;
				}
				System.out.println("Answer:" +answer);
			}


		}
