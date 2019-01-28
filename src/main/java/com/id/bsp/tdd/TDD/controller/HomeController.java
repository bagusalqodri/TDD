package com.id.bsp.tdd.TDD.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.id.bsp.tdd.TDD.model.UserModel;


@RestController
public class HomeController{
	  
	@RequestMapping(path = "/")
	public ModelAndView home() {
		ModelAndView home = new ModelAndView("home");
		return home;
	}
	
	@RequestMapping(path = "/api/tdd/{id}", method= RequestMethod.GET, produces = "application/json")
	public String getData(@PathVariable final int id) throws Exception {
		UserModel user = new UserModel();
		user.setId(id);
		user.setName("Bagus Al-Qodri");
		user.setContractDuration(10);
		  
		Gson gson = new Gson();
		return gson.toJson(user);
	}
      
}
