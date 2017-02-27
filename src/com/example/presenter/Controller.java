package com.example.presenter;

import java.util.ArrayList;

import com.example.bean.Repos;
import com.example.bean.User;
import com.example.model.VolleyFace;
import com.example.view.callback.LoginUICallBack;
import com.example.view.callback.SearchUserUICallBack;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class Controller {
	private static Controller instance = null;
	private static Handler handler = null;
	private Context mContext = null;
	private VolleyFace volleyFace = null;
	private ModelCallBackImpi modelCallBack = null;
	private LoginUICallBack loginUiCallBack = null;
	private SearchUserUICallBack searchUserCallBack = null;
	public static Controller getInstance(Context context){
		if(instance == null){
			
			instance = new Controller(context.getApplicationContext());
			
		}
		return instance;
	}
	private Controller(Context context){
		mContext = context;
		volleyFace = new VolleyFace(mContext);
		modelCallBack = new ModelCallBackImpi();
		modelCallBack.setController(this);
		volleyFace.setCallBack(modelCallBack);
		
	}
	/***********LogIn*******************/
	public void login(String username, String password,  LoginUICallBack logincallBack) {
		loginUiCallBack = logincallBack;				
		volleyFace.login(username, password);
		
	}
	public void loginFailed(int failedCode) {
		if(loginUiCallBack != null){
			loginUiCallBack.loginFailed(failedCode);
		}
		
	}
	public void loginSuccess() {
		if(loginUiCallBack != null){
			loginUiCallBack.loginSuccess();
		}
	}
	/***********SearchIn*******************/
	public void searchUser(String userName, SearchUserUICallBack mUserSearchCallBack) {
		searchUserCallBack = mUserSearchCallBack;
		volleyFace.searchUser(userName);
		
	}
	public void searchUserResult(ArrayList<User> userList) {
		if(searchUserCallBack != null){
			searchUserCallBack.onUserSearchResult(userList);
		}
	}
	public void searchRepos(String userName, SearchUserUICallBack mUserSearchCallBack) {
		searchUserCallBack = mUserSearchCallBack;
		volleyFace.searchRepos(userName);
	}
	public void searchReporResult(ArrayList<Repos> reposList) {
		if(searchUserCallBack != null){
			searchUserCallBack.onReposSearchResult(reposList);
		}
	}
	

}
