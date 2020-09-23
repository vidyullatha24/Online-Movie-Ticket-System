package com.capg.otms.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.otms.ms.model.Show;
import com.capg.otms.ms.service.ShowServiceImpl;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController                              //to handle request handling methods
@RequestMapping("/show")
public class ShowController {

	@Autowired                          //@Autowired used to inject the bean class instances 
	ShowServiceImpl showService;

	@GetMapping("/all")                 //request mapping method which maps Http requests to the specified method
	public ResponseEntity<List<Show>> findAllShows() {

		List<Show> list = showService.findAllShows();
		ResponseEntity<List<Show>> responseEntity = new ResponseEntity<List<Show>>(list, HttpStatus.OK);
		return responseEntity;

	}

	@PostMapping("/add")               //request mapping method which mapps the url to add theatre
	public ResponseEntity<Show> addShow(@RequestBody Show show) {
		return new ResponseEntity<Show>(showService.addShow(show), HttpStatus.CREATED);

	}

	@PutMapping("/update")             //request mapping method which mapps the url to update theatre
	public ResponseEntity<Show> updateTheShowById(@RequestBody Show show) {

		if (show != null) {
			show = showService.updateShowById(show);
			return new ResponseEntity<Show>(show, HttpStatus.OK);
		}
		return new ResponseEntity<Show>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/delete/id/{id}")             //request mapping method which mapps the url to delete theatre
	public ResponseEntity<Show> deleteShowById(@PathVariable("id") Integer showId) {   //@PathVariable is used extract the value from the URL
		if (showId != 0) {
			showService.deleteShowById(showId);
			return new ResponseEntity<Show>(HttpStatus.OK);
		}
		return new ResponseEntity<Show>(HttpStatus.NOT_FOUND);
	}
}
