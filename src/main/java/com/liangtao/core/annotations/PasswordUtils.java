package com.liangtao.core.annotations;

import java.util.List;
/**
 * 密码工具
 */
public class PasswordUtils {
	/**
	 * 判断密码的合法性(密码必须包含至少一个数字)
	 */
	@UseCase(id=47,description="Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}
	
	/**
	 * 密码加密(反转)
	 */
	@UseCase(id=48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}
	
	/**
	 * 修改密码约束(新密码不能等于以前使用的密码)
	 */
	@UseCase(id=49,description="New passwords can't equal previously used ones")
	public boolean checkForNewPassword(List<String> prevPasswords, String password) {
		return !prevPasswords.contains(password);
	}
	
}
