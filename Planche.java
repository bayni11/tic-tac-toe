package projet_IA;

import java.util.ArrayList;
import java.util.List;

public class Planche {
public static final int No_Joueur =0;
public static final int Joueur_x=1;
public static final int Joueur_Y=2;
private int[][] Planche=new int[3][3];
public Point Move;

public boolean GameOver()
{
	return PlayerWon(Joueur_x) || PlayerWon(Joueur_Y) ||getCelluleDisp().isEmpty();
}
public boolean PlayerWon(int Joueur)
{
	if ((Planche[0][0]==Planche[1][1]&& Planche[0][0]==Planche[2][2] && Planche[0][0]==Joueur) || 
			(Planche[0][2]==Planche[1][1]&& Planche[0][2]==Planche[2][0] && Planche[0][2]==Joueur))
	{
		return true;
	}
for(int i=0;i<3;i++)
{
	
		if((Planche [i][0]==Planche[i][1] && Planche[i][0] == Planche[i][2]&& Planche[i][0]==Joueur)
				||(Planche[0][i])==Planche[1][i]&& Planche[0][i]==Planche[2][i] && Planche[0][i]==Joueur)
		{
	return true;
		}	
}
return false;
}
public List<Point> getCelluleDisp()
{
	List<Point> CelluleDisp= new ArrayList<>();// Declaration d'un tableau qui enregistre notre saisie
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			if(Planche[i][j]==No_Joueur)
				CelluleDisp.add(new Point(i,j));
		}
	}
	
	return CelluleDisp;
}
public boolean placeAMove(Point point,int player)
{
	if(Planche[point.x][point.y]!=No_Joueur)
		return false;
	
	Planche[point.x][point.y]=player;
	return true;
}
public void displayBoard() // Fonction qui situe Notre Choix dans le jeu
{
	System.out.println();
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			String valeur="?";
			if(Planche[i][j]==Joueur_x)
			{
				valeur="X";
			}
			else if(Planche[i][j]==Joueur_Y)
			valeur="O";
			System.out.print(valeur +" ");
		}
		System.out.println();
	}
	System.out.println();
}
public int minimax(int depth,int turn)
{
	if(PlayerWon(Joueur_x))
	{
return 1;
	}
	if(PlayerWon(Joueur_Y))
	{
		return -1;
	}
	List<Point> CelluleDisp= getCelluleDisp();
	if(CelluleDisp.isEmpty())
	{
		return 0;
	}
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;
	for(int i=0;i<CelluleDisp.size();i++)
	{
		Point point=CelluleDisp.get(i);
		if(turn == Joueur_x)
		{
			placeAMove(point, Joueur_x);
			int scoreCourant=minimax(depth +1, Joueur_Y);
					max=Math.max(scoreCourant, max);
			
			if(depth==0)
			
				System.out.println("SCORE DE LA POSITION " +point+ " = "+scoreCourant);
			
			if(scoreCourant>=0)
			
				if(depth==0)
				Move=point;
		
		if(scoreCourant==1) {	
			Planche[point.x][point.y]=No_Joueur;
		break;
	}
		if(i==CelluleDisp.size()-1 && max<0)
		
			if(depth==0)
			
				Move=point;
			
		}
		else if(turn == Joueur_Y)
		{
		placeAMove(point, Joueur_Y);
		int scoreCourant=minimax(depth +1, Joueur_x);
		min=Math.min(scoreCourant, min);
		if(min==-1) {
			Planche[point.x][point.y]=No_Joueur;
		break;
			}
		}
		Planche[point.x][point.y]=No_Joueur;
}
	return turn==Joueur_x ? max:min;
}
}

