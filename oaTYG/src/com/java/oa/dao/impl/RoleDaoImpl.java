package com.java.oa.dao.impl;

import org.springframework.stereotype.Repository;

import com.java.oa.dao.RoleDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
 
}
