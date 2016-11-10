package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vizzavi.ecommerce.common.ErCountry;

/**
 * a parent bean for all priceplan objects, created for the priceplan refactor project
 * @author matt
 *
 */
@Entity
public class Priceplan implements Serializable, CatalogBean	{

	@OneToMany(mappedBy="priceplan",targetEntity=CatalogPackage.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CatalogPackage> packages;

	@OneToMany(mappedBy="priceplan",targetEntity=CatalogService.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CatalogService> services;
	
	private ErCountry opco;
	private String name;
	private String description;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="`KEY`")
	private Long key;
	
	Priceplan(){}
	
	public Priceplan(ErCountry opco, String name)	{
		this.opco=opco;
		this.name=name;
	}
	
	public  List<CatalogPackage> getPackages() {
		return packages;
	}
	
	public  void setPackages(List<CatalogPackage> packages) {
		this.packages = packages;
		for(CatalogPackage p: packages)	{
			p.setPriceplan(this);
		}
	}
	
	public  List<CatalogService> getServices() {
		return services;
	}
	
	public  void setServices(List<CatalogService> services) {
		this.services = services;
		for (CatalogService s: services){
			s.setPriceplan(this);
		}
	}
	
	public ErCountry getOpco() {
		return opco;
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

	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}


	
	
}
