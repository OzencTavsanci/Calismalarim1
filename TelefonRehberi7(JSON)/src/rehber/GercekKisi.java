package rehber;

import java.util.ArrayList;


public class GercekKisi extends Kisi {
	private String tahsili;
	private String meslegi;
	private String sicilKaydi;
	


	public GercekKisi(String ad, String dogumTarihi, String kimlikNo, String yasadigiYer, int telefonNo, String email,String tahsili, String meslegi, String sicilKaydi) {
		super(ad, dogumTarihi, kimlikNo, yasadigiYer, telefonNo, email);
		this.tahsili = tahsili;
		this.meslegi = meslegi;
		this.sicilKaydi = sicilKaydi;
	}

	public GercekKisi () {
		
	}

	@Override
	public String getInfo (ArrayList <Kisi> kisiListesi) {
	return "İsim Soyisim: "+ getAd() + "\nDoğum Tarihi: " + getDogumTarihi() + "\nKimlik Numarası: " + getKimlikNo() 
	        + "\nİkametgah Yeri: " + getYasadigiYer() + "\nİletişim Numarası: " + getTelefonNo() + "\nİletişim Adresi: " + getEmail() +"\nÖğrenim Durumu:" +getTahsili()
            + "\nMesleği: " + getMeslegi() + "\nSabıka Kaydı: " + getSicilKaydi();
	}
	
	@Override
	public String toString() {
		return  getAd() + "|" + getDogumTarihi()	+ "|" + getKimlikNo() + "|" + getYasadigiYer() + "|" + getTelefonNo() + "|" + getEmail()+ "|" + getTahsili()
		             + "|" + getMeslegi() + "|" + getSicilKaydi();
	}


	public String getTahsili() {
		return tahsili;
	}


	public void setTahsili(String tahsili) {
		this.tahsili = tahsili;
	}


	public String getMeslegi() {
		return meslegi;
	}


	public void setMeslegi(String meslegi) {
		this.meslegi = meslegi;
	}


	public String getSicilKaydi() {
		return sicilKaydi;
	}


	public void setSicilKaydi(String sicilKaydi) {
		this.sicilKaydi = sicilKaydi;
	}


}

