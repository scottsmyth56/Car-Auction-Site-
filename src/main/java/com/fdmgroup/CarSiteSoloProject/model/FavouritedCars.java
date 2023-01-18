package com.fdmgroup.CarSiteSoloProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavouritedCars {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int favCarID;
	
	@Column(name = "fk_carID")
	private int carAdID;
	
	@Column(name = "fk_customerID")
	private int customerID;

	public int getFavCarID() {
		return favCarID;
	}

	public void setFavCarID(int favCarID) {
		this.favCarID = favCarID;
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
	
	
	
	
}
