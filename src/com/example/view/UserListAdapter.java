package com.example.view;

import java.util.ArrayList;

import com.example.bean.User;
import com.example.test.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class UserListAdapter extends BaseAdapter{
	private Context mContext = null;
	private ArrayList<User> mDataList = null;
	private LayoutInflater layoutInflater = null;
	public UserListAdapter(Context context){
		mContext = context;
		layoutInflater = LayoutInflater.from(context);  
	}
	public void setData(ArrayList<User> dataList){
		mDataList = dataList;
	}

	@Override
	public int getCount() {
		if(mDataList == null){return 0;}
		return mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		if(mDataList == null){return null;}
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = (ItemUserView)layoutInflater.inflate(R.layout.user_view, null);
			((ItemUserView)convertView).init();
		}
		((ItemUserView)convertView).setData(mDataList.get(position));
		return convertView;
	}

}
