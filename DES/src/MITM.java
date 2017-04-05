import java.util.HashMap;

public class MITM {
   
	private long cryptotext,k18,k28,plaintext;
	
	private HashMap<Long,Long> mp = new HashMap<Long,Long>(); 
	
	MITM(long text,long crypto,long k18,long k28) {
		
		//din motive de performante presupun ca cunosc primii 48 de biti din ambele chei
		cryptotext=crypto;
		plaintext=text;
		this.k18=k18;
		this.k28=k28;
	}

	public void  attack() {
		for (long i=0;i<256;++i) 
			mp.put(new Encrypt((k28<<8 | i)).encrypt(plaintext), (k28<<8 | i));
		
		for (long i=255;i>=0;--i)
			if (mp.get(new Decrypt((k18<<8 | i)).decrypt(cryptotext))!=null)  {
				System.out.println("atac reusit");
				System.out.println(Long.toHexString(mp.get(new Decrypt((k18<<8 | i)).decrypt(cryptotext))));
				System.out.println(Long.toHexString((k18<<8 | i)));
				return;
			}
		System.out.println("atac esuat");
	}
	
	
	
}
