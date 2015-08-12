import java.io.*;
import java.util.*;
public class Jatek implements Constants{
public static int[][] tabla, prior;
public static ArrayList toplist = new ArrayList(10);
	public static void ujjatek(int betolt) //
	{
	String topbe;
	tabla = new int[sor1+5][osz1+5];
	prior = new int[sor1][osz1];
	topbe= new String("          ");
	topbetolt(0);
	if(betolt==0){
		for(int cv=0; cv<12; cv++ ){
			for(int cv2=0; cv2<12; cv2++){
				tabla[cv][cv2]=0;
			}
		}
	}
	else{  //mentett játék betöltése
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("amoba.sav")));
			String ss;
			char sstomb[];
			for(int cv=0; cv<sor1; cv++ ){
				ss=br.readLine();
				sstomb=ss.toCharArray();
				for(int cv2=0; cv2<osz1; cv2++){
					tabla[cv][cv2]=Kiirat.integerre(sstomb[cv2])-48;
				}
				System.out.println(ss+" ");
			}
			br.close();
		}
		catch (IOException e) {
			System.out.println("Hiba tortent: "+e);
		}
	}
	char kilep;
	int vege=1, lepesszam=0, lszam=0;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  //az InputStream reader a hid a ket megoldas kozt
	do{
		lszam++;
		char xx1;
		char yy1;
		int x1, y1;
		tablaki();
		//priorki(); //prioritástábla
		System.out.println("Kerem az X kordinata erteket:");
		xx1=Kiirat.inChar();
		System.out.println("Kerem az Y kordinata erteket:");
		yy1=Kiirat.inChar();
		x1=Kiirat.integerre(xx1);
		y1=Kiirat.integerre(yy1);
		x1=x1-49;
		y1=y1-49;
		System.out.println("X: "+x1+" Y: "+y1);
		if(x1==64 || y1==64 ){
			vege=0;
		}
		if(x1==66 || y1==66 ){
			mentes();
			System.out.println("A jatek allas elmentve!");
		}
		if(x1<9 && x1>=0 && y1<9 && y1>=0){
			if(tabla[y1][x1]==1 || tabla[y1][x1]==2){
				System.out.println("Ide nem tehetsz X-et");
			}
			else{
				tabla[y1][x1]=1;
				if(WinsGame4(y1,x1)!=1){
					priornul();
					DoMove();
					lepesszam++;
					if(ellenrak()==1){
						tablaki();
						System.out.println("Vesztettel!");
						vege=0;
					}
				}
				else{
					vege=0;
					tablaki();
					System.out.println("Nyertel!\nAdd meg a neved hogybekerulj a toplistaba:");
					try{
						topbe=br.readLine();
					}
					catch (IOException e) {
						System.out.println("Hiba tortent: "+e);
						}
						toplist.add(new Toposz(topbe));
						topment();
						return;
					}
				}
			}
			else{
				if(x1==113 || y1==113){
					vege=0;
				}
				System.out.println("Kerem a megadott szamok 1-9-ig legyenek");
			}
			if(lepesszam>=40){
				vege=0;
				tablaki();
				System.out.println("A tabla betelt, dontetlen!");
			}
		}
		while(vege!=0);
	}
	protected static int topbetolt(int a) //toplista betöltése
	{
		int cv=0;
		toplist.clear();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("players.top")));
			String ssa;
			ssa= new String("          ");
			while(cv<9 && (ssa=br.readLine())!=null){
			    if(a==0) toplist.add(new Toposz(ssa));
				if(a==1) toplist.add(ssa);

			cv++;
			}
			br.close();
		}
		catch (IOException e) {
			System.out.println("Hiba tortent: "+e);
		}
		return cv;
	}
public static void topmegnez()  //toplista Kiiratása
	{
		int asize;
		asize=topbetolt(1);
		/*int cv=0;
		while(cv<asize){
			System.out.println(toplist.get(cv));
			cv++;
		}*/
		ArrayList backup = new ArrayList(toplist.size());
		backup.addAll(toplist);
		System.out.println(new Rendezo().order(toplist));
		/*for(Iterator it = toplist.iterator(); it.hasNext()==true;){
			((Toposz) it.next()).nev();
			//System.out.println(it.next());
		}*/
	}
