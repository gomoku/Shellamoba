import java.io.*;
import java.util.*;
public class Kiirat {  //karakter olvasas, es "laptorles"
	private static int i=0;
	public static void clear(){
		for(i=0;i<300;i++){
			System.out.println("\n");
		}
		for(i=0;i<100;i++){
			System.out.println("\r");
		}
	}
	public static char inChar() {
   		int aChar = 0;
		try
		{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	aChar = br.read();
    	} catch (IOException e) {
			System.out.println("Nem sikerult a konzolrol valo beolvasas!");
		}
		return (char) aChar;
	}
	public static int inInt() {
	    int aInt = 0;
		try
		{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    aInt = br.read();
	    } catch (IOException e) {
			System.out.println("Nem sikerult a beolvasas!");
		}
		return (int) aInt;
	}

	public static int integerre(char a) {
		return (int) a;
	}
}