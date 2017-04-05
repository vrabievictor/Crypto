import java.math.BigInteger;



public class Encrypt {
   
	public BigInteger publicKey;
	public BigInteger n;
	
	Encrypt(BigInteger publicKey,BigInteger n) {
		this.publicKey=publicKey;
		this.n=n;
	}
	
	public BigInteger encrypt(BigInteger msg) {
      return msg.modPow(publicKey, n);
	}
	
}
