package chapter1;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
public class Rehber {
	private Object[]liste;
	
	public Rehber() throws Exception {
		Scanner scanner = new Scanner(System.in);
		liste =new Object[10];
		Object []bilgi = new Object [3];
		String ad;
		String soyad;
		String telNo;
		
		File dosya = new File("telefonRehberi.txt");
		Scanner scnr = new Scanner(dosya);
		String temp;
		int a =0;
		String[] line =new String[1];
		while(scnr.hasNextLine()) {
			temp=scnr.nextLine();
			temp=temp.replace('[', ' ');
            temp=temp.replace(']', ' ');
            temp=temp.trim();
			System.out.println(temp);
			line[0]=temp;
//			liste[a]=line.clone();
			liste[a]=line.clone(); // String olarak kaydettiği için arama ve dosya yazdırırken boottrap hatası verdi.
			a++;
		}
		scnr.close();

//		FileInputStream fis  = new FileInputStream("telefonRehberi.txt");
//		InputStreamReader isr = new InputStreamReader(fis);
//		BufferedReader br = new BufferedReader(isr);
////		StringBuilder sb = new StringBuilder();
//		String temp;
//		int a=0;
//		while((temp=br.readLine()) != null) {
//				temp=temp.replace('[', ' ');
//				temp=temp.replace(']', ' ');
//				liste[a]=temp;
////				line=line.concat(temp);
////				liste = line.split("\\r\\n");
////				dizi[a]=dizi[a].replace('[', ' ');
////				dizi[a]=dizi[a].replace(']', ' ');
//				a++;
////			    liste[a]=dizi;
//		}
//		br.close();	
//		
//		System.out.println(line);
//		System.out.println(Arrays.deepToString(liste));	
		
		System.out.println("\nKayıt Menüsüne Hoşgeldiniz.");
		
		int i=0;
		int boy =0;
		if(a!=0) {
			boy=(a-1);
		}
		else {
			boy=0;
		}
		do {
		   System.out.println("\n[0] Çıkış" + "\n[1] Kayıt Ekle"+ "\n[2] Kayıt Arama"+ "\n[3] Kayıt Sil");
		   i=scanner.nextInt();
		   
		   if(i==1) {
			   System.out.println("Lütfen kayıt yapmak istediğiniz kişinin ad, soyad ve telefon numarasını giriniz.");
			   System.out.println("\nAdı: ");
			   ad=scanner.next();
			   System.out.println("\nSoyadı:");
			   soyad=scanner.next();
			   System.out.println("\nTelefon Numarası: ");
			   telNo=scanner.next();
			   bilgi[0]= ad;
			   bilgi[1]= soyad;
			   bilgi[2]= telNo;
			   while(liste[boy]!=null) {
				   boy++;
			   }
			   liste[boy]=bilgi.clone();
			   System.out.println(Arrays.deepToString((Object[]) liste[boy]));	
		   }
           if(i==2) {
        	   System.out.println("Lütfen Kayıt Bilgilerine Ulaşmak İstedeğiniz Kişinin Ad ve Soyadını Yazınız."); 
        	   String kayıtAraInfo = scanner.next();
        	   String araKontrol=null;

        	   for(int j=0; j<=boy ; j++) {
        		   String ara =Arrays.deepToString((Object[]) liste[j]);
			       ara=ara.replace(']', ' ');
			       ara=ara.replace('[', ' ');
			       ara=ara.trim();
        		   String[] bilgiYazdır=ara.split(",");
        		   
        		   System.out.println(bilgiYazdır[0] + " : " + kayıtAraInfo);

        		   if(bilgiYazdır[0]==kayıtAraInfo) {
//        		   if(bilgiYazdır[0].equalsIgnoreCase(kayıtAraInfo)) {
        			   System.out.println("\nKayıt Bilgileri: "+"\nAdı: "+bilgiYazdır[0]
            		   +"\nSoyadı: "+bilgiYazdır[1]+"\nTelefon Numarası: "+bilgiYazdır[2]);
        		   }
        	   }
        	   

//        	   for(int j=0; j<=boy ; j++) {
//        		   String ara =Arrays.deepToString((Object[]) liste[j]);
//            	   if(ara.contains(kayıtAraInfo)) {
//    			       ara=ara.replace(']', ' ');
//    			       ara=ara.replace('[', ' ');
//        	           ara=ara.trim();
//        	           String[] bilgiYazdır=ara.split(",");
//            		   System.out.println("\nKayıt Bilgileri: "+"\n\nAdı: "+bilgiYazdır[0]
//                	   +"\nSoyadı: "+bilgiYazdır[1]+"\nTelefon Numarası: "+bilgiYazdır[2]);
//            		   araKontrol=bilgiYazdır[0];
//        	       }
//        	   }
        	   if(araKontrol==null) {
        		   System.out.println("\nAradağınız Kişinin Bilgilerine Ulaşılamamıştır.");
        	   }
           }
           if(i==3) {
        	   System.out.println("Bilgilerini Silmek İstediğiniz Kişinin Adını Yazınız.");
        	   String kayıtSilInfo =scanner.next();
        	   Object []temp1= new Object[1];
        	   Object [] temp2= new Object [boy+1];
			   int b =0;
        	   for(int j=0; j<=boy ; j++) {
        		   String sil =Arrays.deepToString((Object[]) liste[j]);
        		   if(sil.contains(kayıtSilInfo)) {
            		   continue;
        		   } 
        		   else {
        			   temp1[0] =sil;
        			   temp2[b]=(Object[])temp1.clone();
            		   b++;
        		   }
        	   }
        	   if(temp2[b]==null) {
        		   System.out.println("Kayıt Silinmiştir.");
        	   }   
        	   else {
        		   System.out.println("Silinecek Kayıt Bulunamamıştır.");
        	   }
    		   liste = temp2.clone();
        	   System.out.println(Arrays.deepToString(liste));
	       } 
           if(i==0) {
		    break;
	       }          
  		}while(i!=0); 

		FileOutputStream fos = new FileOutputStream (dosya);
	   	BufferedOutputStream bw = new BufferedOutputStream(fos);
	   	int j=0;
	    while( liste[j]!=null) {
	    	fos.write(Arrays.deepToString((Object[])liste[j]).getBytes());
		   	fos.write("\r\n".getBytes());
		   	fos.flush();
		   	j++;
	   	}
	  	fos.close();		
	}
	
	
	public static void main(String[] args) throws Exception {
		new Rehber();
	}
}
