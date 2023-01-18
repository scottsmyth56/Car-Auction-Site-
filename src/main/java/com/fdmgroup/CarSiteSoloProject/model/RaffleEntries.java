package com.fdmgroup.CarSiteSoloProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RaffleEntries {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  EntryID;
	
	@Column(name = "fk_dealerID")
	private int dealerID;
	

	@Column(name = "fk_customerID")
	private int customerID;

	
	

	public RaffleEntries() {
		super();
	}


	public RaffleEntries(int entryID, int dealerID, int customerID) {
		super();
		EntryID = entryID;
		this.dealerID = dealerID;
		this.customerID = customerID;
	}


	public int getEntryID() {
		return EntryID;
	}


	public void setEntryID(int entryID) {
		EntryID = entryID;
	}


	public int getDealerID() {
		return dealerID;
	}


	public void setDealerID(int dealerID) {
		this.dealerID = dealerID;
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	
	
	
}
