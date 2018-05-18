package com.java.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.oa.dao.PrivilegeDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Privilege;
import com.java.oa.service.PrivilegeService;
import com.java.oa.service.base.impl.BaseServiceImpl;
@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege> implements PrivilegeService{
	
	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;
	
	@Override
	public BaseDao getBaseDao() {
		return this.privilegeDao;
	}

	@Override
	public List<Privilege> findTopList() {
		return  privilegeDao.findTopList();
	}

	@Override
	public List<String> getAllPrivilegeUrls() {
		 
		return privilegeDao.getAllPrivilegeUrls();
	} 

	 
}
