package com.dongyuzheng.helpinghand;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String get_token_from_web(){
        EditText user = (EditText)findViewById(R.id.editText);
        EditText pwd = (EditText)findViewById(R.id.editText2);
        String username = user.getText().toString();
        String password = pwd.getText().toString();

        HashMap<String,String> hm0 = new HashMap<String,String>();
        hm0.put("username", username);
        hm0.put("password", password);

        HashMap<String,String> hm = new HashMap<String,String>();

        // TO-DO: CHECK IF BAD LOGIN VS BAD CONNECTION
        try {
            return HttpFactory.POST("http://45.79.163.119/api/get-token/", hm0, hm).getString("token");
        }
        catch (Exception e){
            return "bad_login";
        }
    }

    public void onLoginClick(View v) {

        String token = get_token_from_web();
        GlobalVariables.api_token = token;

        if (token.equals("bad_login")) {
            CharSequence text = "Incorrect username or password.";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (token.equals("bad_connection")) {
            CharSequence text = "Bad connection to server.";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
