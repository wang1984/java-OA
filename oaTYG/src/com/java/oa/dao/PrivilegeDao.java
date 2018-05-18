package com.java.oa.dao;

import java.util.List;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege> {
	List<Privilege> findTopList();
	List<String> getAllPrivilegeUrls();
}
