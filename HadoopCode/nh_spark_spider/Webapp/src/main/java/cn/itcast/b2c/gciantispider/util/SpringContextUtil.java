package cn.itcast.b2c.gciantispider.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;
	
	 public void setApplicationContext(ApplicationContext applicationContext )
	  {
	    context = applicationContext;
	  }

	  public static ApplicationContext getApplicationContext() {
	    return context;
	  }

	  public static <T> T getBean(String name)
	  {
	    return  (T) context.getBean(name);
	  }
	  
	  public static <T> T getBean(Class<T> clazz) {
	        return context.getBean(clazz);
	    }
	  


}
