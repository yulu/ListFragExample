package com.example.listfragexample;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DetailFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.detail_fragment, container, false);
		
		return view;
	}
	
	public void setTitle(String title){
		TextView view = (TextView)getView().findViewById(R.id.detail_title);
		view.setText(title);
	}
}
