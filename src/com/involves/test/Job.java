package com.involves.test;

import com.involves.converter.Convertable;

public class Job implements Convertable {

	private String cargo;
	private String empresa;

	public Job(String cargo, String empresa) {
		this.cargo = cargo;
		this.empresa = empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
