package mysite.controller.action.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mysite.controller.ActionServlet.Action;
import mysite.dao.UserDao;
import mysite.vo.UserVo;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		UserVo vo = new UserDao().findById(authUser.getId());
		
		vo.setName(name);
		if(!password.equals("")) vo.setPassword(password);
		vo.setGender(gender);
		
		new UserDao().update(vo);
		
		request.setAttribute("vo", vo);
		
		UserVo sessionUser = new UserDao().findByEmailAndPassword(vo.getEmail(), vo.getPassword());
		session.setAttribute("authUser", sessionUser);
		
		response.sendRedirect(request.getContextPath()+"/user?a=updateform");
	}

}
