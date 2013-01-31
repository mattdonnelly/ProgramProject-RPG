package com.infinitron.rpg;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
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