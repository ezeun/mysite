package mysite.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import mysite.service.BoardService;
import mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("")
	public String list(Model model) {
		Map<String, Object> m = boardService.getContentsList(1, 1, 5, "");
		model.addAttribute("pageInfo", m.get("pageInfo"));
		model.addAttribute("list", m.get("list"));
		return "board/list";
	}
	
	@RequestMapping("/{firstPage}/{curPage}/{lastPage}")
	public String list(@PathVariable("firstPage") int firstPage,
						@PathVariable("curPage") int curPage,
						@PathVariable("lastPage") int lastPage, Model model) {
		Map<String, Object> m = boardService.getContentsList(firstPage, curPage, lastPage, "");
		model.addAttribute("pageInfo", m.get("pageInfo"));
		model.addAttribute("list", m.get("list"));
		return "board/list";
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vo", boardService.getContents(id));
		return "board/view";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardService.deleteContents(id, authUser.getId());
		return "redirect:/board";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("gNo", -1);
		model.addAttribute("oNo", -1);
		model.addAttribute("depth", -1);
		return "board/write";
	}
	
	@RequestMapping(value="write/{gNo}/{oNo}/{depth}", method=RequestMethod.GET)
	public String write(@PathVariable("gNo") int gNo, @PathVariable("oNo") int oNo, @PathVariable("depth") int depth, Model model) {
		model.addAttribute("gNo", gNo);
		model.addAttribute("oNo", oNo);
		model.addAttribute("depth", depth);
		return "board/write";
	}
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String write(int gNo, int oNo, int depth) {
		//여기부터
		boardService.addContents(null);
		return "redirect:/board";
	}
}
