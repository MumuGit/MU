package com.diabin.ui.parabola;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;

public class ParabolaAnimator {
    /**
     * x:匀速
     * y:y=1/2*g*t*t
     */
    public void start(final View target) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(1000);
        animator.setObjectValues(new PointF(0, 0));
        final PointF pointF = new PointF();
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float v, Object o, Object t1) {
                float x = v *10* 100;
                float y = 0.5f * 9.8f * v*10 * v*10;
                pointF.set(x, y);
                return pointF;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF2 = (PointF) valueAnimator.getAnimatedValue();
                target.setX(pointF2.x);
                target.setY(pointF2.y);
            }
        });
        animator.start();
    }
}
