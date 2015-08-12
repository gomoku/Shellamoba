import java.io.*;
import java.util.*;
public class Toposz{
	public String topnev;
	public Toposz(String i){
	topnev=i;
	}
	public void nev() {
	System.out.println(topnev);
	}
	public String nev2() {
		/*try {
			FileOutputStream outStream= new FileOutputStream("players.top");
			PrintWriter kifele=new PrintWriter(outStream);
		kifele.println(topnev);
		kifele.close();
				}
				catch (IOException e) {
					System.out.println("Hiba tortent: "+e);
		}*/
		return topnev;
	}
}