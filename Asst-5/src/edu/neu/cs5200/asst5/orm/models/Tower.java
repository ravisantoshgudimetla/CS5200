package edu.neu.cs5200.asst5.orm.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Tower {
	@Id
	private int id;
	private String name;
	private double height;
	private int sides;
	@ManyToOne
	@JoinColumn(name="siteId")
	private Site site;
	@OneToMany(mappedBy="tower", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Equipment> equipments;
	
	
	public Tower() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}   
	public int getSides() {
		return this.sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}
   
}

