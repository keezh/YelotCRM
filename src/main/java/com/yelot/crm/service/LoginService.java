package com.yelot.crm.service;

import com.yelot.crm.Util.MD5Utils;
import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.entity.User;
import com.yelot.crm.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;

	public ResponseData login(String username, String password) {
		password = MD5Utils.getMD5Code(password);

		User user = loginMapper.selectUser(username, password);
		if (null != user) {
			System.err.println("11");
			String sign = "";// modify by kee
			return new ResponseData(ResponseData.SUCCESS,
					ResponseData.SUCCESS_MESSAGE).putDataValue("token", sign)
					.putDataValue("user", user);
		} else {
			System.out.println("22");
			return new ResponseData(ResponseData.FAIL,
					ResponseData.FAIL_MESSAGE);
		}
	}

}
