package mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mysite.security.Auth;
import mysite.service.FileUploadService;
import mysite.service.SiteService;
import mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	private final SiteService siteService;
	private final FileUploadService fileUploadService;
	
	public AdminController(SiteService siteService, FileUploadService fileUploadService) {
		this.siteService = siteService;
		this.fileUploadService = fileUploadService;
	}
	
	@RequestMapping({"", "/main"})
	public String main(Model model) {
		model.addAttribute("vo", siteService.getSite());
		return "admin/main";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(SiteVo vo,
						 @RequestParam("file") MultipartFile file) {
		vo.setProfile(fileUploadService.restore(file));
		siteService.updateSite(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/guestbook")
	public String guestBook(){
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board(){
		return "admin/board";
	}
}
