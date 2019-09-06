package com.ct.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Tv {

	@Id
	@Column(name="TV_ID")
	private int tvId;
	@Size(min=1, max=17,message="TV name cannot be less than 3 or greater than 17 characters")
	@Column(name="TV_NAME")
	private String tvName;
	@Column(name="TV_BRAND")
	@NotEmpty(message="field is mandatory")
	private String tvBrand;
	@Column(name="TV_PRICE")
	@NotNull(message="field is mandatory")
	private int tvPrice;
	@Column(name="TV_DESCRIPTION")
	@Size(min=3, max=200,message="TV Description cannot be less than 3 or greater than 200 characters")
	private String tvDescription;
	@Column(name="TV_IMAGE")
	private String tvPath;
	
	
	
	public Tv() {
		
	}
	
	public int getTvId() {
		return tvId;
	}
	public void setTvId(int tvId) {
		this.tvId = tvId;
	}
	public String getTvName() {
		return tvName;
	}
	public void setTvName(String tvName) {
		this.tvName = tvName;
	}
	public String getTvBrand() {
		return tvBrand;
	}
	public void setTvBrand(String tvBrand) {
		this.tvBrand = tvBrand;
	}
	public int getTvPrice() {
		return tvPrice;
	}
	public void setTvPrice(int tvPrice) {
		this.tvPrice = tvPrice;
	}
	public String getTvDescription() {
		return tvDescription;
	}
	public void setTvDescription(String tvDescription) {
		this.tvDescription = tvDescription;
	}
	public String getTvPath() {
		return tvPath;
	}
	public void setTvPath(String tvPath) {
		this.tvPath = tvPath;
	}
	@Override
	public String toString() {
		return "Tv [tvId=" + tvId + ", tvName=" + tvName + ", tvBrand=" + tvBrand + ", tvPrice=" + tvPrice
				+ ", tvDescription=" + tvDescription + ", tvPath=" + tvPath + "]";
	}
	
	
}
