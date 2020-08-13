package com.rahul.SpringRestfulJPA.rahulSpring.post;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PostResource {
	
	@Autowired
	public PostDao postService;
	
	@GetMapping("/posts")
	public List<Post> findAll(){
		return postService.findAll();
	}
	
	@GetMapping("/posts/{pid}")
	public Post findOne(@PathVariable int pid) {
		Post findOne = postService.findOne(pid);
		if(findOne==null) {
			throw new PostNotFoundException("Post with pid--> "+pid+" Not found");
		}
		return findOne;
	}
	
	@PostMapping("/posts")
	public ResponseEntity<Object> save(@RequestBody Post pos) {
		Integer pid = pos.getPostId();
		if(pid!=null) {
			if(postService.findOne(pid)!=null) {
				throw new PostAlreadyExistsException("Post with pid --> "+pid+" Already Exists");
			}
		}
		Post post = postService.save(pos);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{pid}").buildAndExpand(post.getPostId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/posts/{pid}")
	public ResponseEntity<Object> deleteId(@PathVariable int pid) {
		Post deleteId = postService.deleteId(pid);
		if(deleteId==null) {
			throw new PostNotFoundException("Post with pid -->"+pid+" not found can't delete");
		}
		return ResponseEntity.noContent().build();
	}
	
	//User Posts here
	
	@GetMapping("/users/{uid}/posts")
	public List<Post> userFindAll(@PathVariable int uid){
		return postService.userPostAll(uid);
	}
	
	@GetMapping("/users/{uid}/posts/{pid}")
	public Post usersFindById(@PathVariable int uid,@PathVariable int pid) {
		Post userPostFind = postService.userPostFind(uid, pid);
		if(userPostFind==null)
			throw new PostNotFoundException("User "+uid+" does not have post-->"+pid);
		return userPostFind;
	}
	
	@PostMapping("/users/{uid}/posts")
	public ResponseEntity<Object> usersCreatePost(@PathVariable int uid,@RequestBody Post pos) {
		Integer pid = pos.getPostId();
		if(pid!=null) {
			if(postService.userPostFind(uid, pid)!=null) {
				throw new PostAlreadyExistsException("Post with id-->"+pid+" in user -->"+uid+"already exists");
			}
		}
		Post userPostSave = postService.userPostSave(uid, pos);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{pid}").buildAndExpand(userPostSave.getPostId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{uid}/posts/{pid}")
	public ResponseEntity<Object> userDeletePostID(@PathVariable int uid, @PathVariable int pid) {
		Post userPostDelete = postService.userPostDelete(uid, pid);
		if(userPostDelete==null) {
			throw new PostNotFoundException("Post "+pid+" not found in user "+uid);
		}
		return ResponseEntity.noContent().build();
	}

}
