import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Coordpoint {
	private int x;
	private int y;
	
	public Coordpoint(int xcoord, int ycoord)
	{
		x=xcoord;
		y=ycoord;
	}
	public String toString()
	{
		return "("+ x + "," + y + ")";
		
	}
	public Coordpoint plus(Coordpoint b)
	{
		Coordpoint a= this;
		
		return new Coordpoint(a.x+b.x, a.y+b.y);
		
	}
	public Coordpoint scalar(int value)
	{
		return new Coordpoint(value*this.x, value*this.y);
	}
	public double slope(Coordpoint b)
	{
		Coordpoint a=this;
		

		int deltax=0;
		double dy=0.0;
			if(a.x!=b.x)
			{
				dy=b.y-a.y;
				deltax=b.x-a.x;
			}
			return (dy/deltax);

		
	}
	private static Scanner scanner;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file =new File ("H://Lineinput.txt");
		//File file = new File("H://Lineinput.txt");
		scanner= new Scanner(file);
		String Coord1;
		String Coord2;
		String substring1;
		Coord1=scanner.nextLine();
		
		
		int x1=Integer.parseInt(Coord1.substring(1, Coord1.indexOf(',')));
		int y1=Integer.parseInt(Coord1.substring(Coord1.indexOf(',')+1, Coord1.indexOf(')')));
		
		Coord2=scanner.nextLine();
//		System.out.println(Coord2);
//		System.out.println(Coord2.charAt(1));
		int x2=Integer.parseInt(Coord2.substring(1, Coord2.indexOf(',')));
		int y2=Integer.parseInt(Coord2.substring(Coord2.indexOf(',')+1, Coord2.indexOf(')')));
		
		Coordpoint a=new Coordpoint(x1,y1);
		Coordpoint b=new Coordpoint(x2,y2);
		System.out.println(a);
		System.out.println(b);
		
		System.out.println(a.plus(b));
		System.out.println(b.scalar(-1));
		
			
		System.out.println("Slope: "+ a.slope(b));


	}

}
