package com.conorharney.simpleSpringApp;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest 
{
	@BeforeClass
    public static void BeforeClass() {
    }

    @Before
    public void before() {
        System.out.println("Before");
    }
    
    @Test
    public void testAddingServer() {
    	System.out.println("Test");
    	
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @AfterClass
    public static void AfterTest() {
        System.out.println("AfterClass");
    }
}
