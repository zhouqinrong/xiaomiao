package com.example.view;

import com.example.utils.ImageLoadUtils;
import com.example.bean.Repos;
import com.example.test.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemReposView extends LinearLayout{
	public ImageView iconView = null;
	public TextView titleText = null;
	public TextView languageText = null;
	private Context mContext = null;
	public ItemReposView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public ItemReposView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public ItemReposView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public ItemReposView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	public void init(){
		iconView = (ImageView)findViewById(R.id.repos_image);
		titleText = (TextView)findViewById(R.id.repos_title);
		languageText = (TextView)findViewById(R.id.repos_language);
	}
	public void setData(Repos data){
		titleText.setText(data.name);
		languageText.setText(data.language);
		ImageLoadUtils.getInstance(mContext).loadImage(data.icon, iconView);
	}
}