protected static void topment() //toplista mentése
	{	int asize;
		asize=toplist.size();
		int cv=0;
		//Collections.sort(toplist, new Rendezo());

		try {
			FileOutputStream outStream= new FileOutputStream("players.top");
			PrintWriter kifele=new PrintWriter(outStream);
			/*while(cv<asize){
				kifele.println(toplist.get(cv));
				cv++;
			}*/
			for(Iterator it = toplist.iterator(); it.hasNext()==true;){
				kifele.println(((Toposz) it.next()).nev2());
						//System.out.println(it.next());
		}

			kifele.close();
		}
		catch (IOException e) {
			System.out.println("Hiba tortent: "+e);
		}
	}
 protected static void mentes(){ //Játékállás mentése
		try {
			FileOutputStream outStream= new FileOutputStream("amoba.sav");
			PrintWriter kifele=new PrintWriter(outStream);
			for(int cv=0; cv<sor1; cv++ ){
				for(int cv2=0; cv2<osz1; cv2++){
					kifele.print(tabla[cv][cv2]);
				}
				kifele.print("\n");
			}
			kifele.close();
		}
		catch (IOException e) {
			System.out.println("Hiba tortent: "+e);
		}
	}
	protected static void tablaki(){ // Játéktábla kirajzolása
		int osz;
		System.out.println("0 1 2 3 4 5 6 7 8 9");
		for(int cv=0; cv<9; cv++ ){
			osz=cv+1;
			for(int cv2=0; cv2<9; cv2++){
				if(tabla[cv][cv2]==0)
					if(cv2==0){
						System.out.print(osz+"   ");
					}
					else{
						System.out.print("  ");
					}
				if(tabla[cv][cv2]==1)
					if(cv2==0){
						System.out.print(osz+" X ");
					}
					else{System.out.print("X ");
				}
				if(tabla[cv][cv2]==2)
					if(cv2==0){
						System.out.print(osz+" O ");
					}
					else{
						System.out.print("O ");
					}
				}
				System.out.print("\n");
			}
		}
	protected static void priorki(){ //prioritástábla Kiiratása a képernyõre
		for(int cv=0; cv<9; cv++ ){
			for(int cv2=0; cv2<9; cv2++){
				System.out.print(prior[cv][cv2]+" ");
			}
			System.out.println(" ");
		}
	}
	protected static void priornul(){  //prioritástábla nullázása
		for(int cv=0; cv<9; cv++ ){
			for(int cv2=0; cv2<9; cv2++){
				prior[cv][cv2]=0;
			}
		}
	}
	protected static int WinsGame( int i0, int j0 ) {
		int i, j;
		/* lenne-e 3 egymás mellett*/
		i = i0;
		j = j0-3; if( j < 0 ) j = 0;
		while( (j <= j0) && (j <= 4) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i][j+1] == tabla[i0][j0]) &&
				(tabla[i][j+2] == tabla[i0][j0]) &&
				(tabla[i][j+3] == tabla[i0][j0]) )
				return 1;
			j++;
		}
		j = j0;
		i = i0-3; if( i < 0 ) i = 0;
		while( (i <= i0) && (i <= 4) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j] == tabla[i0][j0]) &&
				(tabla[i+2][j] == tabla[i0][j0]) &&
				(tabla[i+3][j] == tabla[i0][j0]) )
				return 1;
			i++;
		}
		j = j0-3; i = i0-3;
		while( (j < 0) || (i < 0) ) { j++; i++; };
		while( (i <= i0) && (i <= 4) && (j <= j0) && (j <= 4) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j+1] == tabla[i0][j0]) &&
				(tabla[i+2][j+2] == tabla[i0][j0]) &&
				(tabla[i+3][j+3] == tabla[i0][j0]) )
				return 1;
			j++; i++;
		}
		j = j0+3; i = i0-3;
		while( (j < 0) || (i < 0) ) { j--; i++; };
		while( (i <= i0) && (i <= 4) && (j >= j0) && (j >= 4) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j-1] == tabla[i0][j0]) &&
				(tabla[i+2][j-2] == tabla[i0][j0]) &&
				(tabla[i+3][j-3] == tabla[i0][j0]) )
				return 1;
			j--; i++;
		}
		return 0;
	}
	public static int WinsGame2( int i0, int j0 ) {
		int i, j;
		/* Lenne-e 4 darab egymás mellett?*/
		i = i0;
		j = j0-3; if( j < 0 ) j = 0;
		while( (j <= j0) && (j <= 3) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i][j+1] == tabla[i0][j0]) &&
				(tabla[i][j+2] == tabla[i0][j0]) &&
				(tabla[i][j+3] == tabla[i0][j0]) )
				return 1;
			j++;
		}
		j = j0;
		i = i0-3; if( i < 0 ) i = 0;
		while( (i <= i0) && (i <= 3) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j] == tabla[i0][j0]) &&
				(tabla[i+2][j] == tabla[i0][j0]) &&
				(tabla[i+3][j] == tabla[i0][j0]) )
				return 1;
			i++;
		}
		j = j0-3; i = i0-3;
		while( (j < 0) || (i < 0) ) { j++; i++; };
		while( (i <= i0) && (i <= 2) && (j <= j0) && (j <= 3) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j+1] == tabla[i0][j0]) &&
				(tabla[i+2][j+2] == tabla[i0][j0]) &&
				(tabla[i+3][j+3] == tabla[i0][j0]) )
				return 1;
			j++; i++;
		}
		j = j0+3; i = i0-3;
		while( (j < 0) || (i < 0) ) { j--; i++; };
		while( (i <= i0) && (i <= 3) && (j >= j0) && (j >= 3) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j-1] == tabla[i0][j0]) &&
				(tabla[i+2][j-2] == tabla[i0][j0]) &&
				(tabla[i+3][j-3] == tabla[i0][j0]) )
				return 1;
			j--; i++;
		}
		return 0;
	}
	protected static int WinsGame4( int i0, int j0 ) {
			int i, j;
			/* Nyertne-e? kijött-e 5 darab egymás mellett?*/
			i = i0;
			j = j0-4; if( j < 0 ) j = 0;
			while( (j <= j0) && (j <= 4 && j<9) ) {
				if( (tabla[i][j] == tabla[i0][j0]) &&
					(tabla[i][j+1] == tabla[i0][j0]) &&
					(tabla[i][j+2] == tabla[i0][j0]) &&
					(tabla[i][j+3] == tabla[i0][j0])  &&
					(tabla[i][j+4] == tabla[i0][j0]))
					return 1;
				j++;
			}
			j = j0;
			i = i0-4; if( i < 0 ) i = 0;
			while( (i <= i0) && (i <= 4 && i<9) ) {
				if( (tabla[i][j] == tabla[i0][j0]) &&
					(tabla[i+1][j] == tabla[i0][j0]) &&
					(tabla[i+2][j] == tabla[i0][j0]) &&
					(tabla[i+3][j] == tabla[i0][j0])  &&
					(tabla[i+4][j] == tabla[i0][j0]))
					return 1;
				i++;
			}
			j = j0-4; i = i0-4;
			while( (j < 0) || (i < 0) ) { j++; i++; };
			while( (i <= i0) && (i <= 4) && (j <= j0) && (j <= 4) ) {
				if( (tabla[i][j] == tabla[i0][j0]) &&
					(tabla[i+1][j+1] == tabla[i0][j0]) &&
					(tabla[i+2][j+2] == tabla[i0][j0]) &&
					(tabla[i+3][j+3] == tabla[i0][j0])  &&
					(tabla[i+4][j+4] == tabla[i0][j0]))
					return 1;
				j++; i++;
			}
			j = j0+4; i = i0-4;
			while( (j < 0) || (i < 0) ) { j--; i++; };
			while( (i <= i0) && (i <= 3) && (j >= j0) && (j >= 3) ) {
				if( (tabla[i][j] == tabla[i0][j0]) &&
					(tabla[i+1][j-1] == tabla[i0][j0]) &&
					(tabla[i+2][j-2] == tabla[i0][j0]) &&
					(tabla[i+3][j-3] == tabla[i0][j0])  &&
					(tabla[i+4][j-4] == tabla[i0][j0]))
					return 1;
				j--; i++;
			}
			return 0;
	}
