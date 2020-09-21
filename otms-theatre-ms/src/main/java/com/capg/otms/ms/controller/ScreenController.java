package com.capg.otms.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.otms.ms.model.Screen;
import com.capg.otms.ms.service.ScreenServiceImpl;


@RestController                 //to handle request handling methods
@RequestMapping("/screen")

public class ScreenController {

	@Autowired                 //@Autowired used to inject the bean class instances 
	ScreenServiceImpl screenService;

	public void addDummyScreens() {

	}
	@GetMapping("/all")          //request mapping method which maps Http requests to the specified method
	public List<Screen> findAllScreens() {

		return screenService.findAllScreens();

	}

	@PostMapping("/add")           //request mapping method which mapps the url to add theatre
	public Screen addScreen(@RequestBody Screen screen) {   //@RequestBody binds the method return value to the web response body
		if (screenService.validateScreenId(screen.getScreenId())) {
			Screen screen1 = screenService.addScreen(screen);
			screen = screen1;
		}
		return screen;

	}

	@PutMapping("/update")        //request mapping method which mapps the url to update theatre
	public Screen updateTheScreenById(@RequestBody Screen screen) {  //@RequestBody binds the method return value to the web response body

		return screenService.updateScreenById(screen);

	}

	@DeleteMapping("/delete/id/{id}")    //request mapping method which mapps the url to delete theatre
	public void deleteScreenById(@PathVariable("id") int screenId) {  //@PathVariable is used extract the value from the URL

		screenService.deleteScreenById(screenId);

	}
}