package com.example.model;

import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class VolleyFace {
	private ModelCallBack mCallback = null;
	private final String searchUrl = "https://api.github.com/search/users?q=";
	private final String searchRepo = "https://api.github.com/users/";
	private RequestQueue mQueue = null;
	public VolleyFace(Context context){
		mQueue = Volley.newRequestQueue(context);  
	}

	public void setCallBack(ModelCallBack modelCallBack) {
		mCallback = modelCallBack;
		
	}
	
	public void login(String username, String password) {
		
		if(mCallback != null){
			mCallback.loginSuccess();
		}
	}

	

	public void searchUser(String userName) {
		String url = searchUrl + userName;
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						if (mCallback != null) {
							mCallback.searchUserResult(response);
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (mCallback != null) {
							mCallback.searchUserResult(null);
						}
					}
				});
		jsonObjectRequest.setShouldCache(true);
		mQueue.add(jsonObjectRequest);

	}

	public void searchRepos(String userName) {
		String url = searchRepo + userName + "/repos";
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
			@Override
			public void onResponse(JSONArray jsonArray) {
				if (mCallback != null) {
					mCallback.searchReporResult(jsonArray);
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (mCallback != null) {
					Log.e("AAA", "error = " + error);
					mCallback.searchReporResult(null);
				}
			}
		});
		jsonArrayRequest.setShouldCache(true);		
		mQueue.add(jsonArrayRequest);
		
	}

	

}
