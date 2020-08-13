package com.rahul.SpringRestfulJPA.rahulSpring.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rahul.SpringRestfulJPA.rahulSpring.post.Post;

@Component 
public class UserDao {
	private static List<User> users = new ArrayList<User>();
	
	private static List<Post> post1= new ArrayList<Post>();
	private static List<Post> post2= new ArrayList<Post>();
	private static List<Post> post3= new ArrayList<Post>();

	
	
	static {
		
		post1.add(new Post(11,"hello","testing  Posts"));
		post1.add(new Post(21,"hello","testing  Posts 2"));
		post1.add(new Post(31, "hello hello", "testing Posts 3"));
		post2.add(new Post(41, "hello 4", "testing Posts 4"));
		post3.add(new Post(51, "hello 5", "testing Posts 5"));
		
		users.add(new User(1, "Rahul", new Date(), 96.69, post1));
		users.add(new User(2, "Kiran", new Date(), 97.98, post2));
		users.add(new User(3, "Ravikiran", new Date(), 86.66, post3));
		
	}
	
	private static int userCount = findMaxId();
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int uid) {
		for(User use:users) {
			if(use.getId()==uid) {
				return use;
			}
		}
		return null;
	}
	
	public User save(User use) {
		if(use.getId()==null) {
			use.setId(++userCount);			
		}
		users.add(use);
		return use;
	}
	
	public User delete(int uid) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User use=iterator.next();
			if(use.getId()==uid) {
				iterator.remove();
				return use;
			}
		}
		return null;
	}
	
	public static int findMaxId() {
		int maxi=-1;
		for(User use:users) {
			if(maxi<use.getId()) {
				maxi=use.getId();
			}
		}
		return maxi;
	}
}
