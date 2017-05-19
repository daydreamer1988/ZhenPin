package com.minicup.zhenpin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyHorizontalScrollView mScrollView;
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = (MyHorizontalScrollView) findViewById(R.id.scrollView);
        mContainer = (LinearLayout) findViewById(R.id.container);

        //设置Item单击事件
        /**
         * position: item位置
         * index:item中第几个图片
         */

        mScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int index) {
                Toast.makeText(MainActivity.this, ""+position + ":"+index, Toast.LENGTH_SHORT).show();
            }
        });

        mScrollView.addZhenPin(mContainer, getZhenPin());
    }

    private List<ZhenPinBean> getZhenPin() {
        List<ZhenPinBean> mData = new ArrayList<>();
        mData.add(new ZhenPinBean());
        mData.add(new ZhenPinBean());
        mData.add(new ZhenPinBean());
        mData.add(new ZhenPinBean());
        return mData;
    }

    public void doClick(View view) {
        mScrollView.smoothScrollBy(1020, 0);
    }
}
