#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

double abs(double a) {
 if (a<0) 
    return -a;
   return a;
}


using namespace std;


char _text_criptat[3000000];
int lungime_cheie;


double p[]={0.08167,0.01492,0.02782,0.04253,0.12702,0.02228,0.02015,0.06094,0.06966,0.00153,0.00772,0.04025,0.02406,0.06749,0.07507,0.01929,0.00095,0.05987,0.06327,0.09056,0.02758,0.00978,0.02360,0.00150,0.01974,0.00074};

double EPS=0.03;
const double IC_ENG=0.0686;


int determina_lungimea_cheii() {
	int m=0;
	int ok=1;
	int n=strlen(_text_criptat);
	
	int freq[30];
	int * sol_curr= (int *) malloc(109*sizeof(int));
	int * sol= (int *) malloc(109*sizeof(int) );
	int * aux;
	sol[0]=100;
	double IC[100];
	int len;
	//presupun ca lungimea cheii nu depaseste 100 
	//o sa accept cu o anumita probabilitate mai multe lungimi probabile 
	//si o sa micsorez probabilitatea de acceptare pana cand o sa am o cheie la pasul curant astfel incat la pasul urmator 
	//sa nu mai accept niciuna
	while (sol[0]>0) {
	m=0;
	sol[0]=0;
	while (m<100) {
		m++;
	    memset(IC,0,sizeof(IC));
	    
	 	for (int i=0;i<m;i++) {
			
			memset(freq,0,sizeof(freq));
			for (int j=i;j<n;j+=m) {
				freq[_text_criptat[j]-'A']++;
			}
			if (i==0) len=n/m; else len=(n+m-i)/m;
			for (int j=0;j<26;++j) 
			  IC[i]+=(1.0*freq[j]*(freq[j]-1))/(1.0*len*(len-1));   
		}
		
		ok=0;
		for (int i=0;i<m;++i)
		 if (abs(IC[i]-IC_ENG)>EPS) { 
			 ok++;
		 }	
		 if (ok==0)   
		 sol[++sol[0]]=m;
	}
	EPS-=0.0005;
	if (sol[0]>0) {
	aux=sol_curr;
	sol_curr=sol;;
	sol=aux;
	sol[0]=sol_curr[0];
   }
}	

	return sol_curr[1];
}

int s;
int freq[300];
double MIC;
int len;
int n=strlen(_text_criptat);

void determina_cheia(int m) {
		
	int n=strlen(_text_criptat);
	EPS=0.009;

	
	for (int i=0;i<lungime_cheie;++i) {
		s=-1;	
		
		while (s<=25) {
		   s++;
		   MIC=0;
		   memset(freq,0,sizeof(freq));
		   for (int j=i;j<n;j+=lungime_cheie) {
			   freq[(_text_criptat[j]-'A'+s) % 26]++;
		   }
		   if (i==0) len=n/lungime_cheie; else len=(n+lungime_cheie-i)/lungime_cheie;
		   
		   for (int j=0;j<26;++j)
		    MIC+=p[j]*(freq[j]/(1.0*len));
		    
		    
		   if (abs(MIC-0.066)<EPS) {
			   cout<<(char)((26-s) % 26 + 'A');
			   break;
		   }	   	   
		  
	   }		  
		 
	}
	
}

void citesc_textul_din_fisier(const char * fisier,char * _text) {
	
	int text = open(fisier,O_RDONLY);
	char c[1024]; 
  
  while (read(text,&c,1023)>0) {
	  strcat(_text,c);
	  memset(c,0,1024);
  }
 
  close(text);
}

int main() {
	
	citesc_textul_din_fisier("cryptotext",_text_criptat);
	lungime_cheie=determina_lungimea_cheii();
	
	cout<<lungime_cheie<<'\n';
    determina_cheia(lungime_cheie);
    cout<<'\n';

	return 0;
}


