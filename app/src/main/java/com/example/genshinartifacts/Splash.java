package com.example.genshinartifacts;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.genshinartifacts.helpers.BdDataJSONParserHelper;
import com.example.genshinartifacts.helpers.UserSession;
import com.example.genshinartifacts.helpers.UsersDataJSONParserHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Splash extends AppCompatActivity {

    private static int timesplash = 1000;
    private Splash splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splash = this;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },timesplash);
        setContentView(R.layout.activity_splash);

        userLogin(this);
    }

    private void userLogin(Context context) {
        if(isOnline(context)) {
            UserSession.init(context);
            if (UserSession.isUserSessionExist()) {
                completeLogin();
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Splash.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },timesplash);
            }
        } else {
            makeToast("Проверьте подключение к Интернету");
        }
    }

    private void completeLogin(){
        BdDataJSONParserHelper.getDataJSONFile(this);
        new Splash.UserJsonTask(splash).execute(UserSession.getUserUID(), UserSession.getUserACCOUNT_ID(), UserSession.getUserCOOKIE_TOKEN());
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
            if (!isConnected) {
                makeToast("Сервер не отвечает");
            } else if (result == null) {
                makeToast("Данные неверные");
            } else {
                UsersDataJSONParserHelper.getUsersData(context, result);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(splash, MainActivity.class);
                        startActivity(intent);
                        splash.finish();
                    }
                },timesplash);
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
}