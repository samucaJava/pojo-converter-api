package com.involves.test;

import java.math.BigDecimal;

import com.involves.converter.Convertable;

public class Car implements Convertable {

	private String model;
	private long year;
	private String color;
	private BigDecimal price;
	private Double tankCapacity;

	public Car(String model, long year, String color, BigDecimal price, Double tankCapacity) {
		super();
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		this.tankCapacity = tankCapacity;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getTankCapacity() {
		return tankCapacity;
	}

	public void setTankCapacity(Double tankCapacity) {
		this.tankCapacity = tankCapacity;
	}

}
