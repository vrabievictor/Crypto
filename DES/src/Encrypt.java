
public class Encrypt {
  
    
	private int index;
	private long c,d;
    
    Encrypt(long key) {
    	key=DESBoxes.PC1(key);
    	d=(key & (DESBoxes.power2[28]-1));
    	c=(key>>28)&(DESBoxes.power2[28]-1);
    	index=0;
    }
    
    public long encrypt(long msg) {
    	index=0;
    	msg=DESBoxes.IP(msg);
    	    	
    	long r0=msg&((((long)1)<<32)-1);
    	long l0=(msg>>32)&((((long)1)<<32)-1);
    	long l,r;
    	
    	for (int i=0;i<15;++i) {
    		l=r0;
    		long b=nextKey();
    		r=l0^(DESBoxes.P(DESBoxes.SBoxes(DESBoxes.expansion(r0)^b)));    		
    		l0=l;
    		r0=r;
    	}
    	
    	r=r0;
    	l=l0^(DESBoxes.P(DESBoxes.SBoxes(DESBoxes.expansion(r0)^nextKey())));
    	
    	
    	return DESBoxes.IPprime(l<<32 | r);
    }
    
    
    
    private long nextKey() {
    	c=DESBoxes.leftShift(c);
    	d=DESBoxes.leftShift(d);
    	index++;
    	   	 	
    	if (!(index == 1 || index == 2 || index == 9 || index == 16)) {
    		c=DESBoxes.leftShift(c);
    		d=DESBoxes.leftShift(d);
    	}
    	return DESBoxes.PC2((c<<28)|d);
    }

}
