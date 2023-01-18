package com.fdmgroup.CarSiteSoloProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Car_Bids")
public class OnlineBids {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  bidID;
	
	@Column(name = "fk_dealerID")
	private int dealerID;
	
	@Column(name = "fk_carID")
	private int carAdID;
	
	@Column(name = "fk_customerID")
	private int customerID;
	
	private String bidderEmail;
	private int bidPrice;
	private boolean isActive;


	
	
	public OnlineBids() {
		super();
	}

	public OnlineBids(int dealerID, int carAdID, int customerID, String bidderEmail, int bidPrice, boolean isActive) {
		super();
		this.dealerID = dealerID;
		this.carAdID = carAdID;
		this.customerID = customerID;
		this.bidderEmail = bidderEmail;
		this.bidPrice = bidPrice;
		this.isActive = isActive;
	}

	public int getBidID() {
		return bidID;
	}

	public void setBidID(int bidID) {
		this.bidID = bidID;
	}

	public int getDealerID() {
		return dealerID;
	}

	public void setDealerID(int dealerID) {
		this.dealerID = dealerID;
	}

	public int getCarAdID() {
		return carAdID;
	}

	public void setCarAdID(int carAdID) {
		this.carAdID = carAdID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getBidderEmail() {
		return bidderEmail;
	}

	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}

	
	
	
	
	
	

}
