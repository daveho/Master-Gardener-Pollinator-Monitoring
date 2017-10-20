package model;

public class Post {
		
	private int postId;
	private int accountId;
	private int groupId;
	private String text;
	
	// date time at later date
	// image or picture
	
	public Post(String text, int accountID, int groupID){
		this.text = text;
		this.accountId = accountID;
		this.groupId = groupID;
	}
	public Post() {

	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public void setAccountId(int accountId){
		this.accountId = accountId;
	}
	
	public void setGroupId(int groupId){
		this.groupId = groupId;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public int getPostId() {
		return postId;
	}
	
	public int getAccountId(){
		return this.accountId;
	}
	
	public int getGroupId(){
		return this.groupId;
	}
	
	//getters
	public String getText(){
		return this.text;
	}
	
}
