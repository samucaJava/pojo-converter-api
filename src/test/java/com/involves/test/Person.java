package com.involves.test;

import java.util.List;

import com.involves.converter.Convertable;

public class Person implements Convertable {

	private String name;
	private int age;
	private Address address;
	private Job job;
	private List<Car> cars;
	private String[] bands;

	public Person(PessoaBuilder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.address = builder.address;
		this.job = builder.job;
		this.cars = builder.cars;
		this.bands = builder.bands;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public String[] getBands() {
		return bands;
	}

	public void setBands(String[] bands) {
		this.bands = bands;
	}

	public static class PessoaBuilder {
		private String name;
		private int age;
		private Address address;
		private Job job;
		private List<Car> cars;
		private String[] bands;

		public PessoaBuilder name(String name) {
			this.name = name;
			return this;

		}

		public PessoaBuilder age(int age) {
			this.age = age;
			return this;
		}

		public PessoaBuilder address(Address address) {
			this.address = address;
			return this;
		}

		public PessoaBuilder job(Job job) {
			this.job = job;
			return this;
		}

		public PessoaBuilder cars(List<Car> cars) {
			this.cars = cars;
			return this;
		}

		public PessoaBuilder bands(String[] bands) {
			this.bands = bands;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

}
