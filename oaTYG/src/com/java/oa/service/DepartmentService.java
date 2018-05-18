package com.java.oa.service;

import java.util.List;

import com.java.oa.domain.Department;
import com.java.oa.service.base.BaseService;

public interface DepartmentService extends BaseService<Department>{
 
	/**
	 * 查询所有顶级部门的列表
	 * 
	 * @return
	 */
	List<Department> findTopList();

	/**
	 * 查询所有子部门的列表
	 * 
	 * @param parentId
	 * @return
	 */
	List<Department> findChildren(Long parentId);

}
