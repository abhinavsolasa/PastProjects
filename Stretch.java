import java.awt.List;
import java.util.ArrayList;

public class Stretch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rows=6;
		int columns=10;
		int numbers= 2;
		int start=40;
		int[] blocks= new int[numbers];
		int[][] board = new int[6][10];
		int num=0;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				num+=1;
				board[i][j]=num;
			}
		}
		
		blocks[0]=23;
		blocks[1]=37;
		for(int j=0;j<numbers;j++)
		{
			for(int k=0; k<rows;k++)
			{
				for(int l=0;l<columns;l++)
				{
					if(board[k][l]==blocks[j])
					{
						board[k][l]=0;
					}
				}
			}
		}
		int poscolumn = 0, posrow=0;
		for(int x=0;x<rows;x++)
		{
			for(int y=0;y<columns;y++)
			{
				if(board[x][y]==start)
				{
					poscolumn=y;
					posrow=x;
				}
			}	
		}
		System.out.println("Start coordinates: " + posrow+" "+ poscolumn);	
		ArrayList <Integer> types = new ArrayList<Integer>();
		// type1= A; type 2= B, type 3= C
		
		int type= 1;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				System.out.print(board[i][j]+ " ");
				if(j==(columns-1))
				{
					System.out.println();
				}
			}
		}
		if((start%columns)==0)
		{
			do
			{
				
			}while(poscolumn!=0);
		}
		
		else
		{
			do
			{
				if(type==1)
				{
					if(board[posrow][poscolumn-3]!=0)
					{
						types.add(type);
					}
					type++;
				}
				else if(type==2)
				{
					
					
					type++;
				}
				else
				{
					
					
					type =1;
				}
				
				
			}while(poscolumn!=columns-1);
		}
		
		char[] answers= new char[types.size()];
		for(int e=0;e<types.size();e++)
		{
			if(types.get(e)==1)
			{
				answers[e]='A';
			}
			else if(types.get(e)==2)
			{
				answers[e]='B';
			}
			else
			{
				answers[e]='C';
			}
		}
		    
	}

}
