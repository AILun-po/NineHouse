package edu.ae.entity;

/**
 * �����¼��
 * 
 */
public class AnswerRecord {
	private int id;	//��Ŀ���
	private String answer;	//�����¼
	private int length;	//������Ŀ������
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
	 * �������¼ת����ָ�����ȵļ�¼
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
