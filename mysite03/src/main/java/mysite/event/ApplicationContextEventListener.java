package mysite.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import mysite.service.SiteService;
import mysite.vo.SiteVo;

public class ApplicationContextEventListener {
	@Autowired
	private ApplicationContext applicationContext;

	@EventListener({ContextRefreshedEvent.class})
	public void handlerContextRefreshedEvent() {
		SiteService siteService = applicationContext.getBean(SiteService.class);
		SiteVo siteVo = siteService.getSite();
		
        // ApplicationContext에 SiteVo를 Bean으로 등록
        ((org.springframework.beans.factory.support.DefaultListableBeanFactory) 
            applicationContext.getAutowireCapableBeanFactory())
            .registerSingleton("siteVo", siteVo);
		
		System.out.println("-- ContextRefreshedEvent Received --" + siteService);
	}
}
