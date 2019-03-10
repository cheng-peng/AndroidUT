package com.cxp.androidut.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cxp.androidut.R;

/**
 * 文 件 名: SampleFragment
 * 创 建 人: CXP
 * 创建日期: 2019-03-10 22:14
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class SampleFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

}