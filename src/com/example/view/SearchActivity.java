package com.example.view;


import java.util.ArrayList;

import com.example.bean.Repos;
import com.example.bean.User;
import com.example.presenter.Controller;
import com.example.test.R;
import com.example.view.callback.SearchUserUICallBack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchActivity extends Activity{
	private EditText userText , reposText;
	private ListView listView = null;
	private Controller mController;
	private ArrayList<User> mUserList = null;
	private UserListAdapter mUserListAdapter = null;
	private ArrayList<Repos> mReposList = null;
	private ReposListAdapter mReposListAdapter = null;
	private SearchUserUICallBack mUserSearchCallBack = new SearchUserUICallBack(){
		@Override
		public void onUserSearchResult(ArrayList<User> userList) {
			mUserList = userList;
			if(mUserList == null || mUserList.size() <= 0){return;}
			if(mUserListAdapter == null){
				mUserListAdapter = new UserListAdapter(SearchActivity.this);				
			}
			listView.setAdapter(mUserListAdapter);
			mUserListAdapter.setData(mUserList);
			mUserListAdapter.notifyDataSetChanged();
		}

		@Override
		public void onReposSearchResult(ArrayList<Repos> reposList) {
			mReposList = reposList;
			if(mReposList == null || mReposList.size() <= 0){return;}
			if(mReposListAdapter == null){
				mReposListAdapter = new ReposListAdapter(SearchActivity.this);				
			}
			listView.setAdapter(mReposListAdapter);
			mReposListAdapter.setData(mReposList);
			mReposListAdapter.notifyDataSetChanged();
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		mController = Controller.getInstance(this);
		userText = (EditText)this.findViewById(R.id.user_text);
		reposText = (EditText)this.findViewById(R.id.repos_text);
		listView = (ListView)this.findViewById(R.id.list_view);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	public void searchUser(View view){
		String userName = userText.getText().toString();
		if(userName == null || "".equals(userName)){
			Toast.makeText(SearchActivity.this, "Please input nusername", Toast.LENGTH_SHORT).show();
		}else{
			mController.searchUser(userName, mUserSearchCallBack);
		}
		
		
	}
	public void searchRepos(View view){
		String user = reposText.getText().toString();
		if(user == null || "".equals(user)){
			Toast.makeText(SearchActivity.this, "Please input nusername", Toast.LENGTH_SHORT).show();
		}else{
			mController.searchRepos(user, mUserSearchCallBack);
		}
	}
	
	

}
