package springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

public class MyFilterInvocationSecurityMetadataSource2 implements
		FactoryBean<DefaultFilterInvocationSecurityMetadataSource> {

	private FilterInvocationSecurityMetadataSourceMapBuilder builder;
	
	public void setBuilder(
			FilterInvocationSecurityMetadataSourceMapBuilder builder) {
		this.builder = builder;
	}
	
	@SuppressWarnings("unused")
	@Override
	public DefaultFilterInvocationSecurityMetadataSource getObject()
			throws Exception {
		//实际从数据库中获取的是一个 Map
		//key: 受保护的资源. 即 URL
		//value: 可以访问该资源的权限的名字的集合
		//例如: {"/admin.jsp"=["ROLE_ADMIN"], "/index.jsp"=["ROLE_ADMIN","ROLE_USER"]}
		LinkedHashMap<String, List<String>> srcMap = builder.buildSrcMap();
		
		//初始化 DefaultFilterInvocationSecurityMetadataSource 必须的 requestMap
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();
		
		RequestMatcher matcher = null;
		Collection<ConfigAttribute> attributes = null;
		
		if(srcMap != null && srcMap.size() > 0){
			for(Map.Entry<String, List<String>> entry: srcMap.entrySet()){
				matcher = new AntPathRequestMatcher(entry.getKey());
				attributes = new ArrayList<>();
				
				for(String roleName: entry.getValue()){
					attributes.add(new SecurityConfig(roleName));
				}
				requestMap.put(matcher, attributes);
			}
		}
		
		DefaultFilterInvocationSecurityMetadataSource metadataSource = 
				new DefaultFilterInvocationSecurityMetadataSource(requestMap);
		return metadataSource;
	}

	@Override
	public Class<?> getObjectType() {
		return DefaultFilterInvocationSecurityMetadataSource.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
