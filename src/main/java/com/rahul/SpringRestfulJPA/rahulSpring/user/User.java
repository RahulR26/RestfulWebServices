package com.rahul.SpringRestfulJPA.rahulSpring.user;

import java.util.Date;
import java.util.List;

import com.rahul.SpringRestfulJPA.rahulSpring.post.Post;

public class User {
	private Integer id;
	private String name;
	private Date dob;
	private Double score;
	public List<Post> userPosts;
	public User(Integer id, String name, Date dob, Double score, List<Post> userPosts) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.score = score;
		this.userPosts = userPosts;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", score=" + score + ", userPosts=" + userPosts
				+ "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public List<Post> getUserPosts() {
		return userPosts;
	}
	public void setUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}
	

}
