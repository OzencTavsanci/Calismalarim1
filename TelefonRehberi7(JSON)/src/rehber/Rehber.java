package rehber;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.module.ModuleDescriptor.Builder;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



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
		File dosya = new File("telefonRehberi.json");
		ArrayList <Kisi> kisiListesiTemp = new ArrayList <Kisi>();
		if(!dosya.exists()) {
			//dosya.createNewFile();
			kisiListesiTemp = new ArrayList<Kisi>();
		}	
		else {
			FileReader reader = new FileReader(dosya);
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(reader);
			JSONArray kisilistesiJsonArray = (JSONArray) obj;
			for(int i = 0; i< kisilistesiJsonArray.size(); i++) {
				JSONObject kisiListesiObject= (JSONObject) kisilistesiJsonArray.get(i);	
				if(kisiListesiObject.containsKey("gercekKisi") ) {
					JSONObject detayObject =(JSONObject) kisiListesiObject.get("gercekKisi");
					GercekKisi kisi = new GercekKisi();
	            	kisi.setAd((String) detayObject.get("İsim"));
	    			kisi.setDogumTarihi((String) detayObject.get("DoğumTarihi"));
	    			kisi.setKimlikNo((String) detayObject.get("KimlikNumarası"));// integer cast ettik
	    			kisi.setYasadigiYer((String) detayObject.get("İkametgahYeri"));
	    			kisi.setTelefonNo(Integer.parseInt( (String) detayObject.get("İletişimNumarası")));
	    			kisi.setEmail((String) detayObject.get("İletişimAdresi"));
	    			kisi.setTahsili((String) detayObject.get("ÖğrenimDurumu"));
	    			kisi.setMeslegi((String) detayObject.get("Mesleği"));
	    			kisi.setSicilKaydi((String) detayObject.get("SicilKaydı"));	    				
		            kisiListesiTemp.add(kisi);
				    }									
				if(kisiListesiObject.containsKey("tuzelKisi") ) {
					JSONObject detayObject =(JSONObject) kisiListesiObject.get("tuzelKisi");
					TuzelKisi kisi = new TuzelKisi();					
	            	kisi.setAd((String) detayObject.get("İsim"));
	    			kisi.setDogumTarihi((String) detayObject.get("DoğumTarihi"));
	    			kisi.setKimlikNo((String) detayObject.get("KimlikNumarası"));// integer cast ettik
	    			kisi.setYasadigiYer((String) detayObject.get("İkametgahYeri"));
	    			kisi.setTelefonNo(Integer.parseInt((String) detayObject.get("İletişimNumarası")));
	    			kisi.setEmail((String) detayObject.get("İletişimAdresi"));
    				kisi.setKurulusSermayesi(Long.parseLong((String) detayObject.get("KuluşSermayesi")));
    				kisi.setSirketTuru((String) detayObject.get("ŞirketTürü"));
    				kisi.setBagliOlduguVergiDairesi((String) detayObject.get("BağlıolduğuVergiDairesi"));
    				kisi.setSektor((String) detayObject.get("FaaliyetSektörü"));
	            	kisiListesiTemp.add(kisi);
				   }
				}

			}			 
		return kisiListesiTemp;
	}	
	
		
	public void kayıtYazdır(ArrayList <Kisi> kisiListesi) throws Exception {
	    File dosya = new File("telefonRehberi.json");
	    if(!dosya.exists()) {
	    	dosya.createNewFile();	    	
	    }
	    FileWriter fileWriter = new FileWriter(dosya);
	    JSONArray kisiListesiJson = new JSONArray();
	    for(int i =0; i< kisiListesi.size(); i++) {
	    	if(kisiListesi.get(i) instanceof GercekKisi ) {
	    		JSONObject gercekKisiDetay = new JSONObject();
	    		JSONObject gercekKisiObj = new JSONObject();
	    		
	    		gercekKisiDetay.put("İsim", kisiListesi.get(i).getAd());
	    		gercekKisiDetay.put("DoğumTarihi",kisiListesi.get(i).getDogumTarihi());
	    		gercekKisiDetay.put("KimlikNumarası",kisiListesi.get(i).getKimlikNo());
	    		gercekKisiDetay.put("İkametgahYeri",kisiListesi.get(i).getYasadigiYer());
	    		gercekKisiDetay.put("İletişimNumarası",String.valueOf(kisiListesi.get(i).getTelefonNo()));
	    		gercekKisiDetay.put("İletişimAdresi",kisiListesi.get(i).getEmail());
	    		gercekKisiDetay.put("ÖğrenimDurumu",((GercekKisi) kisiListesi.get(i)).getTahsili());
	    		gercekKisiDetay.put("Mesleği",((GercekKisi) kisiListesi.get(i)).getMeslegi());
	    		gercekKisiDetay.put("SicilKaydı",((GercekKisi) kisiListesi.get(i)).getSicilKaydi());
	    		
	    		gercekKisiObj.put("gercekKisi", gercekKisiDetay);
		    	kisiListesiJson.add(gercekKisiObj);
	    	}
	    	if(kisiListesi.get(i) instanceof TuzelKisi ) {
	    		JSONObject tuzelKisiDetay = new JSONObject();
	    		JSONObject tuzelKisiObj = new JSONObject();
	    		tuzelKisiDetay.put("İsim", kisiListesi.get(i).getAd());
	    		tuzelKisiDetay.put("DoğumTarihi",kisiListesi.get(i).getDogumTarihi());
	    		tuzelKisiDetay.put("KimlikNumarası",kisiListesi.get(i).getKimlikNo());
	    		tuzelKisiDetay.put("İkametgahYeri",kisiListesi.get(i).getYasadigiYer());
	    		tuzelKisiDetay.put("İletişimNumarası",String.valueOf(kisiListesi.get(i).getTelefonNo()));
	    		tuzelKisiDetay.put("İletişimAdresi",kisiListesi.get(i).getEmail());
	    		tuzelKisiDetay.put("KuluşSermayesi",Long.toString(((TuzelKisi) kisiListesi.get(i)).getKurulusSermayesi()));
	    		tuzelKisiDetay.put("ŞirketTürü",((TuzelKisi) kisiListesi.get(i)).getSirketTuru());
	    		tuzelKisiDetay.put("BağlıolduğuVergiDairesi",((TuzelKisi) kisiListesi.get(i)).getBagliOlduguVergiDairesi());
	    		tuzelKisiDetay.put("FaaliyetSektörü",((TuzelKisi) kisiListesi.get(i)).getSektor());
	    		
	    	    tuzelKisiObj.put("tuzelKisi", tuzelKisiDetay);
	    	    kisiListesiJson.add(tuzelKisiObj);
	    		
	    	}
	    }
	    fileWriter.write(kisiListesiJson.toJSONString());
	    fileWriter.flush();    
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
	    yeniKisi.setDogumTarihi(scan1.nextLine());
	    System.out.println("\nKimlik Numarası: ");
	    yeniKisi.setKimlikNo(scan1.nextLine());
	    System.out.println("\nİkametgah Yeri: ");
	    yeniKisi.setYasadigiYer(scan1.nextLine());
	    System.out.println("\nİletişim Numarası: ");
	    yeniKisi.setTelefonNo(Integer.parseInt(scan1.nextLine()));	 
	    System.out.println("\nİletişim Adresi: ");
	    yeniKisi.setEmail(scan1.nextLine());
	    System.out.println("\r\nTahsil Durumu:  ");
	    yeniKisi.setTahsili(scan1.nextLine());
	    System.out.println("\r\nMesleği: ");
	    yeniKisi.setMeslegi(scan1.nextLine());
	    System.out.println("\r\nSicil Kaydi: ");
	    yeniKisi.setSicilKaydi(scan1.nextLine());
	    
	    kisiListesi.add(yeniKisi);
	    return kisiListesi;
	}
	
	private ArrayList <Kisi> tuzelKisiKayitEkle(ArrayList <Kisi> kisiListesi){
		Scanner scan1= new Scanner(System.in);
	    TuzelKisi yeniKisi = new TuzelKisi();
	    System.out.println("Lütfen kaydını yapmak istediğiniz şirketin bilgilerini giriniz.");
	    System.out.println("\nŞirket İsmi: ");
	    yeniKisi.setAd(scan1.nextLine());
	    System.out.println("\nŞirketin Kuruluş Tarihi: ");
	    yeniKisi.setDogumTarihi(scan1.nextLine());
	    System.out.println("\nTicaret Sicil Numarası: ");
	    yeniKisi.setKimlikNo(scan1.nextLine());
	    System.out.println("\nMerkez Yeri: ");
	    yeniKisi.setYasadigiYer(scan1.nextLine());
	    System.out.println("\nİletişim Numarası: ");
	    yeniKisi.setTelefonNo(Integer.parseInt(scan1.nextLine()));	    
	    System.out.println("\nİletişim Adresi: ");
	    yeniKisi.setEmail(scan1.nextLine());
	    System.out.println("\nKuruluş Sermayesi: ");
	    yeniKisi.setKurulusSermayesi(Long.parseLong(scan1.nextLine()));
	    System.out.println("\nSirketTuru: ");
	    yeniKisi.setSirketTuru(scan1.nextLine());
	    System.out.println("\nBagliOlduguVergiDairesi: ");
	    yeniKisi.setBagliOlduguVergiDairesi(scan1.nextLine());
	    System.out.println("\nFaaliyet Gösterdiği Sektör: ");
	    yeniKisi.setSektor(scan1.nextLine());

	    kisiListesi.add(yeniKisi);		
		return kisiListesi;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		new Rehber();
	}

}

