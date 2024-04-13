package com.example.eventapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.eventapp.Adapter.FragmentAdapter;
import com.example.eventapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, userselection.class);
                startActivity(intent);
            } });



        fragment_tab_setup();
        getSupportActionBar().setElevation(0);

    }

    private void fragment_tab_setup(){
        FragmentAdapter fragmentAdapter=new FragmentAdapter(this);
        activityMainBinding.viewPagerMain.setAdapter(fragmentAdapter);
        String[]TabName={"Groups","Chats","Calls","Status"};
        new TabLayoutMediator(activityMainBinding.tabLayoutMain,activityMainBinding.viewPagerMain,((tab, position) -> tab.setText(TabName[position]))).attach();
        activityMainBinding.viewPagerMain.setCurrentItem(1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.Settings_main){
            startActivity(new Intent(MainActivity.this,SettingActity.class));
           return true;
        } else if (item.getItemId()==R.id.newGrop_main) {
            startActivity(new Intent(MainActivity.this, Group_Name_Activity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}