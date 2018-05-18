package com.java.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.oa.dao.PrivilegeDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.Privilege;

@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao {

	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

	@Override
	public List<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
 