package com.yobo.studying_view1.lsn18_coordinator;

import java.util.ArrayList;
import java.util.List;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

import com.yobo.studying_view1.R;

public class MainActivity extends AppCompatActivity implements HideScrollListener{

	private RecyclerView recyclerview;
	private ImageButton fab;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.b_activity_main);
		
		recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
		fab = (ImageButton)findViewById(R.id.fab);
		
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setTitle("你好啊");
//		recyclerview.setOnScrollListener(null);
//		recyclerview.addOnScrollListener(new FabScrollListener(this));
		
		recyclerview.setLayoutManager(new LinearLayoutManager(this));
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 60; i++) {
			list.add("Item"+i);
		}
		Adapter adapter = new FabRecyclerAdapter(list );
		recyclerview.setAdapter(adapter );
		
	}

	public void rotate(View v){
		Snackbar.make(v, "你好啊啊啊", Snackbar.LENGTH_SHORT)
				.setAction("确定", new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "就这样把！", Toast.LENGTH_SHORT).show();
					}
				})
				.show();
	}
	
	@Override
	public void onHide() {
		toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
		LayoutParams layoutParams = (LayoutParams) fab.getLayoutParams();
		fab.animate().translationY(fab.getHeight()+layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
	}

	@Override
	public void onShow() {
		toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
		LayoutParams layoutParams = (LayoutParams) fab.getLayoutParams();
		fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

	}

}
