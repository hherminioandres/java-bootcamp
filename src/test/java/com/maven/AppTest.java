package com.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
	
    @Test
    public void compareMessages(){
    	App app = new App();
        assertEquals("Must be Hello World", app.getMessage(), "Hello World");         
    }
}
