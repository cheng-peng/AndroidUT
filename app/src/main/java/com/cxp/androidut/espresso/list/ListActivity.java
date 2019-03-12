package com.cxp.androidut.espresso.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cxp.androidut.R;

/**
 * 文 件 名: ListActivity
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:05
 * 描    述: Espresso recyclerView使用
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView text1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView=findViewById(R.id.recycler_view);
        text1=findViewById(R.id.text_view);

        initList();
    }

    private void initList() {
        text1.setBackgroundColor(Color.LTGRAY);
        text1.setVisibility(View.GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(30, new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                text1.setText(String.valueOf(position));
                text1.setVisibility(View.VISIBLE);
            }
        }));
    }
}
