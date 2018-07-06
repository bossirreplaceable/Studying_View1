package com.yobo.studying_view1.lsn18_coordinator;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class FabBehavior extends FloatingActionButton.Behavior {
	private boolean visible = true;
	public FabBehavior(Context context,AttributeSet attrs) {
		super();
	}
	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
			FloatingActionButton child, View directTargetChild, View target,
			int nestedScrollAxes) {
		// 当观察View（RecyclerView）开始滑动的时候回调
		//nestedScrollAxes：滑动关联轴，现在我们只关心垂直的滑动
		return nestedScrollAxes==ViewCompat.SCROLL_AXIS_VERTICAL||super.onStartNestedScroll(
				coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
	}
	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout,
			FloatingActionButton child, View target, int dxConsumed,
			int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
				dxUnconsumed, dyUnconsumed);
		if(dyConsumed>0&&visible){
			//show
			visible = false;
			onHide(child);
		}else if(dyConsumed<0){
			//hide
			visible = true;
			onShow(child);
		}
		
	}
	
	public void onHide(FloatingActionButton fab) {
		//隐藏动画-属性动画
//		toolbar.animate().translationY(-toolbar.getHeight()).
// setInterpolator(new AccelerateInterpolator(3));
		MarginLayoutParams layoutParams = (MarginLayoutParams) fab.getLayoutParams();
//		fab.animate().translationY(fab.getHeight()+layoutParams.bottomMargin).
// setInterpolator(new AccelerateInterpolator(3));
		ViewCompat.animate(fab).scaleX(0f).scaleY(0f).start();
	}

	public void onShow(FloatingActionButton fab) {
		//显示动画-属性动画
//		toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
		MarginLayoutParams layoutParams = (MarginLayoutParams)  fab.getLayoutParams();
//		fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
		ViewCompat.animate(fab).scaleX(1f).scaleY(1f).start();
	}
	
}
