package com.cobble.takeaway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import com.cobble.takeaway.dao.PrivilegeMapper;
import com.cobble.takeaway.dao.RelPrivilegeRoleMapper;
import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.PrivilegeSearchPOJO;
import com.cobble.takeaway.pojo.RelPrivilegeRolePOJO;
import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	
	private static final Logger logger = LoggerFactory.getLogger(PrivilegeServiceImpl.class);
	
	@Autowired
	private PrivilegeMapper privilegeMapper;
	@Autowired
	private RelPrivilegeRoleMapper relPrivilegeRoleMapper;

	@Override
	public int insert(PrivilegePOJO privilegePOJO) throws Exception {
		int ret = 0;
		ret = privilegeMapper.insert(privilegePOJO);
		if (!CollectionUtils.isEmpty(privilegePOJO.getRoleId())) {
			for (Integer roleId : privilegePOJO.getRoleId()) {
				RelPrivilegeRolePOJO relPrivilegeRolePOJO = new RelPrivilegeRolePOJO();
				relPrivilegeRolePOJO.setPrivilegeId(privilegePOJO.getPrivilegeId());
				relPrivilegeRolePOJO.setRoleId(roleId);
				relPrivilegeRoleMapper.insert(relPrivilegeRolePOJO);
			}
		}
		return ret;
	}

	@Override
	public int update(PrivilegePOJO privilegePOJO) throws Exception {
		int ret = 0;
		ret = privilegeMapper.update(privilegePOJO);
		return ret;
	}

	@Override
	public List<PrivilegePOJO> finds(
			PrivilegeSearchPOJO privilegeSearchPOJO) throws Exception {
		List<PrivilegePOJO> ret = null;
		ret = privilegeMapper.finds(privilegeSearchPOJO);
		return ret;
	}

	@Override
	public int getCount(PrivilegeSearchPOJO privilegeSearchPOJO)
			throws Exception {
		int ret = 0;
		ret = privilegeMapper.getCount(privilegeSearchPOJO);
		return ret;
	}

	@Override
	public PrivilegePOJO findById(Integer id) throws Exception {
		PrivilegePOJO ret = null;
		ret = privilegeMapper.findById(id);
		return ret;
	}

	@Override
	public int delete(Integer id) throws Exception {
		int ret = 0;
		ret = privilegeMapper.deleteById(id);
		return ret;
	}

	@Override
	public int delete(Integer[] ids) throws Exception {
		int ret = 0;
		if (ids == null) {
			return ret;
		}
		for (Integer id: ids) {
			ret += privilegeMapper.deleteById(id);
		}
		return ret;
	}

	@Override
	public List<PrivilegePOJO> findAll() throws Exception {
		List<PrivilegePOJO> ret = null;
		ret = privilegeMapper.findAll();
		return ret;
	}

	@Override
	public List<PrivilegePOJO> findByRoleId(Integer roleId) throws Exception {
		List<PrivilegePOJO> ret = null;
		ret = privilegeMapper.findByRoleId(roleId);
		return ret;
	}

	@Override
	public List<String> findRoles(String url) throws Exception {
		List<String> ret = new ArrayList<String>();
		try {
			List<PrivilegePOJO> privilegePOJOs = privilegeMapper.finds(null);
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
							ret.add(rolePOJO.getRoleName());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("获取匹配权限的所有角色失败: {}", e);
		}
		
		/*if (CollectionUtils.isEmpty(ret)) {	// Because if ret == null, will allow any access.... TODO
			ret.add("NO_FOUND_ANY_ROLE");
			ret.add("ROLE_ANONYMOUS");
		}*/
		
		return ret;
	}

}
