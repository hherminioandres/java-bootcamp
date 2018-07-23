package com.blog;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;

public class BlogTest {
	
	private static String NAMEB = "Blog bootcamp 2018";
	private static String NAMEP = "Post";
	private static int QUANTITY = 10;
	
	@Test
	public void testAddPostToBlog() {
		Blog blog = new Blog(NAMEB);
		blog.setPost(NAMEP, "...");
		assertFalse(blog.getAllPosts().isEmpty());
	}
	@Test
	public void testDeletePostToBlog() {
		Blog blog = new Blog(NAMEB);
		blog.setPost(NAMEP, "...");
		blog.deletePost(NAMEP);
		assertTrue(blog.getAllPosts().isEmpty());
	}
	@Test
	public void testGetLast10Posts() {
		Blog blog = new Blog(NAMEB);
		for(int i = 0; i < 15; i++){
			blog.setPost("Post "+i, "...");
		}
		assertEquals(10, blog.getNPosts(QUANTITY).size());
	}
	@Test
	public void testAddAndDeletePostToBlogMock() {
		PostDB postsMock = mock(PostDB.class);
		Blog blog = new Blog(NAMEB, postsMock);
		blog.setPost(NAMEP, "...");
		blog.deletePost(NAMEP);
		verify(postsMock).add(NAMEP, "...");
		verify(postsMock).delete(NAMEP);
	}
	
}