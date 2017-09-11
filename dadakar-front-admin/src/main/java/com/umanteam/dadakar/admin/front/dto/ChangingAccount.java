package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;

public class ChangingAccount implements Serializable {
	
	/* Variables */

	private static final long serialVersionUID = -1475125689925354783L;
	private String accountId;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String url;
	
	/* Constructors */
	
	public ChangingAccount() {}

	public ChangingAccount(String accountId, String oldPassword, String newPassword, String confirmPassword, String url) {
		this.accountId = accountId;
		this.oldPassword = DigestUtils.sha1Hex(oldPassword);
		this.newPassword = DigestUtils.sha1Hex(newPassword);
		this.confirmPassword = DigestUtils.sha1Hex(confirmPassword);
		this.url = url;
	}

	/* Getters and Setters */
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = DigestUtils.sha1Hex(oldPassword);
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = DigestUtils.sha1Hex(newPassword);
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = DigestUtils.sha1Hex(confirmPassword);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/* Methods */

	@Override
	public String toString() {
		return "ChangingAccount [accountId=" + accountId + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + ", confirmPassword=" + confirmPassword + ", url=" + url + "]";
	}

}
