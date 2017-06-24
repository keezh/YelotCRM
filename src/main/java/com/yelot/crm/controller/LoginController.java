package com.yelot.crm.controller;

import com.yelot.crm.Util.ResponseData;
import com.yelot.crm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xyzloveabc
 * @2017年6月10日
 */
@RestController
@RequestMapping("/Login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public ResponseData login(String username, String password) {
		return loginService.login(username, password);
	}

}
