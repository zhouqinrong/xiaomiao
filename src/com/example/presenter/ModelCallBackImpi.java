package com.example.presenter;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Repos;
import com.example.bean.User;
import com.example.model.ModelCallBack;

public class ModelCallBackImpi implements ModelCallBack{
	private Controller mController = null;
	private Handler mHandler = null;
	public void setController(Controller controller){
		mController = controller;
		mHandler = new Handler(Looper.getMainLooper());
	}

	@Override
	public void loginFailed(final int failedCode) {
		if(mController == null){return;}
		mHandler.post(new Runnable(){

			@Override
			public void run() {
				mController.loginFailed(failedCode);
				
			}});
	}

	@Override
	public void loginSuccess() {
		if(mController == null){return;}
		mHandler.post(new Runnable(){

			@Override
			public void run() {
				mController.loginSuccess();
				
			}});
	}

	@Override
	public void searchUserResult(final JSONObject result) {
		if(mController == null || result == null){return;}
		final ArrayList<User> userList = new ArrayList<User>();
		try {
			JSONArray jsonObjs = result.getJSONArray("items");
			int size = jsonObjs.length();
			for(int i = 0; i < size; i++){
				User user = new User();
				JSONObject obj = jsonObjs.getJSONObject(i);
				user.name = obj.getString("login");
				user.url = obj.getString("avatar_url");
				userList.add(user);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
		mHandler.post(new Runnable(){

			@Override
			public void run() {
				mController.searchUserResult(userList);
				
			}});
	}

	@Override
	public void searchReporResult(final JSONArray jsonArray) {
		if(mController == null || jsonArray == null){return;}
		final ArrayList<Repos> reposList = new ArrayList<Repos>();
		try {
			
			int size = jsonArray.length();
			for(int i = 0; i < size; i++){
				Repos repos = new Repos();
				JSONObject obj = jsonArray.getJSONObject(i);
				JSONObject subObj = obj.getJSONObject("owner");
				repos.name = obj.getString("full_name");
				repos.icon = subObj.getString("avatar_url");
				repos.language = obj.getString("language");
				reposList.add(repos);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		mHandler.post(new Runnable(){

			@Override
			public void run() {
				mController.searchReporResult(reposList);
				
			}});
	}

}
