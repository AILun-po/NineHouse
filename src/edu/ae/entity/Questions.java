package edu.ae.entity;

/**
 * 
 * ������
 *
 */
public class Questions {
	
	private int id;		//�����id
	private String content;		//��������
	private String relatedCharacter; //�������Ը�
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setRelatedCharacter(String relatedCharacter) {
		this.relatedCharacter = relatedCharacter;
	}
	public String getRelatedCharacter() {
		return relatedCharacter;
	}
	
	
}
