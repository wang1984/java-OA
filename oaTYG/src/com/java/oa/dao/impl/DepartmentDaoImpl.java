package com.java.oa.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.oa.dao.DepartmentDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

	@Override
	public List<Department> findChildren(Long parentId) {
		 
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent.id=?")//
				.setParameter(0, parentId)//
				.list();
	}

	@Override
	public List<Department> findTopList() {
		 
		return getSession().createQuery(//
				"FROM Department d WHERE d.parent IS NULL")//
				.list();
	}

}
