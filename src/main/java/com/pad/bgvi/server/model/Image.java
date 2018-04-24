package com.pad.bgvi.server.model;

import javax.persistence.Column;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "IMAGES")
public class Image extends Entity{
	@Column(name = "IMAGE", nullable = false)
	private byte[] image;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
