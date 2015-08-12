import java.io.*;
import java.util.*;

/**************************************
Készítette Mester Gábor
h866305
MEGQABT.SZE
**************************************/

public class amoba {
	public static void main(String[] args)
 	{
		char ertek;
		do{
		ertek=Kiirat.inChar();
		Kiirat.clear();
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("+ Amoba Jatek kotelezo program +");
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("+ Keszitette: Mester Gabor     +");
		System.out.println("++++++++++++++++++++++++++++++++");
		System.out.println("+ Menu valasztas:              +");
		System.out.println("+        1. Jatek inditasa     +");
		System.out.println("+        2. Jatek betoltese    +");
		System.out.println("+        3. Info               +");
		System.out.println("+        4. Toplista           +");
		System.out.println("+        q. Kilepes            +");
		System.out.println("++++++++++++++++++++++++++++++++");
         switch(ertek)
	     {
			 case '1': System.out.println("A jatek indul");
					   Stmenu3 o1= new Stmenu3();
			 		   o1.m1();
			 		   break;
			 case '2': System.out.println("A mentett jatek betoltese");
					   Stmenu2 o2= new Stmenu2();
			 		   o2.m2();
			 		   break;
		 	 case '3': System.out.println("Szimpla amoba(Five-in-a-row, Gomoku) Jatek\n A human Jatekos az 'X', a gep 'O', a Jatekban az nyer aki elobb kirak 5-ot vizszintes fuggoleges vagy atlos iranyban\nKilepni a 'q', menteni az 's' betuk lenyomasaval lehet amikor keri a Jatek a kordinatakat.\n Jo Jatekot!");
		 	 		   break;
			 case '4': System.out.println("Toplista");
			  		   Jatek.topmegnez();
					   break;
		 	}
        	System.out.println("A beolvasott elso ertek: "+ertek);
		}
		while(ertek!='q');
	}
}





