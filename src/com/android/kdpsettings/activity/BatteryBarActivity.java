package com.android.kdpsettings.activity;

import android.app.Activity;
import android.os.Bundle;
import com.android.kdpsettings.fragments.BatteryBar;

public class BatteryBarActivity extends Activity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  // TODO Auto-generated method stub
  super.onCreate(savedInstanceState);
  
  getFragmentManager().beginTransaction().replace(android.R.id.content,
                new BatteryBar()).commit();
 }

}