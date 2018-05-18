package com.java.oa.service;

import com.java.oa.domain.User;
import com.java.oa.service.base.BaseService;

public interface UserService extends BaseService<User>{

	User findByLoginNameAndPassword(String loginName, String password);

}
