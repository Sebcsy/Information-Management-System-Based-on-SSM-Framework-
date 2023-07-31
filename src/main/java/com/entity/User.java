package com.entity;

import java.util.Date;

public class User{
    private Integer userId;  //����id 
    private String userName; //�˺�  
    private String userPass;  //����
    private String userNickname;  //�ǳ�
    private String userEmail;  //����
    private String userUrl;  //�û�����ַ
    private String userAvatar;  //ָ��һ��ͼƬ��ַ(����ֶ�Ŀǰֻ�Ǳ����ֶ�,��ʱ����)
    private String userLastLoginIp;   //����¼��IP
    private Date userRegisterTime;   //�û�ע���ʱ��
    private Date userLastLoginTime; //����¼��ʱ��
    private Integer userStatus; //�û���״̬
    private byte [] userPhoto;  //�û���Ƭ,����Ǿ������Ƭ����
    //��ǰ©������ֶ�,Ҫ����
    private Integer articleCount;  //�û���������������
    
    
	public Integer getUserId() {
		return userId;
	}
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getUserLastLoginIp() {
		return userLastLoginIp;
	}
	public void setUserLastLoginIp(String userLastLoginIp) {
		this.userLastLoginIp = userLastLoginIp;
	}
	public Date getUserRegisterTime() {
		return userRegisterTime;
	}
	public void setUserRegisterTime(Date userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}
	public Date getUserLastLoginTime() {
		return userLastLoginTime;
	}
	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public byte[] getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}
    
}