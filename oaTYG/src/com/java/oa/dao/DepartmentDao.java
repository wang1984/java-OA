package com.java.oa.dao;

import java.util.List;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.Department;

public interface DepartmentDao extends BaseDao<Department> {

	List<Department> findChildren(Long parentId);

	List<Department> findTopList();

}
