package su.vfe.foodmanager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String root() {
		return "redirect:/client";
	}

	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String client() {
		return "index";
	}
}