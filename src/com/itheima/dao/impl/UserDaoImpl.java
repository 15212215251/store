package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.DataSourceUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserDaoImpl implements UserDao {

	@Override
	public void add(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?);";
		System.out.println("userName is :"+user.getName());
		qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		
		
	}

	@Override
	public User getByCode(String code) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from user where code =? limit 1; ";
		return queryRunner.query(sql, new BeanHandler<>(User.class),code);
	}

	@Override
	public void update(User user) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set username = ?,password=?,name =?, email=?,birthday= ?,state = ?,code = ? where uid = ?;";
		queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getBirthday(), user.getState(), null, user.getUid());

	}

	@Override
	public User getByUsernameAndPwd(String username, String password) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1;";
		return queryRunner.query(sql, new BeanHandler<>(User.class), username,password);
	}

}
