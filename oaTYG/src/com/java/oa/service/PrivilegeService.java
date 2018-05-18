package com.java.oa.service;

import java.util.List;

import com.java.oa.domain.Privilege;
import com.java.oa.service.base.BaseService;

public interface PrivilegeService extends BaseService<Privilege>{
	 List<Privilege> findTopList();

	List<String> getAllPrivilegeUrls();
}
