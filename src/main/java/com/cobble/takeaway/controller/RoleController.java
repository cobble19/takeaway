package com.cobble.takeaway.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cobble.takeaway.pojo.ExtjsPOJO;
import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.RolePOJO;
import com.cobble.takeaway.pojo.RoleSearchPOJO;
import com.cobble.takeaway.service.RoleService;

@Controller
public class RoleController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/mgr/role/user/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<RolePOJO> findRoleByUserId(@PathVariable("userId") Integer userId, Model model) throws Exception {
		ExtjsPOJO<RolePOJO> ret = new ExtjsPOJO<RolePOJO>();
		List<RolePOJO> rolePOJOs = new ArrayList<RolePOJO>();
		rolePOJOs = roleService.findByUserId(userId);
		List<RolePOJO> allRoles = roleService.findAll();
		
		if (!CollectionUtils.isEmpty(allRoles) && !CollectionUtils.isEmpty(rolePOJOs)) {
			for (RolePOJO rolePOJO : rolePOJOs) {
				for (RolePOJO temp : allRoles) {
					if (temp.getRoleId().intValue() == rolePOJO.getRoleId()) {
						temp.setChecked(true);
					}
				}
			}
		}
		
		int total = CollectionUtils.isEmpty(allRoles) ? 0 : allRoles.size();
		
		ret.setGridModelList(allRoles);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/role/{privilegeId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<RolePOJO> findRoleByPrivilegeId(@PathVariable("privilegeId") Integer privilegeId, Model model) throws Exception {
		ExtjsPOJO<RolePOJO> ret = new ExtjsPOJO<RolePOJO>();
		List<RolePOJO> rolePOJOs = new ArrayList<RolePOJO>();
		rolePOJOs = roleService.findByPrivilegeId(privilegeId);
		List<RolePOJO> allRoles = roleService.findAll();
		
		if (!CollectionUtils.isEmpty(allRoles) && !CollectionUtils.isEmpty(rolePOJOs)) {
			for (RolePOJO rolePOJO : rolePOJOs) {
				for (RolePOJO temp : allRoles) {
					if (temp.getRoleId().intValue() == rolePOJO.getRoleId()) {
						temp.setChecked(true);
					}
				}
			}
		}
		
		int total = CollectionUtils.isEmpty(allRoles) ? 0 : allRoles.size();
		
		ret.setGridModelList(allRoles);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/role/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<RolePOJO> findAllRole(Model model) throws Exception {
		ExtjsPOJO<RolePOJO> ret = new ExtjsPOJO<RolePOJO>();
		List<RolePOJO> allRoles = new ArrayList<RolePOJO>();
		allRoles = roleService.findAll();
		int total = CollectionUtils.isEmpty(allRoles) ? 0 : allRoles.size();
		
		ret.setGridModelList(allRoles);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/role", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<RolePOJO> findRole(RoleSearchPOJO roleSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<RolePOJO> ret = new ExtjsPOJO<RolePOJO>();
		List<RolePOJO> rolePOJOList = new ArrayList<RolePOJO>();
		rolePOJOList = roleService.finds(roleSearchPOJO);
		int total = roleService.getCount(roleSearchPOJO);
		
		ret.setGridModelList(rolePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/role/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(RolePOJO rolePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = roleService.insert(rolePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/role/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(RolePOJO rolePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = roleService.update(rolePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/role/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = roleService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
