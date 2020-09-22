package com.capg.otms.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.otms.ms.exception.InvalidInputException;
import com.capg.otms.ms.exception.TheatreAlreadyExistsException;
import com.capg.otms.ms.exception.TheatreException;
import com.capg.otms.ms.model.Show;
import com.capg.otms.ms.reprository.ShowRepo;

@Service                                 //indicates the class is a service provider
public class ShowServiceImpl implements IShowService {

	@Autowired                             
	ShowRepo showRepo;

	 //Throws TheatreAlreadyExistException,Invalid Input Exception
	 //Adding Show to database	 
	public Show addShow(Show show) {
		Integer showId = show.getShowId();
		if (showId == 0) {
			throw new InvalidInputException("show id must be minimum of four characters");
		} else if (showRepo.existsById(showId)) {
			throw new TheatreAlreadyExistsException("user already exists with this id :" + showId);
		}
		return showRepo.saveAndFlush(show);

	}
	 //Updating Show in database
	public Show updateShowById(Show show) {

		int showId = show.getShowId();
		if (showId == 0) {
			throw new InvalidInputException("show id must be minimum of four characters");  // throws Invalid Input Exception,TheatreException
		} else if (showRepo.existsById(showId)) {
			Show updateShow = showRepo.getOne(showId);
			updateShow.setShowName(show.getShowName());
			updateShow.setShowStartTime(show.getShowStartTime());
			updateShow.setShowEndTime(show.getShowEndTime());
			showRepo.saveAndFlush(updateShow);
		}
		return show;

	}
	 //Deleting Show in database
	public boolean deleteShowById(Integer showId) {

		if (showRepo.existsById(showId)) {

			showRepo.deleteById(showId);
		} else {
			throw new TheatreException("Id not found");    //Throws TheatreException
		}
		return true;

	}
	
	 //Throws TheatreException
	 //Shows All the Shows present in Database

	public List<Show> findAllShows() {
		return showRepo.findAll();

	}
	
	//Throws Invalid Input Exception
	//Fetches Show from Database based on ShowId

	public Show getShowById(Integer showId) {

		return showRepo.getOne(showId);
	}

	 //Validates the ShowDetails while adding Show into Database

	public boolean validateShowId(int showId) {
		String show = Integer.toString(showId);
		if (!(show.length()>=8))
			throw new TheatreException("ShowId must be minimum of 8 characters"); //Throws TheatreException

		return true;
	}


}