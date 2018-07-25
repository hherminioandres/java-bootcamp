package com.blog;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class PostDB {
	
	private List<Post> posts = new ArrayList<Post>();
	
	public PostDB() {
		
	}
	
	public Post add(String postTitle, String info) {
		posts.add(new Post(postTitle, info));
		return get(postTitle);
	}
	
	public boolean delete(String postTitle) {
		Post post = get(postTitle);
		if(post != null) {
			posts.remove(post);
			return true;
		}
		return false;
	}
	
	public Post get(String postTitle) {
		for(Post post : posts) {
			if(post.getTitle().equals(postTitle)) {
				return post;
			}
		}
		return null;		
	}
	
	public List<String> getPosts(){
		List<String> postsString =  new ArrayList<String>();
		for(Post post : posts) {
			postsString.add(post.toString());
		}
		Collections.reverse(postsString);
		return postsString;		
	}
	
	public int size() {
		return posts.size();
	}
}
