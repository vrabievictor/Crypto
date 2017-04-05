import java.math.BigInteger;


public class Main {
   
	public static void main(String [] args) {
		
		Encrypt e = new Encrypt(Keys.e,Keys.n);
		
		Decrypt d = new Decrypt(Keys.d,Keys.n);
		
		DecryptCRT dTCR = new DecryptCRT(Keys.p,Keys.q,Keys.n,Keys.d);
		
		
		BigInteger a = new BigInteger("123444474747474744");
		//criptarea 
		
		BigInteger txt= e.encrypt(a);
		
		//decriptarea
		System.out.println(d.decrypt(txt));
		//decriptare cu TCR
		System.out.println(dTCR.decrypt(txt));
		
		
		//acest exemplu ilustreaza atacul asupra cheii private  
		//  e=42667 n=64741 
		new WienerAttack(new BigInteger("42667"),new BigInteger("64741")).attack();
	}
}