protected static int WinsGame3( int i0, int j0 ) {
		int i, j;
		/* Tesztelés: ha 3 darab lenne egymás mellett */
		i = i0;
		j = j0-2; if( j < 0 ) j = 0;
		while( (j <= j0+2) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i][j+1] == tabla[i0][j0]) &&
				(tabla[i][j+2] == tabla[i0][j0]))
				return 1;
			j++;
		}
		j = j0;
		i = i0-2; if( i < 0 ) i = 0;
		while( (i <= i0+2) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j] == tabla[i0][j0]) &&
				(tabla[i+2][j] == tabla[i0][j0]))
				return 1;
			i++;
		}
		j = j0-2; i = i0-2;
		while( (j < 0) || (i < 0) ) { j++; i++; };
		while( (i <= i0) && (i <= 6) && (j <= j0) && (j <= 6) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j+1] == tabla[i0][j0]) &&
				(tabla[i+2][j+2] == tabla[i0][j0]))
				return 1;
			j++; i++;
		}
		j = j0+2; i = i0-2;
		while( (j < 0) || (i < 0) ) { j--; i++; }
		while( (i <= i0) && (i <= 6) && (j >= j0) && (j >= 6) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j-1] == tabla[i0][j0]) &&
				(tabla[i+2][j-2] == tabla[i0][j0]))
				return 1;
			j--; i++;
		}
		return 0;
	}
