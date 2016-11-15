package edu.ae.entity;

/**
 * 
 * 问题类
 *
 */
public class Questions {
	
	private int id;		//问题的id
	private String content;		//问题内容
	private String relatedCharacter; //关联的性格
	
	
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
