package com.fdmgroup.CarSiteSoloProject.model;

import java.nio.charset.StandardCharsets;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Dealers")
public class Dealer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dealerID;

	private String dealerName;
	private String location;
	private String about;
	private String email;
	
	
	

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] logo;

	@Transient
	private String base64Image;
	@Transient
	private MultipartFile file;
	

	public Dealer() {
		super();
	}

	public Dealer(String dealerName, String location, String about, String email) {
		super();
		this.dealerName = dealerName;
		this.location = location;
		this.about = about;
		this.email = email;
	}



	public int getDealerID() {
		return dealerID;
	}

	public void setDealerID(int dealerID) {
		this.dealerID = dealerID;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getBase64Image() {
		return new String(getLogo(), StandardCharsets.UTF_8);
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
