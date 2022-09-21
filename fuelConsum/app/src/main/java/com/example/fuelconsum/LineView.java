package com.example.fuelconsum;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LineView extends View {

    /**
     * View起点距离顶部和底部的距离
     */
    private int mViewMargin;
    /**
     * 网格线的颜色
     */
    private int mLineColor;
    /**
     * 阴影部分的颜色
     */
    private int mShadowColor;
    /**
     * 字体大小
     */
    private int mTextSize;
    /**
     * 字体颜色
     */
    private int mTextColor;
    /**
     * 纵坐标刻度
     */
    private List<String> mYList;
    /**
     * 横坐标刻度
     */
    private List<String> mXList;
    /**
     * 网格线的高度
     */
    private int mHeight;
    /**
     * 网格线距离左边的距离
     */
    private float mMarginLeft;

    private List<Point> mListPoint;
    private LineType mLineType = LineType.LINE;
    private Paint mPaint;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LineView, defStyleAttr, 0);
        int count = array.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = array.getIndex(i);
            switch (index) {
                case R.styleable.LineView_viewMargin:
                    mViewMargin = array.getDimensionPixelSize(index, (int) TypedValue.
                            applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.LineView_lineColor:
                    mLineColor = array.getColor(index, Color.BLACK);
                    break;
                case R.styleable.LineView_shadowColor:
                    mShadowColor = array.getColor(index, Color.BLACK);
                    break;
                case R.styleable.LineView_lineTextColor:
                    mTextColor = array.getColor(index, Color.BLACK);
                    break;
                case R.styleable.LineView_lineTextSize:
                    mTextSize = array.getDimensionPixelSize(index, (int) TypedValue.
                            applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
                    break;
            }
        }
        array.recycle();
        init();
    }

    private void init() {
        /**
         * 设置画笔的属性
         */
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mLineColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setAntiAlias(true);                    //取消锯齿
        mPaint.setStyle(Paint.Style.STROKE);          //设置画笔为空心
        mMarginLeft = (mViewMargin * 2);              //设置左边的偏移距离
        mPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mHeight == 0) {
            mHeight = getHeight() - mViewMargin * 2;
        }
        drawLine(canvas);
        drawXScale(canvas);
        drawYScale(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mTextColor);
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        mListPoint = getPointList();
        if (mLineType == LineType.ARC) {
            drawScrollLine(canvas);
        } else {
            drawLineView(canvas);
        }
        /**
         * 画阴影
         */
        if (mLineType == LineType.ARC) {
            Point pStart = new Point();
            Point pEnd = new Point();
            Path path = new Path();
            for (int i = 0; i < 5; i++) {
                pStart = mListPoint.get(i);
                pEnd = mListPoint.get(i + 1);
                Point point3 = new Point();
                Point point4 = new Point();
                float wd = (pStart.x + pEnd.x) / 2;
                point3.x = wd;
                point3.y = pStart.y;
                point4.x = wd;
                point4.y = pEnd.y;
                path.moveTo(pStart.x, pStart.y);
                path.cubicTo(point3.x, point3.y, point4.x, point4.y, pEnd.x, pEnd.y);
                path.lineTo(pEnd.x, getHeight() - mViewMargin);
                path.lineTo(pStart.x, getHeight() - mViewMargin);
            }
            path.close();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mShadowColor);
            canvas.drawPath(path, mPaint);
        } else {
            Path path = new Path();
            path.moveTo(mListPoint.get(0).x, mListPoint.get(0).y);
            for (int i = 1; i < 6; i++) {
                path.lineTo(mListPoint.get(i).x, mListPoint.get(i).y);
            }
            /**
             * 链接最后两个点
             */
            int index = mListPoint.size() - 1;
            path.lineTo(mListPoint.get(index).x, getHeight() - mViewMargin);
            path.lineTo(mListPoint.get(0).x, getHeight() - mViewMargin);
            path.close();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mShadowColor);
            canvas.drawPath(path, mPaint);
        }
    }

    /**
     * 绘制网格线
     */
    private void drawLine(Canvas canvas) {
        /**
         * 左边第一条竖线
         */
        canvas.drawLine(mMarginLeft, mViewMargin, mMarginLeft, getHeight() - mViewMargin, mPaint);

        /**
         * 5条水平的横线
         */
        for (int i = 0; i < mXList.size(); i++) {
            canvas.drawLine(mMarginLeft, mViewMargin + i * ((getHeight() - mViewMargin * 2) / 5), getWidth() - mViewMargin,
                    mViewMargin + i * ((getHeight() - mViewMargin * 2) / 5), mPaint);
        }

        /**
         * 右边最后一条竖线
         */
        canvas.drawLine(getWidth() - mViewMargin, mViewMargin,
                getWidth() - mViewMargin, mViewMargin + ((getHeight() - mViewMargin * 2)), mPaint);
    }

    /**
     * 绘制y轴刻度
     */
    private void drawYScale(Canvas canvas) {
        for (int i = 0; i < mYList.size(); i++) {
            if (i == 0) {
                canvas.drawText(mYList.get(i).toString(), mViewMargin,
                        mViewMargin, mPaint);
            }
            if (i != 0 && i != 5) {
                canvas.drawText(mYList.get(i).toString(), mViewMargin,
                        mViewMargin + i * ((getHeight() - mViewMargin * 2) / 5), mPaint);
            }
            if (i == 5) {
                canvas.drawText(mYList.get(i).toString(), mViewMargin,
                        mViewMargin + i * ((getHeight() - mViewMargin * 2) / 5) - dpToPx(getContext(), 3), mPaint);
            }
        }
    }

    /**
     * 绘制x轴刻度
     */
    private void drawXScale(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        for (int i = 0; i < mXList.size(); i++) {
            if (i == 0) {
                canvas.drawText(mXList.get(i), mMarginLeft - dpToPx(getContext(), 3),
                        getHeight() - mViewMargin + dpToPx(getContext(), 10), mPaint);
            }
            if (i != 0 && i != 6) {
                canvas.drawText(mXList.get(i), mMarginLeft + i * (getWidth() / 6),
                        getHeight() - mViewMargin + dpToPx(getContext(), 10), mPaint);
            }
            if (i == 6) {
                canvas.drawText(mXList.get(i), mMarginLeft + i * (getWidth() / 6),
                        getHeight() - mViewMargin + dpToPx(getContext(), 10), mPaint);
            }
        }
    }

    /**
     * 绘制折线图
     */
    private void drawLineView(Canvas canvas) {
        Path path = new Path();
        path.moveTo(mListPoint.get(0).x, mListPoint.get(0).y);
        for (int i = 1; i < 6; i++) {
            path.lineTo(mListPoint.get(i).x, mListPoint.get(i).y);
        }
        canvas.drawPath(path, mPaint);
    }

    /**
     * 绘制曲线图
     */
    private void drawScrollLine(Canvas canvas) {
        Point pStart = new Point();
        Point pEnd = new Point();
        Path path = new Path();
        for (int i = 0; i < 5; i++) {
            pStart = mListPoint.get(i);
            pEnd = mListPoint.get(i + 1);
            Point point3 = new Point();
            Point point4 = new Point();
            float wd = (pStart.x + pEnd.x) / 2;
            point3.x = wd;
            point3.y = pStart.y;
            point4.x = wd;
            point4.y = pEnd.y;
            path.moveTo(pStart.x, pStart.y);
            path.cubicTo(point3.x, point3.y, point4.x, point4.y, pEnd.x, pEnd.y);
            canvas.drawPath(path, mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setViewData(List<String> yList, List<String> xList) {
        this.mYList = yList;
        this.mXList = xList;
    }

    /**
     * 根据手机分辨率将px 转为 dp
     */
    private float dpToPx(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue * scale + 0.5f);
    }

    private List<Point> getPointList() {
        List<Point> mList = new ArrayList<>();
        float width = getWidth() - (mMarginLeft + mViewMargin);
        float height = getHeight() - mViewMargin * 2;
        for (int i = 0; i < 6; i++) {
            Point point = new Point();
            if (i == 0) {
                point.x = mMarginLeft;
                point.y = getHeight() - mViewMargin;
            }
            if (i == 1) {
                point.x = mMarginLeft + width * 0.2f;
                point.y = mViewMargin + height * 0.6f;
            }
            if (i == 2) {
                point.x = mMarginLeft + width * 0.4f;
                point.y = mViewMargin + height * 0.8f;
            }
            if (i == 3) {
                point.x = mMarginLeft + width * 0.6f;
                point.y = mViewMargin + height * 0.6f;
            }
            if (i == 4) {
                point.x = mMarginLeft + width * 0.8f;
                point.y = mViewMargin + height * 0.8f;
            }
            if (i == 5) {
                point.x = mMarginLeft + width;
                point.y = mViewMargin + height * 0.6f;
            }
            mList.add(point);
        }
        return mList;
    }

    /**
     * 枚举类型直线或者是弧线
     */
    public enum LineType {
        LINE, ARC
    }

    public void setmLineType(LineType mLineType) {
        this.mLineType = mLineType;
    }

    private class Point {

        public float x;
        public float y;

        public Point() {

        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

}