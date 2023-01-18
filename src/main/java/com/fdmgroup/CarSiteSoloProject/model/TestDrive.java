package com.fdmgroup.CarSiteSoloProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


@Entity
public class TestDrive {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testDriveID;

	@Column(name = "fk_dealerID")
	private int dealerID;
	
	@Column(name = "fk_carID")
	private int carAdID;
	
	@Column(name = "fk_customerID")
	private int customerID;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookingDate;

	public int getTestDriveID() {
		return testDriveID;
	}

	public void setTestDriveID(int testDriveID) {
		this.testDriveID = testDriveID;
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

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	
	



	
	
	
	
	
	
}
