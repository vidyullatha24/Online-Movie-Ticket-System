package com.capg.otms.ms.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity                           //mapping entity class to database table
public class Show {
@Id                               //primary key of the table
private Integer showId;
@DateTimeFormat(pattern = "yyyy/MM/ddThh:mm:ss")        //to print localized field value
private LocalDateTime showStartTime;
@DateTimeFormat(pattern = "yyyy/MM/ddThh:mm:ss")
private LocalDateTime showEndTime;
@ElementCollection                 //collection of instances of a basic type or the embedded class
private List<Integer> seats;


private String showName;
private int movieId;
public void setShowId(Integer showId) {
	this.showId = showId;
}

public void setMovieId(int movieId) {
	this.movieId = movieId;
}

public Show() {     //default constructor
	super();
}

public Show(int showId, LocalDateTime showStartTime, LocalDateTime showEndTime, List<Integer> seats, 
		String showName, int movieId, int screenId) {      //parameterized constructor
	this.showId = showId;
	this.showStartTime = showStartTime;
	this.showEndTime = showEndTime;
	this.seats = seats;
	
	this.showName = showName;
	this.movieId = movieId;
	//this.screenId = screenId;
	
}

public int getShowId() {
	return showId;
}
public void setShowId(int showId) {
	this.showId = showId;
}
public LocalDateTime getShowStartTime() {
	return showStartTime;
}
public void setShowStartTime(LocalDateTime showStartTime) {
	this.showStartTime = showStartTime;
}
public LocalDateTime getShowEndTime() {
	return showEndTime;
}
public void setShowEndTime(LocalDateTime showEndTime) {
	this.showEndTime = showEndTime;
}
public List<Integer> getSeats() {
	return seats;
}
public void setSeats(List<Integer> seats) {
	
	this.seats = seats;
}

public String getShowName() {
	return showName;
}
public void setShowName(String showName) {
	this.showName = showName;
}

public Integer getMovieId() {
	return movieId;
}
public void setMovieId(Integer movieId) {
	this.movieId = movieId;
}

	/*
	 * public int getScreenId() { return screenId; } public void setScreenId(int
	 * screenId) { this.screenId = screenId; }
	 */

@Override                                   //overriding the object class method in this class
public String toString() {
	return "Show [showId=" + showId + ", showStartTime=" + showStartTime + ", showEndTime=" + showEndTime + ", seats="
			+ seats + ", showName=" + showName + ", movieId=" + movieId + ", screenId=" + 
			 "]";
}



}