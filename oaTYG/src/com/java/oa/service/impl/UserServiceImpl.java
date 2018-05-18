package com.java.oa.service.impl;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.oa.dao.UserDao;
import com.java.oa.dao.base.BaseDao;
import com.java.oa.domain.User;
import com.java.oa.service.UserService;
import com.java.oa.service.base.impl.BaseServiceImpl;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	@Resource(name="userDao")
	private UserDao userDao;
	@Override
	public BaseDao getBaseDao() {
		return this.userDao;
	} 

	@Override
	@Transactional
	public void save(User user) {
		// 默认密码是1234
		String md5 = DigestUtils.md5Hex("1234"); // 密码要使用MD5摘要
		//用到的包 commons-codec-1.3.jar
		user.setPassword(md5);

		// 保存到数据库
		this.userDao.save(user);
	}

	@Override
	@Transactional
	public User findByLoginNameAndPassword(String loginName, String password) {  
		return this.userDao.findByLoginNameAndPassword( loginName,password);
	} 
}
