package com.example.model;

import org.json.JSONArray;
import org.json.JSONObject;

public interface  ModelCallBack {
	void loginFailed(int failedCode);
	void loginSuccess();
	void searchUserResult(JSONObject result);
	void searchReporResult(JSONArray result);
}
