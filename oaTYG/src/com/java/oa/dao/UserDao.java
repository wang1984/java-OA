package com.java.oa.dao;

import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.User;

public interface UserDao extends BaseDao<User> {
	User findByLoginNameAndPassword(String loginName, String password);
}
