package com.java.oa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.oa.dao.RoleDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Role;
import com.java.oa.service.RoleService;
import com.java.oa.service.base.impl.BaseServiceImpl;

@Service("roleService")

public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
 
	@Resource(name="roleDao")
	private RoleDao roleDao;
	
	@Override
	public BaseDao getBaseDao() {
		return this.roleDao;
	}
}
