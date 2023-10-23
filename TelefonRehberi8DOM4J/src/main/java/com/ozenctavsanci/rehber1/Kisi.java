package com.ozenctavsanci.rehber1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;




public class Kisi implements Serializable {
	private String ad;
	private String dogumTarihi;
	private String kimlikNo;
	private String yasadigiYer;
	private int telefonNo;
	private String email;
	

	public Kisi() {	
		
	}
	
	public Kisi(String ad, String dogumTarihi, String kimlikNo, String yasadigiYer, int telefonNo, String email) {
		super();
		this.ad = ad;
		this.dogumTarihi = dogumTarihi;
		this.kimlikNo = kimlikNo;
		this.yasadigiYer = yasadigiYer;
		this.telefonNo = telefonNo;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Kisiler [ad=" + getAd() + "|" + getDogumTarihi() + "|" + getKimlikNo() + "|"
				+ getYasadigiYer() + "|" + getTelefonNo() + "|" + getEmail();
	}
	
@Override
	public int hashCode() {
		return Objects.hash(ad, telefonNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kisi other = (Kisi) obj;
		return Objects.equals(ad, other.ad) && Objects.equals(telefonNo, other.telefonNo);
	}

	public String getInfo (ArrayList <Kisi> kisiListesi) {
	return  getAd() +   getDogumTarihi() +  getKimlikNo()
	+ getYasadigiYer() +   getTelefonNo() +  getEmail();
    }
	
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getDogumTarihi() {
		return dogumTarihi;
	}
	public void setDogumTarihi(String dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
	public String getKimlikNo() {
		return kimlikNo;
	}
	public void setKimlikNo(String kimlikNo) {
		this.kimlikNo = kimlikNo;
	}
	public String getYasadigiYer() {
		return yasadigiYer;
	}
	public void setYasadigiYer(String yasadigiYer) {
		this.yasadigiYer = yasadigiYer;
	}
	public int getTelefonNo() {
		return telefonNo;
	}
	public void setTelefonNo(int telefonNo) {
		this.telefonNo = telefonNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	


	

}


