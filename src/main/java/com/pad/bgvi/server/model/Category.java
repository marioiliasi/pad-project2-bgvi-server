package com.pad.bgvi.server.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "CATEGORIES")
public class Category extends Entity{
	
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToOne
	@JoinColumn(name="IMAGE_ID")
	//@Column(name = "IMAGE_ID")
	private Image image;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
