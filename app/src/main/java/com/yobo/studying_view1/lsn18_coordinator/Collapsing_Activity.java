package com.yobo.studying_view1.lsn18_coordinator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yobo.studying_view1.R;

public class Collapsing_Activity extends AppCompatActivity {
	private Toolbar toolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b_collapsing_activity);
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		toolbar.setTitle("我是title");
		setSupportActionBar(toolbar);
		setTitle("你好22");
	}

}
