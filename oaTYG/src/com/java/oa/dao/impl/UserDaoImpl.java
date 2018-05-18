package com.java.oa.dao.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;

import com.java.oa.dao.UserDao;
import com.java.oa.dao.base.impl.BaseDaoImpl;
import com.java.oa.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5 = DigestUtils.md5Hex(password);//对密码进行md5加密
		return (User) getSession().createQuery(//
				"FROM User u WHERE u.loginName=? AND u.password=?")//
				.setParameter(0, loginName)//
				.setParameter(1, md5)// 密码要使用MD5摘要
				.uniqueResult();//只会有一个结果，如果返回了多个结果那么这个方法就会报错
	}

}
 