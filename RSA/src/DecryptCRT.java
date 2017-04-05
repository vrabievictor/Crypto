import java.math.BigInteger;


public class DecryptCRT {
     private BigInteger p;
     private BigInteger q;
     private BigInteger n;
     private BigInteger d;
     
     DecryptCRT(BigInteger p,BigInteger q,BigInteger n,BigInteger d){
    	 this.p=p;
    	 this.q=q;
    	 this.n=n;
    	 this.d=d;
     }
     
     public BigInteger decrypt(BigInteger msg) {
    	// m1 = (m mod p)^(d mod (p-1)) mod p
        // m2 = (m mod q)^(d mod (q-1)) mod q
    	//calculez solutia 
    	 
    	// m=m1 mod p
    	// m=m2 mod q
    	
    	//m=(m1*(q^(-1) mod p)*q +m2*(p^(-1) mod q)*p )mod n
    	
    	//formula lui Garner
    	//m=m2+(q^(-1) mod p)*(m1-m2);
    	 
    	 
    	 BigInteger m1 = msg.mod(p).modPow(d.mod(p.add(new BigInteger("-1"))), p);
    	 BigInteger m2 = msg.mod(q).modPow(d.mod(q.add(new BigInteger("-1"))), q);
    	 
    	 return m2.add(q.modInverse(p).multiply(m1.subtract(m2))).mod(n);
     }
}
