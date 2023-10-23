package com.ozenctavsanci.rehber1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



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
	    XSLYazdır();
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
            SAXReader reader = new SAXReader();
            Document document = reader.read(dosya);           
            Element root = document.getRootElement();
            List<Node> nodesGercek = document.selectNodes("/kisilistesi/gercekkisi" );
            List<Node> nodesTuzel = document.selectNodes("/kisilistesi/tuzelkisi" );
            for(Node node: nodesGercek) {
            	GercekKisi kisi = new GercekKisi();
            	kisi.setAd(node.selectSingleNode("isim").getText());
				kisi.setDogumTarihi(node.selectSingleNode("dogumtarihi").getText());
				kisi.setKimlikNo(node.selectSingleNode("kimliknumarasi").getText());// integer cast ettik
				kisi.setYasadigiYer(node.selectSingleNode("ikametgahyeri").getText());
				kisi.setTelefonNo(Integer.parseInt(node.selectSingleNode("iletisimnumarasi").getText()));
				kisi.setEmail(node.selectSingleNode("iletisimadresi").getText());
				kisi.setTahsili(node.selectSingleNode("ogrenimdurumu").getText());
				kisi.setMeslegi(node.selectSingleNode("meslegi").getText());
				kisi.setSicilKaydi(node.selectSingleNode("sicilkaydi").getText());	
            	kisiListesiTemp.add(kisi);
            }
            for(Node node: nodesTuzel) {
            	TuzelKisi kisi = new TuzelKisi();
            	kisi.setAd(node.selectSingleNode("isim").getText());
				kisi.setDogumTarihi(node.selectSingleNode("kurulustarihi").getText());
				kisi.setKimlikNo(node.selectSingleNode("verginumarasi").getText());// integer cast ettik
				kisi.setYasadigiYer(node.selectSingleNode("hizmetyeri").getText());
				kisi.setTelefonNo(Integer.parseInt(node.selectSingleNode("iletisimnumarasi").getText()));
				kisi.setEmail(node.selectSingleNode("iletisimadresi").getText());
				kisi.setKurulusSermayesi(Long.parseLong(node.selectSingleNode("kurulussermayesi").getText()));
				kisi.setSirketTuru(node.selectSingleNode("sirketturu").getText());
				kisi.setBagliOlduguVergiDairesi(node.selectSingleNode("bagliolduguvergidairesi").getText());
				kisi.setSektor(node.selectSingleNode("faaliyetsektoru").getText());
            	kisiListesiTemp.add(kisi);
            }	
		}
		return kisiListesiTemp;
	}			
	public void kayıtYazdır(ArrayList <Kisi> kisiListesi) throws Exception {
	    File dosya = new File("telefonRehberi.xml");
	    if(!dosya.exists()) {
	    	dosya.createNewFile();	    	
	    }
	    Document doc = DocumentHelper.createDocument();

	    Element root = doc.addElement("kisilistesi"); // root Element		    	    
	    for(int i =0; i< kisiListesi.size(); i++) {
	    	if(kisiListesi.get(i) instanceof GercekKisi ) {
	    		Element gercekKisi = root.addElement("gercekkisi");
	    		gercekKisi.addElement("isim").setText(kisiListesi.get(i).getAd());
	    		gercekKisi.addElement("dogumtarihi").setText(kisiListesi.get(i).getDogumTarihi());
	    		gercekKisi.addElement("kimliknumarasi").setText(kisiListesi.get(i).getKimlikNo());
	    		gercekKisi.addElement("ikametgahyeri").setText(kisiListesi.get(i).getYasadigiYer());
	    		gercekKisi.addElement("iletisimnumarasi").setText(String.valueOf(kisiListesi.get(i).getTelefonNo()));
	    		gercekKisi.addElement("iletisimadresi").setText(kisiListesi.get(i).getEmail());
	    		gercekKisi.addElement("ogrenimdurumu").setText(((GercekKisi) kisiListesi.get(i)).getTahsili());
	    		gercekKisi.addElement("meslegi").setText(((GercekKisi) kisiListesi.get(i)).getMeslegi());
	    		gercekKisi.addElement("sicilkaydi").setText(((GercekKisi) kisiListesi.get(i)).getSicilKaydi());	    		
	    	}
	    	if(kisiListesi.get(i) instanceof TuzelKisi ) {
	    		Element tuzelKisi = root.addElement("tuzelkisi");
	    		tuzelKisi.addElement("isim").setText(kisiListesi.get(i).getAd());
	    		tuzelKisi.addElement("kurulustarihi").setText(kisiListesi.get(i).getDogumTarihi());
	    		tuzelKisi.addElement("verginumarasi").setText(kisiListesi.get(i).getKimlikNo());
	    		tuzelKisi.addElement("hizmetyeri").setText(kisiListesi.get(i).getYasadigiYer());
	    		tuzelKisi.addElement("iletisimnumarasi").setText(String.valueOf(kisiListesi.get(i).getTelefonNo()));
	    		tuzelKisi.addElement("iletisimadresi").setText(kisiListesi.get(i).getEmail());
	    		tuzelKisi.addElement("kurulussermayesi").setText(Long.toString(((TuzelKisi) kisiListesi.get(i)).getKurulusSermayesi()));
	    		tuzelKisi.addElement("sirketturu").setText(((TuzelKisi) kisiListesi.get(i)).getSirketTuru());
	    		tuzelKisi.addElement("bagliolduguvergidairesi").setText(((TuzelKisi) kisiListesi.get(i)).getBagliOlduguVergiDairesi());
	    		tuzelKisi.addElement("faaliyetsektoru").setText(((TuzelKisi) kisiListesi.get(i)).getSektor());
	    	}
	    }
	    OutputFormat format = OutputFormat.createPrettyPrint();
	    XMLWriter writer = new XMLWriter(new FileWriter(dosya),format);
	    writer.write(doc);
	    writer.close();	
    }
	
	public void XSLYazdır() throws TransformerException, IOException  {
		File xslFile = new File("TelefonRehberi.xsl");
	    if(!xslFile.exists()) {
	    	xslFile.createNewFile();	    	
	    }        
	    StreamSource xslStream = new StreamSource(xslFile);
	    TransformerFactory tFactory=TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer(xslStream);
	    StreamSource in = new StreamSource("telefonRehberi.xml");
	    StreamResult out = new StreamResult("TelefonRehberi.html");

	    transformer.transform(in, out);

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

