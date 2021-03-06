package com.cobble.takeaway.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.service.PrivilegeService;

public class MyFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	
	@Autowired
	private PrivilegeService privilegeService;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		List<ConfigAttribute> ret = new ArrayList<ConfigAttribute>();
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		try {
			/*List<PrivilegePOJO> privilegePOJOs = privilegeService.finds(null);
			if (!CollectionUtils.isEmpty(privilegePOJOs)) {
				for (PrivilegePOJO privilegePOJO : privilegePOJOs) {
					String privilegeUrl = privilegePOJO.getUrl();
					PathMatcher pathMatcher = new AntPathMatcher();
					String[] privilegeUrls = StringUtils.split(privilegeUrl, ",");
					boolean isMatcher = false;
					if (ArrayUtils.isNotEmpty(privilegeUrls)) {
						for (String privilegeUrlTemp : privilegeUrls) {
							isMatcher = pathMatcher.match(StringUtils.trim(privilegeUrlTemp), url);
							if (isMatcher) {
								break;
							}
						}
					}
					
					if (isMatcher) {
						List<RolePOJO> rolePOJOs = privilegePOJO.getRolePOJOs();
						if (CollectionUtils.isEmpty(rolePOJOs)) {
							continue;
						}
						for (RolePOJO rolePOJO : rolePOJOs) {
							ConfigAttribute configAttribute = new SecurityConfig(rolePOJO.getRoleName());
							ret.add(configAttribute);
						}
					}
				}
			}*/
			List<String> roles = privilegeService.findRoles(url);
			
			if (!CollectionUtils.isEmpty(roles)) {
				for (String role : roles) {
					ConfigAttribute configAttribute = new SecurityConfig(role);
					ret.add(configAttribute);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (CollectionUtils.isEmpty(ret)) {	// Because if ret == null, will allow any access.... TODO
			ConfigAttribute NO_FOUND_ANY_ROLE = new SecurityConfig("NO_FOUND_ANY_ROLE");
			ret.add(NO_FOUND_ANY_ROLE);
			ret.add(new SecurityConfig("ROLE_ANONYMOUS"));
		}
		
		return ret;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
