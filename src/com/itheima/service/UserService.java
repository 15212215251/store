package com.itheima.service;

import java.sql.SQLException;

import com.itheima.domain.User;

public interface UserService {

	void regist(User user);

	User active(String code) throws SQLException;

	User login(String username, String password) throws SQLException;

}
