package com.example.view;

import com.example.presenter.Controller;
import com.example.test.R;
import com.example.view.callback.LoginUICallBack;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private EditText userNameText = null;
	private EditText passWordText = null;
	private Button submitButton = null;
	private Controller mController = null;
	private LoginImpl loginimpl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameText = (EditText)this.findViewById(R.id.username);
        passWordText = (EditText)this.findViewById(R.id.password);
        submitButton = (Button)this.findViewById(R.id.submit);
        submitButton.setOnClickListener(this);
        mController = Controller.getInstance(getApplicationContext());
        loginimpl = new LoginImpl(); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.submit){
			String username = userNameText.getText().toString();
			String password = passWordText.getText().toString();
			if(username == null || "".equals(username) || password == null || "".equals(password)){
				loginimpl.loginFailed(0);	
			}else{				
				mController.login(username, password, loginimpl);
				
			}
		}
	}
	
	


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		loginimpl = null;
	}


	class LoginImpl implements LoginUICallBack{
		@Override
		public void loginFailed(int failedCode) {
			Toast.makeText(MainActivity.this, "Username or Password error", Toast.LENGTH_SHORT).show();
		}


		@Override
		public void loginSuccess() {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), SearchActivity.class);
			startActivity(intent);
		}
		
	}

	
    
}
