package ozenctavsanci.telefonrehberi.kisiler;

import java.util.*;


public class Sirket extends Kisi{
	private long kurulusSermayesi; 
	private String sirketTuru;
	private String bagliOlduguVergiDairesi;
	private String sektor;
	
	public Sirket(){
		
	}
	
	public Sirket(String ad, String dogumTarihi, String kimlikNo, String yasadigiYer, int telefonNo, String email,long kurulusSermayesi, String sirketTuru, String bagliOlduguVergiDairesi, String sektor) {
		super(ad, dogumTarihi, kimlikNo, yasadigiYer, telefonNo, email);
		this.kurulusSermayesi = kurulusSermayesi;
		this.sirketTuru = sirketTuru;
		this.bagliOlduguVergiDairesi = bagliOlduguVergiDairesi;
		this.sektor = sektor;
	}


	@Override
	public String toString() {
		return "[1]"+ getAd() + "|" + getDogumTarihi() + "|" + getKimlikNo() + "|"
				+ getYasadigiYer() + "|" + getTelefonNo() + "|" + getEmail() + getKurulusSermayesi() + "|" + getSirketTuru()
				+ "|" + getBagliOlduguVergiDairesi() + "|" + getSektor();
	}
	@Override
	public String getInfo (ArrayList <Kisi> kisiListesi) {
	return "Şirket İsmi: "+ getAd() +  "\nKuruluş Tarihi: " + getDogumTarihi() + "\nSicil Numarası: " + getKimlikNo()
			+"\nHizmet Yeri: " + getYasadigiYer() +  "\nİletişim Numarası: " + getTelefonNo() + "\nİletişim Adresi: " + getEmail() + "\nKuruluş Sermayesi: " +getKurulusSermayesi() + "\nŞirket Türü: " + getSirketTuru()
			+ "\nBağlı Olduğu Vergi Dairesi: " + getBagliOlduguVergiDairesi() + "\nHizmet Verdiği Sektör: " +getSektor() ;
}
	
	public long getKurulusSermayesi() {
		return kurulusSermayesi;
	}
	public void setKurulusSermayesi(long kurulusSermayesi) {
		this.kurulusSermayesi = kurulusSermayesi;
	}
	public String getSirketTuru() {
		return sirketTuru;
	}
	public void setSirketTuru(String sirketTuru) {
		this.sirketTuru = sirketTuru;
	}
	public String getBagliOlduguVergiDairesi() {
		return bagliOlduguVergiDairesi;
	}
	public void setBagliOlduguVergiDairesi(String bagliOlduguVergiDairesi) {
		this.bagliOlduguVergiDairesi = bagliOlduguVergiDairesi;
	}
	public String getSektor() {
		return sektor;
	}
	public void setSektor(String sektor) {
		this.sektor = sektor;
	}
}
