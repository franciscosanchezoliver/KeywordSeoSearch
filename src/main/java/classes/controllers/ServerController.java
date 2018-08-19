package classes.controllers;


import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import classes.scrapping.Report;
import classes.services.Service;
import classes.utils.JSONConverter;
import classes.utils.Server;

@Controller
public class ServerController {
    
	@RequestMapping("/serverInfo")
	@ResponseBody
	public String getServerInfo() {
		return "OK";
	}
       
}
