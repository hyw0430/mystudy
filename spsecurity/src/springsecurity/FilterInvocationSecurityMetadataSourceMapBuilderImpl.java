package springsecurity;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class FilterInvocationSecurityMetadataSourceMapBuilderImpl implements
		FilterInvocationSecurityMetadataSourceMapBuilder {

	@Override
	public LinkedHashMap<String, List<String>> buildSrcMap() {
		LinkedHashMap<String, List<String>> srcMap = 
				new LinkedHashMap<>();
		
		srcMap.put("/admin.jsp", Arrays.asList("ROLE_ADMIN"));
		srcMap.put("/user.jsp", Arrays.asList("ROLE_USER"));
				
		return srcMap;
	}

}
