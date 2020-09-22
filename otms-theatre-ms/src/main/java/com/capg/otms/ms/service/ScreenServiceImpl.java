package com.capg.otms.ms.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capg.otms.ms.exception.InvalidInputException;
import com.capg.otms.ms.exception.TheatreAlreadyExistsException;
import com.capg.otms.ms.exception.TheatreException;
import com.capg.otms.ms.model.Screen;
import com.capg.otms.ms.reprository.ScreenRepo;


@Service                                //indicates the class is a service provider
public class ScreenServiceImpl implements IScreenService {

	@Autowired
	ScreenRepo screenRepo;
	 //Adding Screen to database
	public Screen addScreen(Screen screen) {
		Integer screenId = screen.getScreenId();
		if (screenId == 0) {
			throw new InvalidInputException("screen id must be minimum of four characters");  //TheatreAlreadyExistException,Invalid Input Exception
		} else if (screenRepo.existsById(screenId)) {
			throw new TheatreAlreadyExistsException("user already exists with this id :" + screenId);
		}

		return screenRepo.saveAndFlush(screen);
	}

	 // Updating Screen in database
	 
	public Screen updateScreenById(Screen screen) {
		Integer screenId = screen.getScreenId();
		if (screenId == 0) {
			throw new InvalidInputException("screen id must be minimum of four characters ");   //Invalid Input Exception,TheatreException
		} else if (screenRepo.existsById(screenId)) {
			Screen updateScreen = screenRepo.getOne(screenId);

			updateScreen.setScreenName(screen.getScreenName());
			updateScreen.setColumns(screen.getColumns());
			updateScreen.setRows(screen.getRows());
			updateScreen.setMovieEndDate(screen.getMovieEndDate());
			screenRepo.saveAndFlush(updateScreen);
		}
		return screen;
	}

	//Deleting Screen in database
	public boolean deleteScreenById(int screenId) {

		if (screenRepo.existsById(screenId)) {
			screenRepo.deleteById(screenId);
		} else {
			throw new TheatreException("Id not found");
		}
		return true;
	}
	
	 // throws TheatreException 
	 //Shows All the Screens present in Database
	public List<Screen> findAllScreens(){

		return screenRepo.findAll();
	}

	 // Invalid Input Exception
	 //Fetches Screen from Database based on ScreenId
	public Screen getScreenById(int screenId) {
		return screenRepo.getOne(screenId);
	}
	
	 //Validates the ScreenDetails while adding Screen into Database

	public boolean validateScreenId(int screenId) {
		String screen = Integer.toString(screenId);
		if (!(screen.length()>= 4))
			throw new TheatreException("ScreenId must be minimum of 4 characters");
		return true;
	}
}