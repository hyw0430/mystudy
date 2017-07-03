package springsecurity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	private FilterSecurityInterceptor filterSecurityInterceptor;
	private MyFilterInvocationSecurityMetadataSource2 metadataSource;
	private boolean isSetter = false;
	
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		//1. 先获取 FilterSecurityInterceptor 对应的 bean
		if(arg0 instanceof FilterSecurityInterceptor){
			this.filterSecurityInterceptor = (FilterSecurityInterceptor) arg0;
		}
		
		//2. 再获取 MyFilterInvocationSecurityMetadataSource2 对应的 bean
		if(arg0 instanceof MyFilterInvocationSecurityMetadataSource2){
			this.metadataSource = (MyFilterInvocationSecurityMetadataSource2) arg0;
		}
		
		//3. 若这两个 bean 都获取到了, 则替换属性
		if(this.filterSecurityInterceptor != null
				&& this.metadataSource != null
				&& !isSetter){
			try {
				filterSecurityInterceptor.setSecurityMetadataSource(metadataSource.getObject());
				isSetter = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

}
