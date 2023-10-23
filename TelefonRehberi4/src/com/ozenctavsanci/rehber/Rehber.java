package com.ozenctavsanci.rehber;

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

	public static ArrayList<Kisi>  kayıtOku() throws Exception {
		File dosya = new File("telefonRehberi3");
		ArrayList <Kisi> kisiListesiTemp = new ArrayList <Kisi>();
		if(!dosya.exists()) {
			//dosya.createNewFile();
			kisiListesiTemp = new ArrayList<Kisi>();
		}	
		else {
			FileInputStream fileIn = new FileInputStream(dosya);
			if (fileIn.available() == 0) kisiListesiTemp = new ArrayList<Kisi>(); 
			else {
			ObjectInputStream ois = new ObjectInputStream(fileIn);
			kisiListesiTemp = (ArrayList<Kisi>) ois.readObject();
			ois.close();
			fileIn.close();
			}
			System.out.println(kisiListesiTemp);
		}
		return kisiListesiTemp;
	}	
	
		
	public void kayıtYazdır(ArrayList <Kisi> kisiListesi) throws Exception {
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
	
	
	
	
/*	public void kayıtSil(ArrayList<Kisi> kisiListesi) {
		Scanner scanner = new Scanner(System.in);
		   Kisi kisiArama = new Kisi();
	 	   System.out.println("Lütfen Kayıt Bilgilerini Silmek İstediğiniz Kişinin Ad ve Soyad Bilgilerini Sırasıyla Yazınız.");
	 	   System.out.println("Adı:");
	 	   kisiArama.setName(scanner.next());	   
	 	   System.out.println("Soyadı:");
	 	   kisiArama.setSurname(scanner.next());
	 	   for(int i =0; i<kisiListesi.size(); i++) {
	 		  if(kisiListesi.get(i).equals(kisiArama)) {
	 			  kisiListesi.remove(i);
	 			  ((ArrayList<Kisi>) kisiListesi).trimToSize(); 
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
			  GercekKisi gercekKisiKayitSil = new GercekKisi();
		 	  System.out.println("Lütfen Kayıt Bilgilerini Silmek İstediğiniz Kişinin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
		 	  System.out.println("Adı:");
		 	  gercekKisiKayitSil.setAd(scanner.next());	   
		 	  System.out.println("İletişim Numarası:");
		 	  gercekKisiKayitSil.setTelefonNo(scanner.nextInt());
		 	  for(int i =0; i<kisiListesi.size(); i++) {
		 		  if(kisiListesi.get(i).equals(gercekKisiKayitSil)) {
		 			  kisiListesi.remove(i);
		 			  ((ArrayList<Kisi>) kisiListesi).trimToSize(); 
		 			  System.out.println("Kayıtlı bilgiler başarıyla silinmiştir.");
		 			  break;
		 	 	   }	 		  
		      }
		   }  
		   if(secim==2) {
			   TuzelKisi tuzelKisiKayitSil = new TuzelKisi();
		 	   System.out.println("Lütfen Kayıt Bilgilerini Silmek İstediğiniz Şirketin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
		 	   System.out.println("Adı:");
		 	   tuzelKisiKayitSil.setAd(scanner.next());	   
		 	   System.out.println("İletişim Numarası:");
		 	   tuzelKisiKayitSil.setTelefonNo(scanner.nextInt());
		 	   for(int i =0; i<kisiListesi.size(); i++) {
		 		  if(kisiListesi.get(i).equals(tuzelKisiKayitSil)) {
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
		   GercekKisi gercekKisiArama= new GercekKisi();
	 	   System.out.println("Lütfen Kayıt Bilgilerine Ulaşmak İstediğiniz Kişinin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
	 	   System.out.println("Adı:");
	 	   gercekKisiArama.setAd(scanner.next());	   
	 	   System.out.println("İletişim Numarası:");
	 	   gercekKisiArama.setTelefonNo(scanner.nextInt());
	 	   for(int i =0; i<kisiListesi.size(); i++) {
	  		  if(kisiListesi.get(i).equals(gercekKisiArama)) {  
	  	 		  System.out.println(kisiListesi.get(i).getInfo(kisiListesi));
	  	          break;
	  	 	   }
	  	   }
	   }
	   if(secim==2) {
		   TuzelKisi tuzelKisiArama = new TuzelKisi();
	 	   System.out.println("Lütfen Kayıt Bilgilerine Ulaşmak İstediğiniz Şirketin Ad ve İletişim Numarası Bilgilerini Sırasıyla Yazınız.");
	 	   System.out.println("Adı:");
	 	   tuzelKisiArama.setAd(scanner.next());	   
	 	   System.out.println("İletişim Numarası:");
	 	   tuzelKisiArama.setTelefonNo(scanner.nextInt());	
	 	   for(int i =0; i<kisiListesi.size(); i++) {
	  		  if(kisiListesi.get(i).equals(tuzelKisiArama)) {
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
			System.out.println("\n[1]Gerçek Kişi Kayıt\n[2]Tüzel Kişi Kayıt\n[0]Menü");
			secim=scan1.nextInt();
			switch (secim) {
			    case 1: gercekKisiKayitEkle(kisiListesi);
			       break;
			    case 2: tuzelKisiKayitEkle(kisiListesi);
			       break;
			}
		}
		return  kisiListesi;
	 }
	
	
	private ArrayList <Kisi> gercekKisiKayitEkle(ArrayList <Kisi> kisiListesi){
		Scanner scan1= new Scanner(System.in);
	    GercekKisi yeniKisi= new GercekKisi(null, null, null, null, 0, null, null, null, null);
	    System.out.println("Lütfen kaydını yapmak istediğiniz kişinin ad, soyad, telefon numarası ve E-mail adresi bilgilerini giriniz.");
	    System.out.println("\nAdı ve Soyadı: ");
	    yeniKisi.setAd(scan1.nextLine());
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
	
	private ArrayList <Kisi> tuzelKisiKayitEkle(ArrayList <Kisi> kisiListesi){
		Scanner scan1= new Scanner(System.in);
	    TuzelKisi yeniKisi = new TuzelKisi();
	    System.out.println("Lütfen kaydını yapmak istediğiniz şirketin bilgilerini giriniz.");
	    System.out.println("\nŞirket İsmi: ");
	    yeniKisi.setAd(scan1.next());
	    System.out.println("\nŞirketin Kuruluş Tarihi: ");
	    yeniKisi.setDogumTarihi(scan1.next());
	    System.out.println("\nTicaret Sicil Numarası: ");
	    yeniKisi.setKimlikNo(scan1.next());
	    System.out.println("\nMerkez Yeri: ");
	    yeniKisi.setYasadigiYer(scan1.next());
	    System.out.println("\nİletişim Numarası: ");
	    yeniKisi.setTelefonNo(scan1.nextInt());	    
	    System.out.println("\nİletişim Adresi: ");
	    yeniKisi.setEmail(scan1.next());
	    System.out.println("\nKuruluş Sermayesi: ");
	    yeniKisi.setKurulusSermayesi(scan1.nextLong());
	    System.out.println("\nSirketTuru: ");
	    yeniKisi.setSirketTuru(scan1.next());
	    System.out.println("\nBagliOlduguVergiDairesi: ");
	    yeniKisi.setBagliOlduguVergiDairesi(scan1.next());
	    System.out.println("\nFaaliyet Gösterdiği Sektör: ");
	    yeniKisi.setSektor(scan1.next());

	    kisiListesi.add(yeniKisi);		
		return kisiListesi;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		new Rehber();
	}

}

