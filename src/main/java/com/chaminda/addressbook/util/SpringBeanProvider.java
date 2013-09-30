/**
 * 
 */
package com.chaminda.addressbook.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chaminda
 *         <p/>
 *         date 19/09/2013
 */
public class SpringBeanProvider {

	private static ApplicationContext appContext;

	private SpringBeanProvider() {

	}

	private static ApplicationContext getContextInstance() {
		if (appContext == null) {
			appContext = new ClassPathXmlApplicationContext(
					new String[] { "springBeans/applicationContext.xml" });
		}

		return appContext;
	}

	public static Object getBean(final String beanName) {
		return getContextInstance().getBean(beanName);
	}
}