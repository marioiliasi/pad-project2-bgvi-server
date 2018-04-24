package com.pad.bgvi.server.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "PRODUCTS")
public class Product extends Entity{
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID")
	//@Column(name = "CATEGORY_ID")
	private Category category;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "COLORS")
	private String colors;
	@Column(name = "SIZES")
	private String sizes;
	@Column(name = "ACTIVE_COLORS")
	private String activeColors;
	@Column(name = "ACTIVE_SIZES")
	private String activeSizes;
	@Column(name = "CURRENT_UNIT_PRICE")
	private Double currentUnitPrice;
	@Column(name = "UNIT_PRICE")
	private Double unitPrice;
	@Column(name = "UNIT_IN_STOCK")
	private Double unitInStock;
	
	@OneToOne
	@JoinColumn(name="IMAGE_ID")
	//@Column(name = "IMAGE_ID")
	private Image image;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
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
	public String getColors() {
		return colors;
	}
	public void setColors(String colors) {
		this.colors = colors;
	}
	public String getSizes() {
		return sizes;
	}
	public void setSizes(String sizes) {
		this.sizes = sizes;
	}
	public String getActiveColors() {
		return activeColors;
	}
	public void setActiveColors(String activeColors) {
		this.activeColors = activeColors;
	}
	public String getActiveSizes() {
		return activeSizes;
	}
	public void setActiveSizes(String activeSizes) {
		this.activeSizes = activeSizes;
	}
	public Double getCurrentUnitPrice() {
		return currentUnitPrice;
	}
	public void setCurrentUnitPrice(Double currentUnitPrice) {
		this.currentUnitPrice = currentUnitPrice;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getUnitInStock() {
		return unitInStock;
	}
	public void setUnitInStock(Double unitInStock) {
		this.unitInStock = unitInStock;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
}
