import java.math.BigInteger;
import java.util.Random;


public class Keys {

	   
	public static BigInteger q = BigInteger.probablePrime(1024, new Random());
	public static BigInteger p = q.nextProbablePrime();
	
	public static BigInteger n = q.multiply(p);
	public static BigInteger e;
	public static BigInteger d;
	public static BigInteger phi;
	
	static {
		//calculez phi
		BigInteger aux = q.add(new BigInteger("-1"));
		aux = aux.multiply(p.add(new BigInteger("-1")));
		phi=aux;
		//caut e coprim cu phi
		
		e =new BigInteger("66889");
		
		while (!e.gcd(phi).toString().equals("1")) 
			e=e.add(new BigInteger("2"));	
		d=e.modInverse(phi);
	}
	
	
}
