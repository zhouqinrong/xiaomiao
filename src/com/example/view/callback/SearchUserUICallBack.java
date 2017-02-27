package com.example.view.callback;

import java.util.ArrayList;

import com.example.bean.Repos;
import com.example.bean.User;

public interface SearchUserUICallBack {
	void onUserSearchResult(ArrayList<User> resultList);
	void onReposSearchResult(ArrayList<Repos> resultList);

}
