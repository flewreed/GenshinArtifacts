package com.example.genshinartifacts;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.genshinartifacts.helpers.BdDataJSONParserHelper;
import com.example.genshinartifacts.helpers.UserSession;
import com.example.genshinartifacts.helpers.UsersDataJSONParserHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

	private LoginActivity loginActivity;
	private Button btn_login, btn_instruction;
	private TextInputLayout tinp_uid, tinp_cookie, tinp_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		loginActivity = this;

		btn_login = loginActivity.findViewById(R.id.btn_login);
		btn_instruction = loginActivity.findViewById(R.id.btn_instruction);
		tinp_uid = loginActivity.findViewById(R.id.tinp_uid);
		tinp_cookie = loginActivity.findViewById(R.id.tinp_cookie);
		tinp_id = loginActivity.findViewById(R.id.tinp_id);

		userLogin(this);
//		simpleLogin(this);

		openInstruction();
		deleteHint();
	}

	private void simpleLogin(Context context){
		BdDataJSONParserHelper.getDataJSONFile(this);
		UsersDataJSONParserHelper.getUsersData(context, "");
		Intent intent = new Intent(loginActivity, MainActivity.class);
		startActivity(intent);
		loginActivity.finish();
	}

	private void userLogin(Context context) {
		if(isOnline(context)) {
			UserSession.init(context);
			newSessionLogin();
		} else {
			makeToast("Проверьте подключение к Интернету");
		}
	}

	private void newSessionLogin() {
		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String uid = tinp_uid.getEditText().getText().toString();
				String account_id = tinp_id.getEditText().getText().toString();
				String cookie_token = tinp_cookie.getEditText().getText().toString();



				if (uid.length() == 0) makeToast("Введите uid");
				else if (account_id.length() == 0) makeToast("Введите account_id");
				else if (cookie_token.length() == 0) makeToast("Введите cookie_token");
				else {
					UserSession.saveUserSession(uid, account_id, cookie_token);
					completeLogin();
				}
			}
		});
	}

	private void completeLogin(){
		BdDataJSONParserHelper.getDataJSONFile(this);
		new UserJsonTask(loginActivity).execute(UserSession.getUserUID(), UserSession.getUserACCOUNT_ID(), UserSession.getUserCOOKIE_TOKEN());
	}

	private static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	class UserJsonTask extends AsyncTask<String,Void,String> {
		Context context;
		ProgressDialog pd;
		boolean isConnected;

		public UserJsonTask(Context ctx) {
			context = ctx;
		}

		protected void onPreExecute() {
			super.onPreExecute();
			isConnected = true;
			pd = new ProgressDialog(context);
			pd.setMessage("Течение времени ничего не значит для тех, кому не о чем беспокоиться");
			pd.setCancelable(false);
			pd.show();
		}

		protected String doInBackground(String... params) {
			HttpURLConnection connection = null;
			BufferedReader reader = null;

			try {
				String _url = "http://10.0.2.2/" + params[0] + "/" + params[1] + "/" + params[2];
				URL url = new URL(_url);
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();

				InputStream stream = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(stream));
				StringBuffer buffer = new StringBuffer();
				String line = "";

				while ((line = reader.readLine()) != null) {
					buffer.append(line+"\n");
				}
				return buffer.toString();

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				isConnected = false;
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (pd.isShowing()){
				pd.dismiss();
			}
			if (!isConnected){
				makeToast("Сервер не отвечает");
				UserSession.removeUserSession();
			} else if (result == null) {
				makeToast("Данные неверные");
				UserSession.removeUserSession();
			} else {
				UsersDataJSONParserHelper.getUsersData(context, result);
				Intent intent = new Intent(loginActivity, MainActivity.class);
				startActivity(intent);
				loginActivity.finish();
			}
		}
	}

	private void makeToast(String message) {
		final Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		toast.setDuration(Toast.LENGTH_LONG);

		LayoutInflater inflanter = getLayoutInflater();
		View toast_l = inflanter.inflate(R.layout.custom_toast,  (ViewGroup) findViewById(R.id.custom_toast_container));
		toast.setView(toast_l);
		TextView textToast = toast_l.findViewById(R.id.toast_text);
		textToast.setText(message);
		toast.show();
	}

	private void openInstruction(){
		btn_instruction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Dialog dialog = new Dialog(LoginActivity.this);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				dialog.setContentView(R.layout.dialog_instruction);
				dialog.show();

				Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
				btn_close.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						dialog.dismiss();
					}
				});
			}
		});
	}

	private void deleteHint(){
		if (tinp_uid.isEnabled()) {
			tinp_uid.setHintEnabled(false);
			tinp_uid.setHint("");
		}
		if (tinp_cookie.isEnabled()) {
			tinp_cookie.setHintEnabled(false);
			tinp_cookie.setHint("");
		}
		if (tinp_id.isEnabled()) {
			tinp_id.setHintEnabled(false);
			tinp_id.setHint("");
		}
	}
}