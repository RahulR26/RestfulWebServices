package com.rahul.SpringRestfulJPA.rahulSpring.post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rahul.SpringRestfulJPA.rahulSpring.user.User;
import com.rahul.SpringRestfulJPA.rahulSpring.user.UserDao;
import com.rahul.SpringRestfulJPA.rahulSpring.user.UserNotFoundException;


@Component
public class PostDao {
	private static List<Post> posts= new ArrayList<Post>();
	
	
	@Autowired
	public UserDao userService;
	
	static {
		posts.add(new Post(11,"hello","testing  Posts"));
		posts.add(new Post(21,"hello","testing  Posts 2"));
		posts.add(new Post(31, "hello hello", "testing Posts 3"));
		posts.add(new Post(41, "hello 4", "testing Posts 4"));
		posts.add(new Post(51, "hello 5", "testing Posts 5"));
	}
	
	public static int findMaxi() {
		int max=-1;
		for(Post pos:posts) {
			if(max<pos.getPostId()) {
				max=pos.getPostId();
			}
		}
		return max;
	}
	
	private static int numberOfPosts=findMaxi();
	
	public List<Post> findAll(){
		return posts;
	}
	
	public Post findOne(int pid) {
		for(Post pos:posts) {
			if(pos.getPostId()==pid) {
				return pos;
			}
		}
		return null;
	}
	
	public Post save(Post po) {
		if(po.getPostId()==null) {
			po.setPostId(++numberOfPosts);
		}
		return po;
	}
	
	public Post deleteId(int pid) {
		Iterator<Post> iterator = posts.iterator();
		while(iterator.hasNext()) {
			Post po = iterator.next();
			if(po.getPostId()==pid) {
				iterator.remove();
				return po;
			}
		}
		return null;
	}
	
	//User specific ones now
	
	public List<Post> userPostAll(int uid){
		User use=userService.findOne(uid);
		if(use==null) {
			throw new UserNotFoundException("User with id--> "+uid+" Not found no posts ");
		}
		return use.getUserPosts();
	}
	
	public Post userPostFind(int uid,int pid) {
		User use = userService.findOne(uid);
		if(use==null) {
			throw new UserNotFoundException("User with id--> "+uid+" not found so post "+pid+" not searched");
		}
		List<Post> posts=use.getUserPosts();
		for(Post pos:posts) {
			if(pos.getPostId()==pid) {
				return pos;
			}
		}
		return null;
	}
	
	public Post userPostSave(int uid,Post pos) {
		User use = userService.findOne(uid);
		if(use==null)
			throw new UserNotFoundException("User with Id--> "+uid+" not found no post created");
		List<Post> posts = use.getUserPosts();
		posts.add(pos);
		use.setUserPosts(posts);
		return pos;
	}
	
	public Post userPostDelete(int uid,int pid) {
		User use = userService.findOne(uid);
		if(use==null)
			throw new UserNotFoundException("User with id--> "+uid+" not found so post "+pid+" not deleted");
		List<Post> posts= use.getUserPosts();
		Iterator<Post> iterator = posts.iterator();
		while (iterator.hasNext()) {
			Post pos=iterator.next();
			if(pos.getPostId()==pid) {
				iterator.remove();
				return pos;
			}
		}
		return null;
	}
	
	

}
