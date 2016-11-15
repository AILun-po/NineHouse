package edu.ae.entity;

/**
 * 
 * @author 维坡
 * 管理员类
 * 
 */
public class Managers {
	
	private String username; //用户名
	private String password; //密码
	private String name;
	private String workId;
	private String school;
	private String college;
	private String major;
	private String gread;
	private String classes;
	private int age;
	private String gender;
	private String nativeplace;

	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchool() {
		return school;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getCollege() {
		return college;
	}
	public void setGread(String gread) {
		this.gread = gread;
	}
	public String getGread() {
		return gread;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getClasses() {
		return classes;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMajor() {
		return major;
	}
}
