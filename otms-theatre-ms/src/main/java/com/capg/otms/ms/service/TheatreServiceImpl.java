package com.capg.otms.ms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.otms.ms.exception.InvalidInputException;
import com.capg.otms.ms.exception.TheatreAlreadyExistsException;
import com.capg.otms.ms.exception.TheatreException;
import com.capg.otms.ms.model.Theatre;
import com.capg.otms.ms.reprository.ITheatreRepo;

@Service                             //indicates that the class is a service provider
public class TheatreServiceImpl implements ITheatreService {
	
	@Autowired
	ITheatreRepo theatreRepo;
	
	 //Throws TheatreAlreadyExistException, Invalid Input Exception
	 //Adding Theatre to database
	public Theatre addTheatre(Theatre theatre) {
		Integer theatreId = theatre.getTheatreId();
		if (theatreId == 0) {
			throw new InvalidInputException("theatre id must be minimum of four characters starting with 2");
		} else if (theatreRepo.existsById(theatreId)) {
			throw new TheatreAlreadyExistsException("user already exists with this id :" + theatreId);
		}

		return theatreRepo.saveAndFlush(theatre);
	}

	
	 //Throws Invalid Input Exception,TheatreException
	 //Updating Theatre in database
	public Theatre updateTheatre(Theatre theatre) {
		Integer theatreId = theatre.getTheatreId();
		if (theatreId == 0) {
			throw new InvalidInputException("theatre id must be minimum of four characters starting with 2");
		} else if (theatreRepo.existsById(theatreId)) {
			Theatre updateTheatre = theatreRepo.getOne(theatreId);
			updateTheatre.setTheatreName(theatre.getTheatreName());
			updateTheatre.setTheatreCity(theatre.getTheatreCity());
			updateTheatre.setMovies(theatre.getMovies());
			updateTheatre.setManagerName(theatre.getManagerName());
			updateTheatre.setManagerContact(theatre.getManagerContact());
			theatreRepo.saveAndFlush(updateTheatre);
		}

		else {
			throw new TheatreException("Id not found");
		}
		return theatre;
	}
	
	 //Deleting Theatre in database
	
	public boolean deleteTheatreById(Integer theatreId) {

		if (theatreRepo.existsById(theatreId)) {

			theatreRepo.deleteById(theatreId);
		} else {
			throw new TheatreException("Id not found");
		}
		return true;

	}

	
	 //Throws TheatreException
	 //Shows All the Theatres present in Database
	public List<Theatre> findAllTheatres() {

		return theatreRepo.findAll();
	}

	 //Fetching Theatre from Database
	public Theatre getTheatreByName(String theatreName) {

		return theatreRepo.getByTheatreName(theatreName);
	}

	
	 //Throws TheatreException
	 // Validates the TheatreDetails while adding Theatre into Database
	                                                                                                         //@Override
	public boolean validateTheatreId(Integer theatreId) {
		String theatre = Integer.toString(theatreId);
		if (!(theatre.length() >= 4 && theatre.charAt(0) == '2')) {
			throw new TheatreException("TheatreId must be minimum of 4 characters, starting with 2..");
		}
		return true;
	}

	 //Throws Invalid Input Exception
	 //from Database based on given Id
	                                                                                                         //@Override
	public Theatre getTheatreById(Integer theatreId) {
		return theatreRepo.getByTheatreId(theatreId);
	}
}