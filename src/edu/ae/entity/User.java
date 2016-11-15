package edu.ae.entity;

/**
 * 被测试者类
 *
 */
public class User {
	
	private int id;				//数据库中的序号
	private String stuId;		//学号，可以为空
	private String name;   		//姓名，可以为空
	private String gender;    	//0--男，1--女
	private int    age;			//年龄
	private String nativeplace; //用户籍贯
	private String sysResult;	//系统性格分析结果
	private String relResult;	//实际性格分析结果，由管理员填写、修改
	private String start_datetime; //答题开始时间
	private String end_datetime; //答题结束时间
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setSysResult(String sysResult) {
		this.sysResult = sysResult;
	}
	public String getSysResult() {
		return sysResult;
	}
	public void setRelResult(String relResult) {
		this.relResult = relResult;
	}
	public String getRelResult() {
		return relResult;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setStart_datetime(String start_datetime) {
		this.start_datetime = start_datetime;
	}
	public String getStart_datetime() {
		return start_datetime;
	}
	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}
	public String getEnd_datetime() {
		return end_datetime;
	}

	
}
