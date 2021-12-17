package projet_IA;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

public static final Random RANDOM=new Random();// Saisie D'un nombre Aleatoire pour l'ordinateur
@SuppressWarnings("static-access")
public static void main(String[] args)
{
Planche P1=new Planche();//Instanciation des joueurs
@SuppressWarnings("resource")
Scanner sc=new Scanner(System.in);
P1.displayBoard();// Appel de la fonction displayBoard
System.out.println("Selectionner le tour: 1- Ordinateur(X)   2- Utilisateur (O)");
int choix=sc.nextInt();
if(choix==P1.Joueur_x)// Tour de l'ordinateur
{
	Point p=new Point(RANDOM.nextInt(3),RANDOM.nextInt(3));
	P1.placeAMove(p,Planche.Joueur_x);
	P1.displayBoard();
}
while(!P1.GameOver())// Tant que la partie n'est pas achevée les joueurs peuvent avancer
{
	boolean Move2=true;
try {	do
	{
		if(!Move2)
		{
			System.out.println("Case deja remplie");
		}
		System.out.println("Votre tour : ");
		Point Mouvement=new Point(sc.nextInt(),sc.nextInt());
		Move2=P1.placeAMove(Mouvement, Planche.Joueur_Y);
		
	}while(!Move2);
	P1.displayBoard();
	if(P1.GameOver())// Si l'un des joueurs perds la partie s'achève
		break;
	P1.minimax(0,Planche.Joueur_x);// Appel de la fonction L'algorithme MiniMax
	System.out.println("Choisie une position :"+P1.Move);
	P1.placeAMove(P1.Move, Planche.Joueur_x);
	
	P1.displayBoard();
}
catch(NullPointerException e){
	System.out.println();
}
}
// Affichage si j'ai gagné ou perdu ou  un match nul
if(P1.PlayerWon(Planche.Joueur_x))
	System.out.println("Vous avez perdu !");
else if (P1.PlayerWon(Planche.Joueur_Y))
	System.out.println("Vous Avez Gagné ! ");
else
	System.out.println("Match Nul !");
}
}