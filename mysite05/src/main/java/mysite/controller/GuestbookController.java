package mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mysite.service.GuestbookService;
import mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	private GuestbookService guestbookService;
	
	public GuestbookController(GuestbookService guestbookService) {
		this.guestbookService = guestbookService;
	}
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("list", guestbookService.getContentsList());
		return "guestbook/list";
	}
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		guestbookService.addContents(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, GuestbookVo vo) {
		guestbookService.deleteContents(id, vo.getPassword());
		return "redirect:/guestbook";
	}
	
}
