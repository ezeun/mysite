package mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mysite.service.SiteService;

@Controller
public class MainController {
	
	private SiteService siteService;
	
	public MainController(SiteService siteService) {
		this.siteService = siteService;
	}
	
	@RequestMapping({"/", "/main"})
	public String index(Model model) {
		model.addAttribute("vo", siteService.getSite());
		return "main/index";
	}
}
