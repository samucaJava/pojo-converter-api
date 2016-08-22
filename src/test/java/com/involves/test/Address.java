package com.involves.test;

import com.involves.converter.Convertable;

public class Address implements Convertable {

	private String street;
	private long number;

	public Address() {

	}

	public Address(String street, long number) {
		this.street = street;
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

}