protected static int WinsGame5( int i0, int j0 ) {
		int i, j;
		/* Tesztelés hogy ha ide tesz van-e mellette másik */
		i = i0;
		j = j0-1; if( j < 0 ) j = 0;
		while( (j <= j0+1) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i][j+1] == tabla[i0][j0]))
				return 1;			j++;
		}
		j = j0;
		i = i0-1; if( i < 0 ) i = 0;
		while( (i <= i0+1) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j] == tabla[i0][j0]))
				return 1;
			i++;
		}
		j = j0-1; i = i0-1;
		while( (j < 0) || (i < 0) ) { j++; i++; };
		while( (i <= i0) && (i <= 9) && (j <= j0) && (j <= 9) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j+1] == tabla[i0][j0]))
				return 1;
			j++; i++;
		}
		j = j0+1; i = i0-1;
		while( (j < 0) || (i < 0) ) { j--; i++; }
		while( (i <= i0) && (i <= 9) && (j >= j0) && (j >= 9) ) {
			if( (tabla[i][j] == tabla[i0][j0]) &&
				(tabla[i+1][j-1] == tabla[i0][j0]) )
				return 1;
			/* ha nemsikerül tovább próbáljon */
			j--; i++;
		}
		/* Ha nincs ilyen sorozat */
		return 0;
	}
	protected static void DoMove() {
	int i, j, cv, cv2, Player=2, muveveg=1;
	/* DoMove: Megnézi a számítógép számára hogy ha ide tenne hány X illetve O lenne mellette
	ez alapján tölti fel adatokkal a prioritás táblázatot*/
	for(cv=0;cv<9;cv++)
		for(cv2=0;cv2<9;cv2++){
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=2;
				if(WinsGame2(cv, cv2)==1){
					prior[cv][cv2]+=20;
				}
				tabla[cv][cv2]=0;
			}
			else prior[cv][cv2]=(-1);
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=1;
				if(WinsGame2(cv, cv2)==1){
					prior[cv][cv2]+=9;
				}
				tabla[cv][cv2]=0;
			}
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=2;
				if(WinsGame(cv, cv2)==1){
					prior[cv][cv2]+=3;
				}
				tabla[cv][cv2]=0;
			}
			else prior[cv][cv2]=(-1);
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=1;
				if(WinsGame(cv, cv2)==1){
					prior[cv][cv2]+=4;
				}
				tabla[cv][cv2]=0;
			}
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=2;
				if(WinsGame3(cv, cv2)==1){
					prior[cv][cv2]+=1;
				}
				tabla[cv][cv2]=0;
			}
			else prior[cv][cv2]=(-1);
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=1;
				if(WinsGame3(cv, cv2)==1){
					prior[cv][cv2]+=2;
				}
				tabla[cv][cv2]=0;
			}
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=2;
				if(WinsGame4(cv, cv2)==1){
					prior[cv][cv2]+=21;
				}
				tabla[cv][cv2]=0;
			}
			else prior[cv][cv2]=(-1);
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=1;
				if(WinsGame4(cv, cv2)==1){
					prior[cv][cv2]+=20;
				}
				tabla[cv][cv2]=0;
			}
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=2;
				if(WinsGame5(cv, cv2)==1){
						prior[cv][cv2]+=1;
					}
					tabla[cv][cv2]=0;
				}
			else prior[cv][cv2]=(-1);
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				tabla[cv][cv2]=1;
				if(WinsGame5(cv, cv2)==1){
					prior[cv][cv2]+=1;
				}
				tabla[cv][cv2]=0;
			}
		}
	} //DoMove vége
	protected static int ellenrak(){ //ellenség lerakja a prioritastabla alapjan a 'O'-t
	int maxy=0, maxx=0, maxo=-1;
	for(int cv=0; cv<9; cv++ ){
		for(int cv2=0; cv2<9; cv2++){
			if(tabla[cv][cv2]!=1 && tabla[cv][cv2]!=2){
				if(maxo<prior[cv][cv2]){
					maxo=prior[cv][cv2];
					maxy=cv;
					maxx=cv2;
				}
			}
		}
	}
	tabla[maxy][maxx]=2;
	if(WinsGame4(maxy,maxx)==1)
		return 1;
	return 0;
	}
}