package com.itheima.service.impl;


import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public void regist(User user) {

		UserDao dao = new UserDaoImpl();
		try {
			dao.add(user);
			String emailMsg = "您好，欢迎激活账号。<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User active(String code) throws SQLException {
		// TODO Auto-generated method stub
		UserDao dao = new UserDaoImpl();
		User user = dao.getByCode(code);
		if (user == null) {
			return null;
			
		}
		user.setState(1);
		dao.update(user);
		return user;
	}

	@Override
	public User login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		UserDao dao = new UserDaoImpl();
		return dao.getByUsernameAndPwd(username,password);
	}

}
