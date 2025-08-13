package com.eazybank.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class home {
	
	
	@GetMapping("/home")
	public String checkHome()
	{
		return "Hello World";
	}

}
