package com.capg.otms.ms.model;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity                        //mapping entity class to database table
@Table(name = "theatre")       //specifies the name of the database table to which it has to be mapped
public class Theatre {
	@Id                        //primary key of the table
	private Integer theatreId;
	private String theatreName;
	private String theatreCity;
	@ElementCollection         //collection of instances of a basic type or the embedded class
	private List<Integer> movies;
	@OneToMany
	private List<Screen> listOfScreens=new ArrayList<Screen>();
	public void setListOfScreens(List<Screen> listOfScreens) {
		this.listOfScreens = listOfScreens;
	}

	private String managerName;
	private String managerContact;
     // parameterized constructor with all fields initialized
	public Theatre(Integer theatreId, String theatreName, String theatreCity, List<Integer> movies,
			List<Screen> listOfScreens, String managerName, String managerContact) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.theatreCity = theatreCity;
		this.movies = movies;
		this.listOfScreens = listOfScreens;
		this.managerName = managerName;
		this.managerContact = managerContact;
	}

	public Theatre() {
		// TODO Auto-generated constructor stub   //default constructor
	}

	public Integer getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Integer theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getTheatreCity() {
		return theatreCity;
	}

	public void setTheatreCity(String theatreCity) {
		this.theatreCity = theatreCity;
	}

	public List<Integer> getMovies() {
		return movies;
	}

	public void setMovies(List<Integer> movies) {
		this.movies = movies;
	}

	public List<Screen> getListOfScreens() {
		return listOfScreens;
	}



	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerContact() {
		return managerContact;
	}

	public void setManagerContact(String managerContact) {
		this.managerContact = managerContact;
	}

	@Override            //overriding the object class method in this class
	public String toString() {
		return "Theatre [theatreId=" + theatreId + ", theatreName=" + theatreName + ", theatreCity=" + theatreCity
				+ ", movies=" + movies + ", listOfScreens=" + listOfScreens + ", managerName=" + managerName
				+ ", managerContact=" + managerContact + "]";
	}

}