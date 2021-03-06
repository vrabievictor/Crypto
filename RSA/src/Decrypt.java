import java.math.BigInteger;


public class Decrypt {
    
	private BigInteger privateKey;
	private BigInteger n;
	
	Decrypt(BigInteger privateKey,BigInteger n) {
		this.privateKey=privateKey;
		this.n=n;
	}
	
	public BigInteger decrypt(BigInteger msg) {
		return msg.modPow(privateKey, n);
	}
}
