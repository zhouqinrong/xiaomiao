package com.example.view;

import com.example.bean.User;
import com.example.test.R;
import com.example.utils.ImageLoadUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemUserView extends LinearLayout{

	public ImageView iconView = null;
	public TextView titleText = null;
	private Context mContext = null;
	public ItemUserView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public ItemUserView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public ItemUserView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public ItemUserView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	public void init(){
		iconView = (ImageView)findViewById(R.id.user_image);
		titleText = (TextView)findViewById(R.id.user_title);
	}
	public void setData(User data){
		titleText.setText(data.name);
		ImageLoadUtils.getInstance(mContext).loadImage(data.url, iconView);
	}

}
