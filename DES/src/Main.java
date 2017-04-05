
public class Main {
   public static void main(String[] args) {

	  
	   Encrypt a1 = new Encrypt(Long.parseLong("001100110100010101110111100110011011101111001101111111110001",2));
	   Encrypt a2 = new Encrypt(Long.parseLong("100100011010001010110111101111000100111111111",2));
	   
	   Decrypt a3 = new Decrypt(Long.parseLong("1001100110100010101110111100110011011101111001101111111110001",2));
	   Decrypt a4 = new Decrypt(Long.parseLong("100100011010001010110111101111000100111111111",2));
	   
	   long text = Long.parseLong("6A271787AB8883F9",16);
	   
	   long cryptotext = a1.encrypt(a2.encrypt(text));
	   
	   System.out.println("key 1 : "+Long.toHexString(Long.parseLong("100100011010001010110111101111000100111111111",2)));
	   System.out.println("key 2 : "+Long.toHexString(Long.parseLong("001100110100010101110111100110011011101111001101111111110001",2)));
	   
	   //System.out.println(Long.toHexString(a3.decrypt(a4.decrypt(a2.encrypt(a1.encrypt(text))))));
	   
	   
	   MITM m = new MITM(text,cryptotext,Long.parseLong("0011001101000101011101111001100110111011110011011111",2),Long.parseLong("1001000110100010101101111011110001001",2));
	   m.attack();
	   
	   
	   
	   
	   /* P = 4E6F772069732074 68652074696D6520 666F7220616C6C20
          C = 3FA40E8A984D4815 6A271787AB8883F9 893D51EC4B563B53 */
	  /*
	   Encrypt a = new Encrypt(Long.parseLong("0123456789ABCDEF",16));
	   Decrypt b = new Decrypt(Long.parseLong("0123456789ABCDEF",16));
	   
	   long ctext=(a.encrypt(Long.parseLong("666F7220616C6C20",16)));
	   System.out.println(Long.toHexString((ctext)));
	   System.out.println(Long.toHexString(b.decrypt(ctext))); */
   }
}
