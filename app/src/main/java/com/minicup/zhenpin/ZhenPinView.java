package com.minicup.zhenpin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Austin on 2017-05-18.
 */

public class ZhenPinView extends LinearLayout {
    private Context context;
    private int screenWidth;
    private MyHorizontalScrollView.OnItemClickListener listener;
    private int position;
    private MyHorizontalScrollView.OnItemClickListener onItemClickListener;

    public ZhenPinView(Context context, MyHorizontalScrollView.OnItemClickListener listener, int position) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.position = position;
        init();
    }

    private void init() {

      /*  WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();*/

        View item = LayoutInflater.from(context).inflate(R.layout.item_zhenpin, null);
        addView(item);
        item.findViewById(R.id.item1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, 0);
            }
        });
        item.findViewById(R.id.item2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, 1);

            }
        });
        item.findViewById(R.id.item3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, 2);
            }
        });
    }

    /**
     * 如果要适配不同的屏幕，获得屏幕宽高，设置ITEM的宽
     */

   /* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = screenWidth-200;

        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }*/
}
