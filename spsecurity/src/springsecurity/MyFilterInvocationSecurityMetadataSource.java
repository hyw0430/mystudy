package springsecurity;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.RequestMatcher;

public class MyFilterInvocationSecurityMetadataSource extends
		DefaultFilterInvocationSecurityMetadataSource {

	public MyFilterInvocationSecurityMetadataSource(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		//super() 之前不允许有任何的代码.
		super(requestMap);
	}

}
