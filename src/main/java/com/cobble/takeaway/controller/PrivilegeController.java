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
import com.cobble.takeaway.pojo.StatusPOJO;
import com.cobble.takeaway.pojo.PrivilegePOJO;
import com.cobble.takeaway.pojo.PrivilegeSearchPOJO;
import com.cobble.takeaway.service.PrivilegeService;

@Controller
public class PrivilegeController extends BaseController {
	private final static Logger LOGGER = LoggerFactory.getLogger(PrivilegeController.class);
	
	@Autowired
	private PrivilegeService privilegeService;

	@RequestMapping(value = "/mgr/privilege/{roleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<PrivilegePOJO> findAllPrivilege(@PathVariable("roleId") Integer roleId, Model model) throws Exception {
		ExtjsPOJO<PrivilegePOJO> ret = new ExtjsPOJO<PrivilegePOJO>();
		List<PrivilegePOJO> privilegePOJOList = new ArrayList<PrivilegePOJO>();
		privilegePOJOList = privilegeService.findByRoleId(roleId);
		List<PrivilegePOJO> allPrivileges = privilegeService.findAll();
		
		if (!CollectionUtils.isEmpty(allPrivileges) && !CollectionUtils.isEmpty(privilegePOJOList)) {
			for (PrivilegePOJO privilegePOJO : privilegePOJOList) {
				for (PrivilegePOJO temp : allPrivileges) {
					if (temp.getPrivilegeId().intValue() == privilegePOJO.getPrivilegeId()) {
						temp.setChecked(true);
					}
				}
			}
		}
		
		int total = CollectionUtils.isEmpty(allPrivileges) ? 0 : allPrivileges.size();
		
		ret.setGridModelList(allPrivileges);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/privilege/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<PrivilegePOJO> findAllPrivilege(Model model) throws Exception {
		ExtjsPOJO<PrivilegePOJO> ret = new ExtjsPOJO<PrivilegePOJO>();
		List<PrivilegePOJO> allPrivileges = new ArrayList<PrivilegePOJO>();
		allPrivileges = privilegeService.findAll();
		int total = CollectionUtils.isEmpty(allPrivileges) ? 0 : allPrivileges.size();
		
		ret.setGridModelList(allPrivileges);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/privilege", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ExtjsPOJO<PrivilegePOJO> findPrivilege(PrivilegeSearchPOJO privilegeSearchPOJO, Model model) throws Exception {
		ExtjsPOJO<PrivilegePOJO> ret = new ExtjsPOJO<PrivilegePOJO>();
		List<PrivilegePOJO> privilegePOJOList = new ArrayList<PrivilegePOJO>();
		privilegePOJOList = privilegeService.finds(privilegeSearchPOJO);
		int total = privilegeService.getCount(privilegeSearchPOJO);
		
		ret.setGridModelList(privilegePOJOList);
		ret.setSuccess(true);
		ret.setTotal(total);
		return ret;
	}
	
	@RequestMapping(value = "/mgr/privilege/add", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO add(PrivilegePOJO privilegePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = privilegeService.insert(privilegePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/privilege/update", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO update(PrivilegePOJO privilegePOJO, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = privilegeService.update(privilegePOJO);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}
	
	@RequestMapping(value = "/mgr/privilege/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public StatusPOJO delete(Integer[] ids, Model model) throws Exception {
		StatusPOJO ret = new StatusPOJO();
		try {
			int result = privilegeService.delete(ids);
			ret.setSuccess(true);
		} catch (Exception e) {
			LOGGER.error("insert error.", e);
			ret.setSuccess(false);
			throw e;
		}
		
		return ret;
	}

}
