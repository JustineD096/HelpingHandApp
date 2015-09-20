package com.dongyuzheng.helpinghand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.LayoutParams;

import java.util.HashMap;
import org.json.*;

public class LendHandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_hand);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.lm);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("Authorization", "token " + GlobalVariables.api_token);

        JSONArray info = null;
        try {
        info = HttpFactory.GET(GlobalVariables.url+"api/goals/", new HashMap<String, String>(), hm).getJSONArray("data");
            System.out.println(info);

            for (int j = 0; j < info.length(); j++) {
                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                // Create TextView
                TextView goal = new TextView(this);
                goal.setText(info.getJSONObject(j).getString("goal"));
                ll.addView(goal);

                // Create Button
                final Button btn = new Button(this);
                // Give button an ID
                btn.setId(j+1);
                btn.setText("Lend a Hand");
                // set the layoutParams on the button
                btn.setLayoutParams(params);

                final int index = j;
                // Set click listener for button
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Log.i("TAG", "index :" + index);

                        Toast.makeText(getApplicationContext(),
                                "Clicked Button Index :" + index,
                                Toast.LENGTH_LONG).show();

                    }
                });

                //Add button to LinearLayout
                ll.addView(btn);
                //Add button to LinearLayout defined in XML
                lm.addView(ll);

            }


        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lend_hand, menu);
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
