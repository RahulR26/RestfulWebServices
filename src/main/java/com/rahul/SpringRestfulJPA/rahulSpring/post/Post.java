package com.rahul.SpringRestfulJPA.rahulSpring.post;

public class Post {
	private Integer postId;
	private String postName;
	private String postContent;
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postName=" + postName + ", postContent=" + postContent + "]";
	}
	public Post(Integer postId, String postName, String postContent) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.postContent = postContent;
	}
	
	

}
