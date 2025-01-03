package mysite.security;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mysite.service.SiteService;

public class SiteInterceptor implements HandlerInterceptor {
	private final SiteService siteService;
	
	public SiteInterceptor(SiteService siteService)	{
		this.siteService = siteService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String title = siteService.getSite().getTitle();
		request.setAttribute("title", title);
		return true;
	}
}
