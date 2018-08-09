package com.diabin.ui.dragbubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.diabin.ui.R;

public class DragBubbleView extends View {
    /**
     * 气泡默认状态--静止
     */
    private final int BUBBLE_STATE_DEFAUL = 0;
    /**
     * 气泡相连
     */
    private final int BUBBLE_STATE_CONNECT = 1;
    /**
     * 气泡分离
     */
    private final int BUBBLE_STATE_APART = 2;
    /**
     * 气泡消失
     */
    private final int BUBBLE_STATE_DISMISS = 3;

    /**
     * 气泡半径
     */
    private float mBubbleRadius;
    /**
     * 气泡颜色
     */
    private int mBubbleColor;
    /**
     * 气泡消息文字
     */
    private String mTextStr;
    /**
     * 气泡消息文字颜色
     */
    private int mTextColor;
    /**
     * 气泡消息文字大小
     */
    private float mTextSize;
    /**
     * 不动气泡的半径
     */
    private float mBubStillRadius;
    /**
     * 可动气泡的半径
     */
    private float mBubMoveableRadius;
    /**
     * 不动气泡的圆心
     */
    private PointF mBubStillCenter;
    /**
     * 可动气泡的圆心
     */
    private PointF mBubMoveableCenter;
    /**
     * 气泡的画笔
     */
    private Paint mBubblePaint;
    /**
     * 贝塞尔曲线path
     */
    private Path mBezierPath;

    private Paint mTextPaint;

    private Rect mTextRect;

    private Paint mBurstPaint;

    private Rect mBurstRect;

    /**
     * 气泡状态标志
     */
    private int mBubbleState = BUBBLE_STATE_DEFAUL;
    /**
     * 两气泡圆心距离
     */
    private float mDist;
    /**
     * 气泡相连状态最大圆心距离
     */
    private float mMaxDist;
    /**
     * 手指触摸偏移量
     */
    private final float MOVE_OFFSET;

    /**
     * 气泡爆炸的bitmap数组
     */
    private Bitmap[] mBurstBitmapsArray;
    /**
     * 是否在执行气泡爆炸动画
     */
    private boolean mIsBurstAnimStart = false;

    /**
     * 当前气泡爆炸图片index
     */
    private int mCurDrawableIndex;
    /**
     * 气泡爆炸的图片id数组
     */
    private int[] mBurstDrawablesArray = {R.drawable.burst_1, R.drawable.burst_2
            , R.drawable.burst_3, R.drawable.burst_4, R.drawable.burst_5};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragBubbleView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DragBubbleView(Context context, @Nullable AttributeSet attrs,
                          int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DragBubbleView, defStyleAttr, 0);
        mBubbleRadius = array.getDimension(R.styleable.DragBubbleView_bubble_radius, mBubbleRadius);
        mBubbleColor = array.getColor(R.styleable.DragBubbleView_bubble_color, Color.RED);
        mTextStr = array.getString(R.styleable.DragBubbleView_bubble_text);
        mTextSize = array.getDimension(R.styleable.DragBubbleView_bubble_textSize, mTextSize);
        mTextColor = array.getColor(R.styleable.DragBubbleView_bubble_textColor, Color.WHITE);
        array.recycle();

        mBubStillRadius = mBubbleRadius;
        mBubMoveableRadius = mBubStillRadius;
        mMaxDist = 8 * mBubbleRadius;
        MOVE_OFFSET = mMaxDist / 4;

        //抗锯齿
        mBubblePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBubblePaint.setColor(mBubbleColor);
        mBubblePaint.setStyle(Paint.Style.FILL);
        mBezierPath = new Path();

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextRect = new Rect();
        /**
         * 爆炸
         */
        mBurstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBurstPaint.setFilterBitmap(true);
        mBurstRect = new Rect();
        mBurstBitmapsArray = new Bitmap[mBurstDrawablesArray.length];
        for (int i = 0; i < mBurstDrawablesArray.length; i++) {
            //将气泡爆炸的drawable转为bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mBurstDrawablesArray[i]);
            mBurstBitmapsArray[i] = bitmap;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1、画静止状态
        //2、画相连状态
        //3、画分离状态
        //4、画消失状态--爆炸动画
        //画拖拽气泡和文字
        if (mBubbleState == BUBBLE_STATE_DEFAUL) {
            canvas.drawCircle(mBubMoveableCenter.x, mBubMoveableCenter.y, mBubMoveableRadius,
                    mBubblePaint);
            mTextPaint.getTextBounds(mTextStr, 0, mTextStr.length(), mTextRect);
            canvas.drawText(mTextStr, mBubMoveableCenter.x - mTextRect.width() / 2,
                    mBubMoveableCenter.y + mTextRect.height() / 2, mTextPaint);
        }
        //画相连的气泡状态
        if (mBubbleState == BUBBLE_STATE_CONNECT) {
            //画静止气泡
            canvas.drawCircle(mBubStillCenter.x, mBubStillCenter.y, mBubStillRadius,
                    mBubblePaint);
            //画相连曲线
            
        }

    }
}
