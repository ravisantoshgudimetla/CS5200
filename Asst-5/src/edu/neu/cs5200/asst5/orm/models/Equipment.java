package edu.neu.cs5200.asst5.orm.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Equipment {
	@Id
	private Integer id;
	public Equipment() {
		super();
	}
	public Equipment(Integer id, String name, String brand, String description,
			double price, Tower tower) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.tower = tower;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	private String name;
	private String brand;
	private String description;
	private double price;
	@ManyToOne
	@JoinColumn(name="towerId")
	private Tower tower;
	public Equipment(Tower tower) {
		super();
		this.tower = tower;
	}

}


