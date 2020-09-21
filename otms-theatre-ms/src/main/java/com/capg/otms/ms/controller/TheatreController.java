package com.capg.otms.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.otms.ms.model.Theatre;
import com.capg.otms.ms.service.TheatreServiceImpl;

@RestController            //to handle request handling methods
@RequestMapping("/theatre")            
public class TheatreController {

	@Autowired          //@Autowired used to inject the bean class instances         
	TheatreServiceImpl theatreService;

	@GetMapping("/all")           //request mapping method which maps Http requests to the specified method
	public List<Theatre> findAllTheatres() {

		return theatreService.findAllTheatres();

	}

	@PostMapping("/add")      //request mapping method which mapps the url to add theatre
	public Theatre addTheatre(@RequestBody Theatre theatre) {             //@RequestBody binds the method return value to the web response body
		if (theatreService.validateTheatreId(theatre.getTheatreId())) {
			Theatre theatre1 = theatreService.addTheatre(theatre);
			theatre = theatre1;

		}
		return theatre;
	}

	@PutMapping("/update")    //request mapping method which mapps the url to update theatre
	public Theatre updateTheaterById(@RequestBody Theatre theatre) {    //@RequestBody binds the method return value to the web response body

		return theatreService.updateTheatre(theatre);

	}

	@DeleteMapping("/delete/id/{id}")  //request mapping method which mapps the url to delete theatre
	public void deleteTheatreById(@PathVariable("id") Integer theatreId) {    //@PathVariable is used extract the value from the URL
		theatreService.deleteTheatreById(theatreId);  //delete the theatre if the id matches

	}

}