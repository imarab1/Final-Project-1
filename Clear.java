/**
 * @author Adrian Veliz
 * Example code that may be helpful in your Final Project.
 */

import java.io.IOException;
public class Clear{
	public static void main(String[]args)throws IOException, InterruptedException{
		System.out.println("hello");
		sleep(1);
		clearScreen();
		System.out.print("hello again");
		sleep(1);
		clearLine();
		sleep(1); // If the program terminates before sleep, you won't see clearLine's effect.
	}
	
	/**
	 * Clears the terminal by invoking the environment's clear command.
	 * Differs between Windows and Unix
	 * 
	 * @see <a href="https://stackoverflow.com/questions/2979383/java-clear-the-console">java clear the console</a>
	 */
	public static void clearScreen() {
		try{//windows
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch(Exception e){//mac and linux
			try{
				new ProcessBuilder("clear").inheritIO().start().waitFor();
			}catch(Exception e2){}
		}
	}
	
	/**
	 * Delete the characters on a line.
	 * Has no affect if called after a println.
	 * Default is 80 characters. 
	 * 
	 * @see <a href="https://stackoverflow.com/questions/7522022/how-to-delete-stuff-printed-to-console-by-system-out-println">delete stuff</a>
	 */
	public static void clearLine(){
		clearLine(80);
	}
	/**
	 * Delete the characters on a line.
	 * @param length The number of characters to delete.
	 */
	public static void clearLine(int length){
		for(int i =0; i < length;i++){
			System.out.print("\b \b"); // backspace, blank, backspace
		}
		System.out.flush();
	}
	
	/**
	 * Sleep for some number of seconds.
	 * 
	 * @param seconds The number of seconds to sleep.
	 * @see <a href="https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java">make a delay in java</a>
	 */
	public static void sleep(int seconds){
		try{
			Thread.sleep(1000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
