package mysite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.service.SiteService;
import mysite.vo.SiteVo;

public class SiteInterceptor implements HandlerInterceptor {
	private final SiteService siteService;
	
	@Autowired
	private ServletContext servletContext;
	
	public SiteInterceptor(SiteService siteService)	{
		this.siteService = siteService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String title = siteService.getSite().getTitle();
		
		SiteVo siteVo = siteService.getSite();
		servletContext.setAttribute("siteVo", siteVo);
		
//		request.setAttribute("title", title);
		return true;
	}
}
