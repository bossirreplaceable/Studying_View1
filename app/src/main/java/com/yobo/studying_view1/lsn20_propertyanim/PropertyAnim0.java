package com.yobo.studying_view1.lsn20_propertyanim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;

import com.yobo.studying_view1.R;

/**
 * Created by YoBo on 2018/7/4.
 */

public class PropertyAnim0 extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_anim_activity);
    }


    //补间动画
    public void betweenAnim(View v) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_x);
        v.startAnimation(animation);
    }

    /**
     * 抛物线执行效果（利用重力加速度）
     * x
     * y=0.5*g*v*v    v-速度   g-9.8
     */
    public void propertyAnim1(final View v) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(4000);
        animator.setObjectValues(new PointF(0, 0));
        //估值器，符合计算规则，执行相应的动画
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            /**
             * @param v   //执行的百分比
             * @param pointF //开始的point
             * @param t1  //结束的point
             * @return  //正移动到的点的位置
             */
            @Override
            public PointF evaluate(float v, PointF pointF, PointF t1) {
                PointF point = new PointF();
                point.x = 50 * (v * 10);
                point.y = 0.5f * 9.8f * (v * 10) * (v * 10);
                return point;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF p = (PointF) valueAnimator.getAnimatedValue();
                v.setX(p.x);
                v.setY(p.y);
            }
        });
        animator.start();
    }

    public void anim_ofFloat(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", 0, 200, 300, 100, 50, 1);
        animator.setDuration(3000);
//        animator.setInterpolator(new AccelerateInterpolator(5));
        animator.setInterpolator(new CycleInterpolator(4));//加速器
        animator.start();
    }

    //多动画执行，但不是同时执行的
    public void anim_ofFloat2(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", 0, 200, 300);
        animator.setDuration(3000);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationY", 0, 200, 300);
        animator1.setDuration(3000);
        animator1.start();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "rotation", 0, 50, 100, 200);
        animator2.setDuration(3000);
        animator2.start();
    }

    //多个动画同时执行---方法1：动画监听，开启第一个动画时候，同时开启第二个
    public void anim_More(final View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", 0, 200);
        animator.setDuration(2000);
        //开启动画监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //开启动画的时候，会不断的调用该方法
                //valueAnimator.getAnimatedFraction(); 获取动画执行的百分比；
                //获取动画执行0-200的值
                float value = (float) valueAnimator.getAnimatedValue();
                v.setRotation((value / 200) * 360);
                v.setAlpha((value / 400));
            }
        });
        animator.start();
    }


    //多动画同时执行，方法二：控制器
    public void anim_Holder(View v) {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("rotation", 0, 50, 100, 200, 300, 0);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("translationX", 0, 50, 100, 200);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("translationY", 0, 50, 100, 200);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, holder1, holder2, holder3);
        animator.setDuration(3000);
        animator.start();

    }

    //多动画同时，方法三：动画集合
    public void anim_Set(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", 0, 200, 300);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "translationY", 0, 200, 300);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "rotation", 0, 50, 100, 200);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
//        set.playTogether(animator, animator1, animator2);
        set.playSequentially(animator, animator1, animator2);//依次执行动画
        set.start();

    }


}
