package com.blog;


import java.util.List;

public class Blog{
	
	private String name;
	private PostDB posts;
	
	public Blog(String blogName) {
		this.name = blogName;
		posts = new PostDB();
	}
	public Blog(String blogName, PostDB posts) {
		name = blogName;
		this.posts = posts; 
	}
	public List<String> getNPosts(int quantity) {
		List<String> postsAux = posts.getPosts();
		if(postsAux.size()>quantity) {
			postsAux = posts.getPosts().subList(0, 10);
		}
		return postsAux;
	}
	public List<String> getAllPosts() {
		return posts.getPosts();
	}
	public Post setPost(String postTitle, String info) {
		return posts.add(postTitle, info);
	}
	public String getName() {
		return name;
	}
	public void setName(String blogName) {
		name = blogName;
	}
	public boolean deletePost(String postTitle) {
		return posts.delete(postTitle);
	}
}
