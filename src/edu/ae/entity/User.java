package edu.ae.entity;

/**
 * ����������
 *
 */
public class User {
	
	private int id;				//���ݿ��е����
	private String stuId;		//ѧ�ţ�����Ϊ��
	private String name;   		//����������Ϊ��
	private String gender;    	//0--�У�1--Ů
	private int    age;			//����
	private String nativeplace; //�û�����
	private String sysResult;	//ϵͳ�Ը�������
	private String relResult;	//ʵ���Ը����������ɹ���Ա��д���޸�
	private String start_datetime; //���⿪ʼʱ��
	private String end_datetime; //�������ʱ��
	
	
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
