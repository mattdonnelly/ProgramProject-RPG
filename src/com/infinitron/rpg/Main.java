package com.infinitron.rpg;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Window;

public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int starttime = (int)System.currentTimeMillis();
        setContentView(new Game(this));
        int endtime = (int) System.currentTimeMillis();
        Log.d("Time Taken","Time taken to build app: "+ (endtime - starttime) + "ms");
    }
    
    @Override
    protected void onPause()  { 
    	super.onPause(); 
    	finish();
    }
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}