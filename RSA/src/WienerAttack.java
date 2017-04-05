import java.math.BigInteger;

public class WienerAttack {
	
	private BigInteger e;
	private BigInteger n;
	
	WienerAttack(BigInteger e,BigInteger n) {
		this.e=e;
		this.n=n;
	}
	
    public void attack() {
    	
    	
    	BigInteger aux = null;
    	
    	BigInteger q,r,a,b,alfa0,alfa1,beta0,beta1;
    	a=e;
    	b=n;
    	q=a.divide(b);
    	r=a.remainder(b);
    	a=b;
    	b=r;
    	
    	alfa0=BigInteger.ONE;
    	alfa1=q;
    	beta0=BigInteger.ZERO;
    	beta1=BigInteger.ONE;
    	
    	while (!criteria(alfa1,beta1)) {
    		
    		//generez qi din a b q r precedent
    		q=a.divide(b);
    		r=a.remainder(b);
    		a=b;
    		b=r;
    		
    		//daca r == 0 atunci am ajuns la ultimul element al fractiei 
    		
    		if (r.compareTo(BigInteger.ZERO)==0) 
    			break;
    		
    		aux=q.multiply(alfa1).add(alfa0);
    		alfa0=alfa1;
    		alfa1=aux;
    		
    		aux=q.multiply(beta1).add(beta0);
    		beta0=beta1;
    		beta1=aux;
    		
    	}
    	
    	if (criteria(alfa1,beta1)) {
    		System.out.println("atac reusit \n"+beta1);
    	}  else 
    		 System.out.println("atac esuat");
    	
    }
    
    public boolean criteria(BigInteger l,BigInteger d) {
    	
    	if (l.compareTo(BigInteger.ZERO)==0) return false; //am impartire la zero dar din formula cazul in care l=0 implica n=0 deci false
    	
    	BigInteger phi = d.multiply(e).add(new BigInteger("-1")).divide(l);
    	
    	// stiu e si d si l deci pot calcula presupusul phi(n) si rezolvand ecuatia x^2-(n-phi+1)x+n=0 obtin factorizarea lui n
    	
    	BigInteger a = n.subtract(phi).add(new BigInteger("1"));
    	
    	BigInteger delta=a.multiply(a);
    	delta=delta.subtract(n.multiply(new BigInteger("4")));
    	
    	if (delta.compareTo(BigInteger.ZERO)==1) {
    		//avem solutii reale
    		BigInteger sqrt = Square(delta);
    		if (sqrt!=null) {
    			//avem solutii intregi
    			BigInteger x1 = a.add(sqrt).divide(new BigInteger("2"));
    			BigInteger x2 = a.subtract(sqrt).divide(new BigInteger("2"));
    			System.out.println(x1+" "+x2);
    			if (x1.multiply(x2).compareTo(n)==0) {
    				return true;
    			}
              return false;
    		}
    	} else return false;
    	
    	return false;
    	
    }
    
    
    public static BigInteger Square(BigInteger a) {
    	BigInteger s = new BigInteger("0");
    	BigInteger b2 = new BigInteger("2");
    	BigInteger d = new BigInteger(a.toByteArray());
    	while (s.compareTo(d)<=0) {
    		BigInteger m=s.add(d).divide(b2);
    		if (m.multiply(m).compareTo(a)==0)
    			return m;
    		if (m.multiply(m).compareTo(a)<0) 
    			s=m.add(BigInteger.ONE); else d=m.subtract(BigInteger.ONE);
    	}
    	return null;
    }
    
    
}
