#include <bits/stdc++.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>


using namespace std;

FILE * cryptotext;

string _text="";
string _text_legal="";
string cheia="";

int lungime_cheie;



void citesc_textul_din_fisier(const char * fisier) {
	
	int text = open(fisier,O_RDONLY);
	char c[1024]; 
  
  while (read(text,&c,1023)>0) {
	  _text+=c;
	  memset(c,0,1024);
  }
 
  close(text);
}


void sterg_caractere_nedorite() {
	for (unsigned i=0;i<_text.length();++i)
	  if (_text[i]>='a' && _text[i]<='z') 
	     _text_legal+=(char)((int)_text[i]-32); else 
	      if (_text[i]>='A' && _text[i]<='Z')
	          _text_legal+=_text[i];
}


void criptare() {
	for (unsigned i=0;i<_text_legal.length();++i) {
	   fprintf(cryptotext,"%c",(_text_legal[i]-'A'+cheia[i % lungime_cheie]) % 26 +'A');
   }
}



int main(int argc,char * argv[]) {
	
	if (argc<2) {
		puts("cheia se da ca argument executabilului");
		puts("nu ati introdus cheia de criptare");
		return 0;
	}
	
	for (unsigned i=0;i<strlen(argv[1]);++i)
	  if (argv[1][i]>65) 
	     cheia+=argv[1][i]-'a'; else 
	     cheia+=argv[1][i]; 
	lungime_cheie=cheia.length();

  cryptotext = fopen("cryptotext","w");
  
  citesc_textul_din_fisier("text");
  sterg_caractere_nedorite();
  criptare();
 
  fclose(cryptotext);
 return 0;
}
