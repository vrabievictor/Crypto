
public class Decrypt {
  
	private long c,d,index;
  
  Decrypt(long key) {
	  key=DESBoxes.PC1(key);
	  c=(key>>28)&(DESBoxes.power2[28]-1);
	  d=(key)&(DESBoxes.power2[28]-1);
	  index=0;
  }
  
  public long decrypt(long msg) {
      
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
	  index++;
	  if (index>1) {
		  c=DESBoxes.rightShift(c);
		  d=DESBoxes.rightShift(d);
		  if (!(index==2 || index == 9 || index == 16)) {
			  c=DESBoxes.rightShift(c);
			  d=DESBoxes.rightShift(d);
		  }
	  }

	  return DESBoxes.PC2(c<<28 | d);
  }

}
