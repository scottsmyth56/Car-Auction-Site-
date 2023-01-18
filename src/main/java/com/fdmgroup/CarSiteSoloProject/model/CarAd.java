package com.fdmgroup.CarSiteSoloProject.model;

import java.nio.charset.StandardCharsets;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class CarAd {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carAdID;

	@Column(name = "fk_dealerID")
	private int dealerID;

	private String make;
	private String model;
	private int price;
	private String colour;
	private double engineSize;
	private String extraInfo;
	private boolean isActive;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] exteriorImage;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] exteriorImage2;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] interiorImage;

	@Transient
	private String base64Exterior;

	@Transient
	private String base64Interior;

	@Transient
	private String base64Exterior2;

	@Transient
	private MultipartFile fileExterior;
	
	@Transient
	private MultipartFile fileExterior2;
	
	@Transient
	private MultipartFile fileInterior;
	
	

	public CarAd() {
		super();
	}
	
	





	public CarAd(int dealerID, String make, String model, int price, String colour, double engineSize, String extraInfo,
			boolean isActive) {
		super();
		this.dealerID = dealerID;
		this.make = make;
		this.model = model;
		this.price = price;
		this.colour = colour;
		this.engineSize = engineSize;
		this.extraInfo = extraInfo;
		this.isActive = isActive;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public int getCarAdID() {
		return carAdID;
	}

	public void setCarAdID(int carAdID) {
		this.carAdID = carAdID;
	}

	public int getDealerID() {
		return dealerID;
	}

	public void setDealerID(int dealerID) {
		this.dealerID = dealerID;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public double getEngineSize() {
		return engineSize;
	}

	public void setEngineSize(double engineSize) {
		this.engineSize = engineSize;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public byte[] getExteriorImage() {
		return exteriorImage;
	}

	public void setExteriorImage(byte[] exteriorImage) {
		this.exteriorImage = exteriorImage;
	}

	public byte[] getExteriorImage2() {
		return exteriorImage2;
	}

	public void setExteriorImage2(byte[] exteriorImage2) {
		this.exteriorImage2 = exteriorImage2;
	}

	public byte[] getInteriorImage() {
		return interiorImage;
	}

	public void setInteriorImage(byte[] interiorImage) {
		this.interiorImage = interiorImage;
	}


	public MultipartFile getFileExterior() {
		return fileExterior;
	}

	public void setFileExterior(MultipartFile fileExterior) {
		this.fileExterior = fileExterior;
	}

	public MultipartFile getFileExterior2() {
		return fileExterior2;
	}

	public void setFileExterior2(MultipartFile fileExterior2) {
		this.fileExterior2 = fileExterior2;
	}

	public MultipartFile getFileInterior() {
		return fileInterior;
	}

	public void setFileInterior(MultipartFile fileInterior) {
		this.fileInterior = fileInterior;
	}

	public String getBase64Exterior() {
		return new String(getExteriorImage(), StandardCharsets.UTF_8);
	}

	public void setBase64Exterior(String base64Exterior) {
		this.base64Exterior = base64Exterior;
	}

	public String getBase64Interior() {
		return new String(getInteriorImage(), StandardCharsets.UTF_8);
	}

	public void setBase64Interior(String base64Interior) {
		this.base64Interior = base64Interior;
	}

	public String getBase64Exterior2() {
		return new String(getExteriorImage2(), StandardCharsets.UTF_8);
	}

	public void setBase64Exterior2(String base64Exterior2) {
		this.base64Exterior2 = base64Exterior2;
	}

}
