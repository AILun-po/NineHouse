package edu.ae.entity;

/**
 * 答题记录类
 * 
 */
public class AnswerRecord {
	private int id;	//题目序号
	private String answer;	//答题记录
	private int length;	//所答题目的数量
	private String qusetion_sort;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswer() {
		return answer;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	public int getLength() {
		return length;
	}
	
	/**
	 * 将答题记录转换成指定长度的记录
	 * 
	 */
	public void transToStandard(){
		
	}
	
	public char getCharAt(int i){
		return answer.charAt(i);
	}
	public void setQusetion_sort(String qusetion_sort) {
		this.qusetion_sort = qusetion_sort;
	}
	public String getQusetion_sort() {
		return qusetion_sort;
	}
}
