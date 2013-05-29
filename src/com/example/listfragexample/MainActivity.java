package com.example.listfragexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements ItemListFragment.OnItemSelectedListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@SuppressLint("NewApi")
	@Override
	public void onMyItemSelected(String link){
		DetailFragment fragment = (DetailFragment)getFragmentManager().findFragmentById(R.id.detailFragment);
	    	if(fragment != null && fragment.isInLayout()){
	    		fragment.setTitle(link);
	    }
	}

}
