package ozenctavsanci.telefonrehberi.kisiler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rehber  {

	public Rehber() throws Exception {
		ArrayList<Kisi> kisiListesi= new ArrayList<Kisi>(); 
		kisiListesi=kayıtOku();
	    int kullaniciSecimi =-1;
	    while(kullaniciSecimi!=0) {	    	
		    kullaniciSecimi=Menu();
		    int boy=0;
		    
		    switch (kullaniciSecimi) {
		        case 1: 
		        	kayitEkle(kisiListesi);
//		        	kayıtYazdır(kisiListesi);
		            break;		            
		        case 2:
		        	kayıtArama(kisiListesi);
		        	break;		        	
		        case 3:
		        	kayıtSil(kisiListesi);
		        	break;
		    }		
	    }
	    kayıtYazdır(kisiListesi);
	}
	
	public int Menu() {

		System.out.println("\nKayıt Menüsüne Hoşgeldiniz.");
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n[0] Çıkış" + "\n[1] Kayıt Ekle"+ "\n[2] Kayıt Arama"+ "\n[3] Kayıt Sil");
		int secim=scanner.nextInt();
		return secim;
	}
	
	public static ArrayList<Kisi>  kayıtOku () throws Exception {
		File dosya = new File("telefonRehberi3");
		ArrayList <Kisi> kisiListesiTemp = new ArrayList <Kisi>();
		if(!dosya.exists()) {
			dosya.createNewFile();
		}
		Scanner scnr = new Scanner(dosya);
		while(scnr.hasNextLine()) {	
			String temp= scnr.nextLine();	
			if(temp.contains("[0]")) {
				temp=temp.replace("[0]", " ");
	            temp=temp.trim();
				String[] strng=temp.split("\\|");				
				Sahis sahis = new Sahis();			
				sahis.setAd(strng[0]);
				sahis.setDogumTarihi(strng[1]);
				sahis.setKimlikNo(strng[2]);// integer cast ettik
				sahis.setYasadigiYer(strng[3]);
				sahis.setTelefonNo(Integer.parseInt(strng[4]));
				sahis.setEmail(strng[5]);
				sahis.setTahsili(strng[6]);
				sahis.setMeslegi(strng[7]);
				sahis.setSicilKaydi(strng[8]);				
				kisiListesiTemp.add(sahis);
			}
			if(temp.contains("[1]")) {
				temp=temp.replace("[1]", " ");
	            temp=temp.trim();	    	    
				String[] strng=temp.split("\\|");				
				Sirket sirket = new Sirket();
				sirket.setAd(strng[0]);
				sirket.setDogumTarihi(strng[1]);
				sirket.setKimlikNo(strng[2]);// integer cast ettik
				sirket.setYasadigiYer(strng[3]);
				sirket.setTelefonNo(Integer.parseInt(strng[4]));
				sirket.setEmail(strng[5]);
				sirket.setKurulusSermayesi(Long.parseLong(strng[6]));
				sirket.setSirketTuru(strng[7]);
				sirket.setBagliOlduguVergiDairesi(strng[8]);
				sirket.setSektor((strng[9]));				
				kisiListesiTemp.add(sirket);
			}
		}
		scnr.close();
		System.out.println(kisiListesiTemp);
        return kisiListesiTemp;
	}

/*	public static ArrayList<Kisiler>  kayıtOku () throws Exception {
		File dosya = new File("telefonRehberi3");
		ArrayList <Kisiler> kisiListesiTemp = new ArrayList <Kisiler>();
		if(!dosya.exists()) {
			dosya.createNewFile();
		}	
		FileInputStream fileIn = new FileInputStream(dosya);
		ObjectInputStream ois = new ObjectInputStream(fileIn);
		kisiListesiTemp = (ArrayList<Kisiler>) ois.readObject();
		ois.close();
		fileIn.close();
		System.out.println(kisiListesiTemp);
		return kisiListesiTemp;
	}	
	*/
		
/*	public void kayıtYazdır(ArrayList <Kisiler> kisiListesi) throws Exception {
	    File dosya = new File("telefonrehberi3");
	    if(!dosya.exists()) {
	    	dosya.createNewFile();
	    	
	    }
		FileOutputStream fos = new FileOutputStream (dosya);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(kisiListesi);
	    oos.close();
	  	fos.close();
    }
	*/
	
	public void kayıtYazdır(ArrayList <Kisi> kisiListesi) throws Exception {
	    File dosya = new File("telefonrehberi3");
	    if(!dosya.exists()) {
	    	dosya.createNewFile();
	    }
		FileOutputStream fos = new FileOutputStream (dosya);
	   	BufferedOutputStream bw = new BufferedOutputStream(fos);
	    for (int j=0; j<kisiListesi.size(); j++ ) {
	    	if(kisiListesi.get(j) instanceof Sahis) {
		    	fos.write(((Sahis)kisiListesi.get(j)).toString().getBytes());
			   	fos.write("\r\n".getBytes());
			   	fos.flush();
	    	}
	    	if(kisiListesi.get(j) instanceof Sirket) {
	    		fos.write(((Sirket)kisiListesi.get(j)).toString().getBytes());
	    	   	fos.write("\r\n".getBytes());
			   	fos.flush();     		
	    	}
 	
	   	}
	  	fos.close();
    }
	
	
/*	public void kayıtSil(ArrayList<Kisiler> kisiListesi) {
		Scanner scanner = new Scanner(System.in);
		   Kisiler kisiArama = new Kisiler();
	 	   System.out.println("Lütfen Kayıt Bilgilerini Silmek İstediğiniz Kişinin Ad ve Soyad Bilgilerini Sırasıyla Yazınız.");
	 	   System.out.println("Adı:");
	 	   kisiArama.setName(scanner.next());	   
	 	   System.out.println("Soyadı:");
	 	   kisiArama.setSurname(scanner.next());
	 	   for(int i =0; i<kisiListesi.size(); i++) {
	 		  if(kisiListesi.get(i).equals(kisiArama)) {
	 			  kisiListesi.remove(i);
	 			  ((ArrayList<Kisiler>) kisiListesi).trimToSize(); 
	 			  System.out.println("Kayıtlı bilgiler başarıyla silinmiştir.");
	 			  break;
	 	 	   }	 		  
	 	   }
//	 	  System.out.println("Silinmesi istenilen kayıt bulunamamıştır.");
	}
	*/
	public void kayıtSil(ArrayList<Kisi> kisiListesi) {
		   Scanner scanner = new Scanner(System.in);
		   int secim=scanner.nextInt();
		   System.out.println("Lütfen Kayıt Bilgilerini Silmek istediğiniz kayıt türünü seçiniz.\n[1] Şahıs Kaydı için 1'i \n[2] Çalışan Kaydı silmek için 2'yi tuşlayınız.");
		   if(secim==1) {
			  Sahis sahisKayitSil = new Sahis();
		 	  System.out.println("Lütfen Kayıt Bilgilerini Silmek İstediğiniz Kişinin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
		 	  System.out.println("Adı:");
		 	  sahisKayitSil.setAd(scanner.next());	   
		 	  System.out.println("İletişim Numarası:");
		 	  sahisKayitSil.setTelefonNo(scanner.nextInt());
		 	  for(int i =0; i<kisiListesi.size(); i++) {
		 		  if(kisiListesi.get(i).equals(sahisKayitSil)) {
		 			  kisiListesi.remove(i);
		 			  ((ArrayList<Kisi>) kisiListesi).trimToSize(); 
		 			  System.out.println("Kayıtlı bilgiler başarıyla silinmiştir.");
		 			  break;
		 	 	   }	 		  
		      }
		   }  
		   if(secim==2) {
			   Sirket sirketKayitSil = new Sirket();
		 	   System.out.println("Lütfen Kayıt Bilgilerini Silmek İstediğiniz Şirketin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
		 	   System.out.println("Adı:");
		 	   sirketKayitSil.setAd(scanner.next());	   
		 	   System.out.println("İletişim Numarası:");
		 	   sirketKayitSil.setTelefonNo(scanner.nextInt());
		 	   for(int i =0; i<kisiListesi.size(); i++) {
		 		  if(kisiListesi.get(i).equals(sirketKayitSil)) {
		 			  kisiListesi.remove(i);
		 			  ((ArrayList<Kisi>) kisiListesi).trimToSize(); 
		 			  System.out.println("Kayıtlı bilgiler başarıyla silinmiştir.");
		 			  break;
		 	 	   }	 		  
		      }
		   }
//	 	  System.out.println("Silinmesi istenilen kayıt bulunamamıştır.");
	}

	public void kayıtArama(ArrayList <Kisi> kisiListesi) {
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Arama yapmak istediğiniz kayıt türünü seçiniz.\n[1] Şahıs Arama için 1'i \n[2] Şirket Arama için 2'yi tuşlayınız. ");
	   int secim=scanner.nextInt();
	   if(secim==1) {
		   Sahis sahisArama= new Sahis();
	 	   System.out.println("Lütfen Kayıt Bilgilerine Ulaşmak İstediğiniz Kişinin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
	 	   System.out.println("Adı:");
	 	   sahisArama.setAd(scanner.next());	   
	 	   System.out.println("İletişim Numarası:");
	 	   sahisArama.setTelefonNo(scanner.nextInt());
	 	   for(int i =0; i<kisiListesi.size(); i++) {
	  		  if(kisiListesi.get(i).equals(sahisArama)) {  
	  	 		  System.out.println(kisiListesi.get(i).getInfo(kisiListesi));
	  	          break;
	  	 	   }
	  	   }
	   }
	   if(secim==2) {
		   Sirket sirketArama = new Sirket();
	 	   System.out.println("Lütfen Kayıt Bilgilerine Ulaşmak İstediğiniz Şirketin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
	 	   System.out.println("Adı:");
	 	   sirketArama.setAd(scanner.next());	   
	 	   System.out.println("İletişim Numarası:");
	 	   sirketArama.setTelefonNo(scanner.nextInt());	
	 	   for(int i =0; i<kisiListesi.size(); i++) {
	  		  if(kisiListesi.get(i).equals(sirketArama)) {
	  	 		   System.out.println(kisiListesi.get(i).getInfo(kisiListesi));
	  	           break;
	  	 	   }
	  	   }
	   }
	   
// 	   if (kisiListesi.indexOf(kisiArama)!=-1) {
// 		   System.out.println(kisiListesi.);
// 	   } 	   
// 	   System.out.println("Aradığınız kişinin bilgilerine ulaşılamamıştır.");   
	}


		
	public ArrayList <Kisi> kayitEkle(ArrayList <Kisi> kisiListesi) {
		Scanner scan1= new Scanner(System.in);
		int secim =-1;
		while(secim!=0) {
			System.out.println("\n[1]Şahıs Kayıt\n[2]Şirket Kayıt\n[0]Menü");
			secim=scan1.nextInt();
			switch (secim) {
			    case 1: sahisKayitEkle(kisiListesi);
			       break;
			    case 2: sirketKayitEkle(kisiListesi);
			       break;
			}
		}
		return  kisiListesi;
	 }
	
	
	private ArrayList <Kisi> sahisKayitEkle(ArrayList <Kisi> kisiListesi){
		Scanner scan1= new Scanner(System.in);
	    Sahis yeniKisi= new Sahis(null, null, null, null, 0, null, null, null, null);
	    System.out.println("Lütfen kaydını yapmak istediğiniz kişinin ad, soyad, telefon numarası ve E-mail adresi bilgilerini giriniz.");
	    System.out.println("\nAdı ve Soyadı: ");
	    yeniKisi.setAd(scan1.next());
	    System.out.println("\nKişinin Doğum Tarihi: ");
	    yeniKisi.setDogumTarihi(scan1.next());
	    System.out.println("\nKimlik Numarası: ");
	    yeniKisi.setKimlikNo(scan1.next());
	    System.out.println("\nikametgah Yeri: ");
	    yeniKisi.setYasadigiYer(scan1.next());
	    System.out.println("\nİletişim Numarası: ");
	    yeniKisi.setTelefonNo(scan1.nextInt());	    
	    System.out.println("\nİletişim Adresi: ");
	    yeniKisi.setEmail(scan1.next());
	    System.out.println("\nTahsil Durumu: ");
	    yeniKisi.setTahsili(scan1.next());
	    System.out.println("\nMesleği: ");
	    yeniKisi.setMeslegi(scan1.next());
	    System.out.println("\nSicil Kaydi: ");
	    yeniKisi.setSicilKaydi(scan1.next());
	    
	    kisiListesi.add(yeniKisi);
	    return kisiListesi;
	}
	
	private ArrayList <Kisi> sirketKayitEkle(ArrayList <Kisi> kisiListesi){
		Scanner scan1= new Scanner(System.in);
	    Sirket yeniSirket = new Sirket();
	    System.out.println("Lütfen kaydını yapmak istediğiniz şirketin bilgilerini giriniz.");
	    System.out.println("\nŞirket İsmi: ");
	    yeniSirket.setAd(scan1.next());
	    System.out.println("\nŞirketin Kuruluş Tarihi: ");
	    yeniSirket.setDogumTarihi(scan1.next());
	    System.out.println("\nTicaret Sicil Numarası: ");
	    yeniSirket.setKimlikNo(scan1.next());
	    System.out.println("\nMerkez Yeri: ");
	    yeniSirket.setYasadigiYer(scan1.next());
	    System.out.println("\nİletişim Numarası: ");
	    yeniSirket.setTelefonNo(scan1.nextInt());	    
	    System.out.println("\nİletişim Adresi: ");
	    yeniSirket.setEmail(scan1.next());
	    System.out.println("\nKuruluş Sermayesi: ");
	    yeniSirket.setKurulusSermayesi(scan1.nextLong());
	    System.out.println("\nSirketTuru: ");
	    yeniSirket.setSirketTuru(scan1.next());
	    System.out.println("\nBagliOlduguVergiDairesi: ");
	    yeniSirket.setBagliOlduguVergiDairesi(scan1.next());
	    System.out.println("\nFaaliyet Gösterdiği Sektör: ");
	    yeniSirket.setSektor(scan1.next());

	    kisiListesi.add(yeniSirket);		
		return kisiListesi;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		new Rehber();
	}

}
