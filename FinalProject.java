/*
 * Final Project Imara Bhanji
 * Periodic table game
 * It's a simple question and answer game the player is given
 * the element abriviation is given and the player must answer with the correct atomic weight and element name.
 * Then in the second part of the game, the user is given a different type of question that they must answer
 *
 */
import java.io.*;//for files
import java.util.*;//for scanner

public final class FinalProject {//class name
	 public static void main(String[] args) throws FileNotFoundException{//main method that throws file error
		File elements = new File("elements.txt");//brings in file
		Scanner input = new Scanner(elements);	//defines scanner
		
		Element[] ements = new Element[118];//puts them in array
		int i = 0;//used for the while loop so that it will read
		double atomicmass;//defines the properties of file
		String elname;
		String abbr;
		int line;
		while(input.hasNext()){// reads the file
			atomicmass = input.nextDouble();
			elname = input.next();
			abbr = input.next();
			line = input.nextInt();
			ements[i++] = new Element(atomicmass, elname, abbr, line);
		}
		
		PeriodicGame(ements);// Calls method with our elements
		
	}
	
	public static void PeriodicGame (Element[] ements)  {// feeds in elements
        Scanner input = new Scanner(System.in);//defines scanner
				
			System.out.println("What is your name?"); 
			String name = input.nextLine();// asks their name
			System.out.println("\nWelcome to the Chemistry Game " +name+ " lets begin!");
			System.out.println("\nType 'yes' if you would like to play, type 'no' if you would like to exit!");			
			String u = input.nextLine(); // continues the game
			double f;//defines f as a double
		 if (u.equals("no")){//Asks the user if they want to play the game, if they type no, it ends
			System.out.println("Thank you for playing " +name+ ", have a great day!");
			}	
		 while (!u.equals("no")){ 
			  if (u.equals("yes")){ //if the user types yes then it continues on to the next part of the game
				  System.out.println("\n\nThe questions will be asked one at a time, if you think that the answer does not exist, please type '0'.\n\nThere are two games that you can play. Unfortunately to keep the secrecy of the quizzes protected, you may not know the format of the questions.\n\nPlese type '1' to continue to the first game!\n\nIf you would like to play the second game type '2'!");
				 f = input.nextInt();//
					if (f == 1){ //it runs the first quiz when 1 is entered
						int a = (int)(Math.random() * 118);//random
						Element x = ements[a];// randomizes the element
						System.out.println("\nThe computer has found a question worthy of your time, what is the atomic weight of " + x.elname + "?");	//print comp choicer 
						f = input.nextDouble();//input
						amcorrect(f, x);//answer
						System.out.println("\nAlso what is the abbreivation of the element " + x.elname + "?");
						String ans = input.next();//input
						abcorrect(ans, x);//answer
					}
					if (f == 2){ //it runs the second quiz when 2 is entered
						int b = (int)(Math.random() * 118); //random 
						Element z = ements[b];// randomizes the element
						int k;//defines integes
						System.out.println("\nGiven the atomic mass:" + z.atomicmass + " what is the element with that atomic mass? (give the full element name)");	//print comp choicer 
						String ans = input.next();//question 1 input
						elcorrect(ans, z);//answer
						System.out.println("\nWhat is the atomic number given: " + z.atomicmass + " and "+ z.elname + " ?");
						k = input.nextInt();// input 
						linecorrect(k, z);//answer
					
					}
			}
			System.out.println("\nType 'yes' if you would like to play, type 'no' if you would like to exit!");
			u =input.next();  // allows for the user to repeat the game as many times as desired
			if (u.equals("no")){
			System.out.println("Thank you for playing " +name+ ", have a great day!"); // the program continues until user wants to quit and enters no
			}	
		
	}
	}
	public static void amcorrect(double playerinput, Element x) { //win method for atomic mass
		boolean winner = false; // sets win to false
		if (playerinput == x.atomicmass) // prints win statement
			System.out.println("\nCongratulations! That was correct, we will move on to the next question");
		else { //prints lose statement
			System.out.println("\nMy apologies, please train harder and try again, that was incorrect.\nThe correct answer is "+x.atomicmass+"!");

		}
	}
	public static void abcorrect(String pt, Element z) { //win method for atomic mass for abbreviation
		boolean winner = false; // sets win to false
		if (pt == z.abbr) // prints win statement
			System.out.println("\nCongratulations! That was correct, we will move on to the next question");
		else { //prints lose statement
			System.out.println("\nMy apologies, please train harder and try again, that was incorrect.\nThe correct answer is "+z.abbr+"!");

		}
	}
	public static void elcorrect(String el, Element x) { //win method for element name
		boolean winner = false; // sets win to false
		if (el == x.elname)// prints win statement
			System.out.println("\nCongratulations! That was correct, we will move on to the next question");
		else { //prints lose statement
			System.out.println("\nMy apologies, please train harder and try again, that was incorrect.\nThe correct answer is "+x.elname+"!");

		}
	}
	public static void linecorrect(int l, Element z) { //win method for atomic number
		boolean winner = false; // sets win to false
		if (l == z.line)// prints win statement
			System.out.println("\nCongratulations! That was correct, we will move on to the next question");
		else { //prints lose statement
			System.out.println("\nMy apologies, please train harder and try again, that was incorrect.\nThe correct answer is "+z.line+"!");

		}
	}	
}
