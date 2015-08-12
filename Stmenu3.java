import java.io.*;
import java.util.*;
public class Stmenu3 extends Stmenu2 {
  	void m1() {
    	this.m2();
	}
  	void m2() {
		this.m3();
	}
 	void m3(){
   		super.m1();
	}
}
