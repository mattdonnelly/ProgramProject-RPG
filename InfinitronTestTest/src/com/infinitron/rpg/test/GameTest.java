package com.infinitron.rpg.test;

import com.infinitron.rpg.Game;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

public class GameTest extends AndroidTestCase{

	public GameTest() {  
		super();  
	}  
  
	public void setUp() throws Exception {
	    super.setUp();
	} 
	
	// assures test class is set up ok - should always test true
	@SmallTest
	public void testTest(){
		Game g = new Game(mContext);
		assertTrue(true);
	}

}