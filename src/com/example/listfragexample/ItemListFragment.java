package com.example.listfragexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ItemListFragment extends Fragment{
	
	private OnItemSelectedListener listener;
	
	private String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
			"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux",
			"Android", "OS/2"
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.list_fragment, container, false);
		
		ListView listview = (ListView)view.findViewById(R.id.list_view);
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < values.length; ++i){
			list.add(values[i]);
		}
		
		ItemArrayAdapter adapter = new ItemArrayAdapter(getActivity(), 
				android.R.layout.simple_expandable_list_item_1, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id){
				final String title = (String) parent.getItemAtPosition(position);
				updateDetail(title);
			}
		});
		
		
		return view;
	}
	
	public interface OnItemSelectedListener{
		public void onMyItemSelected(String title);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		if(activity instanceof OnItemSelectedListener){
			listener = (OnItemSelectedListener)activity;
		}else{
			throw new ClassCastException(activity.toString()
					+ " must implement MyListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public void onDetach(){
		super.onDetach();
		listener = null;
	}
	
	public void updateDetail(String title){
		listener.onMyItemSelected(title);
	}
	
	private class ItemArrayAdapter extends ArrayAdapter<String>{

		private final Context context;
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public ItemArrayAdapter(Context context, int textViewResourceId,
				List<String> objects){
			super(context, textViewResourceId, objects);
			this.context = context;
			for(int i = 0; i < objects.size(); ++i){
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			/**
			 * inflate the layout and get element references
			 */
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.row_view, parent, false);
			TextView textViewTitle = (TextView)rowView.findViewById(R.id.row_title);
			TextView textViewDate = (TextView)rowView.findViewById(R.id.row_date);
			ImageView imageView = (ImageView)rowView.findViewById(R.id.row_logo);

			/**
			 * set the view elements from the data
			 */
			textViewTitle.setText(getItem(position));
			textViewDate.setText(R.string.date);
			imageView.setImageResource(R.drawable.ic_launcher);

			return rowView;

		}

		@Override
		public long getItemId(int position){
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds(){
			return true;
		}
	}
	
	
}
