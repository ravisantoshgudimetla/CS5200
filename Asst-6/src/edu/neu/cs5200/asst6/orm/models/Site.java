package edu.neu.cs5200.asst6.orm.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;

import java.util.*;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)

public class Site {
    @Id
    @XmlAttribute
	private Integer id;
    @OneToMany(mappedBy="site",cascade=CascadeType.ALL, orphanRemoval=true)
    @XmlElement(name="tower")
    private List<Tower> Towers;
    
	public Site(List<Tower> towers) {
		super();
		Towers = towers;
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Site(Integer id, String name, double latitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@XmlAttribute
	private String name;
	public Site() {
		super();
	}
	@XmlAttribute
	private double latitude;
	@XmlAttribute
	private double longitude;
}

