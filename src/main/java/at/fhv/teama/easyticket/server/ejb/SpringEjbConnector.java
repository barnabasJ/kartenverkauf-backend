package at.fhv.teama.easyticket.server.ejb;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringEjbConnector implements ApplicationContextAware {
    static ApplicationContext apx;

    /**
     * This method is called from within the ApplicationContext once it is
     * done starting up, it will stick a reference to itself into this bean.
     * @param context a reference to the ApplicationContext.
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        apx = context;
    }

    /**
     * This is about the same as context.getBean("beanName"), except it has its
     * own static handle to the Spring context, so calling this method statically
     * will give access to the beans by name in the Spring application context.
     * As in the context.getBean("beanName") call, the caller must cast to the
     * appropriate target class. If the bean does not exist, then a Runtime error
     * will be thrown.
     * @param beanClass the class of the bean to get.
     * @return an Object reference to the named bean.
     */
    public static <T> T getBean(Class<T> clazz) {
        return apx.getBean(clazz);
    }
}
