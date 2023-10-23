package com.ozenctavsanci.rehber1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.module.ModuleDescriptor.Builder;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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
		File dosya = new File("telefonRehberi.xml");
		ArrayList <Kisi> kisiListesiTemp = new ArrayList <Kisi>();
		if(!dosya.exists()) {
			//dosya.createNewFile();
			kisiListesiTemp = new ArrayList<Kisi>();
		}	
		else {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  // process XML securely, avoid attacks like XML External Entities (XXE)
	        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);// Güvenlik
	          // parse XML file
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse("telefonRehberi.xml");
	        doc.getDocumentElement().normalize();// Normalize ediyoruz.
	        
	        System.out.println("Root element :"+ doc.getDocumentElement().getNodeName());
	        
	        Element kisiListesiElement = doc.getDocumentElement();
	        
	        NodeList gercekKisiList = doc.getElementsByTagName("GerçekKişi");//Get all kisiler
	        for (int i = 0; i < gercekKisiList.getLength(); i++) {
	            Node gercekKisiNode = gercekKisiList.item(i);	          
	            if (gercekKisiNode.getNodeType() == Node.ELEMENT_NODE) {
	            	GercekKisi kisi = new GercekKisi();
        			ArrayList <String> strTemp = new ArrayList <String>();
	            	Element gercekKisiElement = (Element) gercekKisiNode; 
	            	NodeList gercekKisiDetay = gercekKisiNode.getChildNodes();
	            	for(int j=0; j<gercekKisiDetay.getLength(); j++) {
	            		Node detay = gercekKisiDetay.item(j);
	            		if(detay.getNodeType() == Node.ELEMENT_NODE) {
	            			Element detayElement = (Element) detay;	
//	            			System.out.println(detayElement.getTextContent());	
	            			strTemp.add(detayElement.getTextContent());          				            		
	            		}

	            	}
            		kisi.setAd(strTemp.get(0));
    				kisi.setDogumTarihi(strTemp.get(1));
    				kisi.setKimlikNo(strTemp.get(2));// integer cast ettik
    				kisi.setYasadigiYer(strTemp.get(3));
    				kisi.setTelefonNo(Integer.parseInt(strTemp.get(4)));
    				kisi.setEmail(strTemp.get(5));
    				kisi.setTahsili(strTemp.get(6));
    				kisi.setMeslegi(strTemp.get(7));
    				kisi.setSicilKaydi(strTemp.get(8));	  
	            	kisiListesiTemp.add(kisi);
	           	}	            
	         }
	        
	        NodeList tuzelKisiList = doc.getElementsByTagName("TüzelKişi");
	        for (int i = 0; i < tuzelKisiList.getLength(); i++) {
	            Node tuzelKisiNode = tuzelKisiList.item(i);	          
	            if (tuzelKisiNode.getNodeType() == Node.ELEMENT_NODE) {	
	            	TuzelKisi kisi = new TuzelKisi();
        			ArrayList <String> strTemp = new ArrayList <String>();
	            	Element tuzelKisiElement = (Element) tuzelKisiNode;
	            	NodeList tuzelKisiDetay = tuzelKisiNode.getChildNodes();
	            	for(int j=0; j<tuzelKisiDetay.getLength(); j++) {
	            		Node detay = tuzelKisiDetay.item(j);
	            		if(detay.getNodeType()==Node.ELEMENT_NODE) {
	            			Element detayElement = (Element) detay;	
//	            			System.out.println(detayElement.getTextContent());	
	            			strTemp.add(detayElement.getTextContent()); 
	            		}
	            	}
	            	kisi.setAd(strTemp.get(0));
    				kisi.setDogumTarihi(strTemp.get(1));
    				kisi.setKimlikNo(strTemp.get(2));// integer cast ettik
    				kisi.setYasadigiYer(strTemp.get(3));
    				kisi.setTelefonNo(Integer.parseInt(strTemp.get(4)));
    				kisi.setEmail(strTemp.get(5));
    				kisi.setKurulusSermayesi(Long.parseLong(strTemp.get(6)));
    				kisi.setSirketTuru(strTemp.get(7));
    				kisi.setBagliOlduguVergiDairesi(strTemp.get(8));
    				kisi.setSektor(strTemp.get(9));
	            	kisiListesiTemp.add(kisi);
	           	}	            
	         }	        
			System.out.println(kisiListesiTemp);
		}
		return kisiListesiTemp;
	}	
	
		
	public void kayıtYazdır(ArrayList <Kisi> kisiListesi) throws Exception {
	    File dosya = new File("telefonRehberi.xml");
	    if(!dosya.exists()) {
	    	dosya.createNewFile();	    	
	    }
	    FileOutputStream output = new FileOutputStream(dosya);
	      
	    DocumentBuilderFactory  documentFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	    Document doc = documentBuilder.newDocument();
	    
	    Element rootElement = doc.createElement("KisiListesi"); // root Element	
	    doc.appendChild(rootElement);
	    	    
	    for(int i =0; i< kisiListesi.size(); i++) {
	    	if(kisiListesi.get(i) instanceof GercekKisi ) {
	    		Element gercekKisi = doc.createElement("GerçekKişi");
	    		rootElement.appendChild(gercekKisi);
	    		Attr attr = doc.createAttribute("id");
	    		attr.setValue(Integer.toString(i+1));
	    		gercekKisi.setAttributeNode(attr);
	    		
	    		Element name = doc.createElement("isim");
	    		name.setTextContent(kisiListesi.get(i).getAd());
	    		gercekKisi.appendChild(name);
	    		
	    		Element dogumTarihi = doc.createElement("DoğumTarihi");
	    		dogumTarihi.setTextContent(kisiListesi.get(i).getDogumTarihi());
	    		gercekKisi.appendChild(dogumTarihi);
	    		
	    		Element kimlikNo = doc.createElement("KimlikNumarası");
	    		kimlikNo.setTextContent(kisiListesi.get(i).getKimlikNo());
	    		gercekKisi.appendChild(kimlikNo);
	    		
	    		Element ikametgahYeri = doc.createElement("İkametgahYeri");
	    		ikametgahYeri.setTextContent(kisiListesi.get(i).getYasadigiYer());
	    		gercekKisi.appendChild(ikametgahYeri);
	    		
	    		Element iletisimNo = doc.createElement("İletişimNumarası");
	    		iletisimNo.setTextContent(String.valueOf(kisiListesi.get(i).getTelefonNo()));
	    		gercekKisi.appendChild(iletisimNo);
	    		
	    		Element iletisimAdresi = doc.createElement("İletişimAdresi");
	    		iletisimAdresi.setTextContent(kisiListesi.get(i).getEmail());
	    		gercekKisi.appendChild(iletisimAdresi);
	    		
	    		Element ogrenimDurumu = doc.createElement("ÖğrenimDurumu");
	    		ogrenimDurumu.setTextContent(((GercekKisi) kisiListesi.get(i)).getTahsili());
	    		gercekKisi.appendChild(ogrenimDurumu);
	    		
	    		Element meslegi = doc.createElement("Mesleği");
	    		meslegi.setTextContent(((GercekKisi) kisiListesi.get(i)).getMeslegi());
	    		gercekKisi.appendChild(meslegi);
	    		
	    		Element sabika = doc.createElement("SicilKaydı");
	    		sabika.setTextContent(((GercekKisi) kisiListesi.get(i)).getSicilKaydi());	    		
	    		gercekKisi.appendChild(sabika);
	    	}

	    	if(kisiListesi.get(i) instanceof TuzelKisi ) {
	    		Element tuzelKisi = doc.createElement("TüzelKişi");
	    		rootElement.appendChild(tuzelKisi);
	    		Attr attr = doc.createAttribute("id");
	    		attr.setValue(Integer.toString(i+1));
	    		tuzelKisi.setAttributeNode(attr);
	    		
	    		Element name = doc.createElement("isim");
	    		name.setTextContent(kisiListesi.get(i).getAd());
	    		tuzelKisi.appendChild(name);
	    		
	    		Element dogumTarihi = doc.createElement("KuruluşTarihi");
	    		dogumTarihi.setTextContent(kisiListesi.get(i).getDogumTarihi());
	    		tuzelKisi.appendChild(dogumTarihi);
	    		
	    		Element kimlikNo = doc.createElement("VergiNumarası");
	    		kimlikNo.setTextContent(kisiListesi.get(i).getKimlikNo());
	    		tuzelKisi.appendChild(kimlikNo);
	    		
	    		Element ikametgahYeri = doc.createElement("HizmetYeri");
	    		ikametgahYeri.setTextContent(kisiListesi.get(i).getYasadigiYer());
	    		tuzelKisi.appendChild(ikametgahYeri);
	    		
	    		Element iletisimNo = doc.createElement("İletişimNumarası");
	    		iletisimNo.setTextContent(String.valueOf(kisiListesi.get(i).getTelefonNo()));
	    		tuzelKisi.appendChild(iletisimNo);
	    		
	    		Element iletisimAdresi = doc.createElement("İletişimAdresi");
	    		iletisimAdresi.setTextContent(kisiListesi.get(i).getEmail());
	    		tuzelKisi.appendChild(iletisimAdresi);
	    		
	    		Element kurulusSermayesi = doc.createElement("KuluşSermayesi");
	    		kurulusSermayesi.setTextContent(Long.toString(((TuzelKisi) kisiListesi.get(i)).getKurulusSermayesi()));
	    		tuzelKisi.appendChild(kurulusSermayesi);
	    		
	    		Element sirketTuru = doc.createElement("ŞirketTürü");
	    		sirketTuru.setTextContent(((TuzelKisi) kisiListesi.get(i)).getSirketTuru());
	    		tuzelKisi.appendChild(sirketTuru);
	    		
	    		Element bagliOlduguVergiDairesi = doc.createElement("BağlıolduğuVergiDairesi");
	    		bagliOlduguVergiDairesi.setTextContent(((TuzelKisi) kisiListesi.get(i)).getBagliOlduguVergiDairesi());
	    		tuzelKisi.appendChild(bagliOlduguVergiDairesi);
	    		
	    		Element sektor = doc.createElement("FaaliyetSektörü");
	    		sektor.setTextContent(((TuzelKisi) kisiListesi.get(i)).getSektor());
	    		tuzelKisi.appendChild(sektor);
	    	}

	    }
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

//        // pretty print
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
	
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

