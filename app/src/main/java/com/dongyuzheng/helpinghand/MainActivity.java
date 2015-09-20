package com.dongyuzheng.helpinghand;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = getTabHost();
        tabHost.setup();

        TabSpec ts1 = tabHost.newTabSpec("page1");
        ts1.setIndicator("Lend Hand");
        ts1.setContent(new Intent(this, LendHandActivity.class));
        tabHost.addTab(ts1);

        TabSpec ts2 = tabHost.newTabSpec("page2");
        ts2.setIndicator("Chat");
        ts2.setContent(new Intent(this, ChatActivity.class));
        tabHost.addTab(ts2);

        TabSpec ts3 = tabHost.newTabSpec("page3");
        ts3.setIndicator("Profile");
        ts3.setContent(new Intent(this, ProfileActivity.class));
        tabHost.addTab(ts3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
