package com.cxp.androidut.espresso.async2;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;

import java.lang.ref.WeakReference;

/**
 * 文 件 名: LoadingDialogFragment
 * 创 建 人: CXP
 * 创建日期: 2019-03-12 13:57
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LoadingDialogFragment extends DialogFragment {
    public static final String TAG = "Loading";

    private static final long DELAY = DateUtils.SECOND_IN_MILLIS * 3;

    private final LoadingHandler handler;

    public LoadingDialogFragment() {
        this.handler = new LoadingHandler(this);
    }

    public boolean isDone = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        handler.sendEmptyMessageDelayed(LoadingHandler.MSG_DISMISS, DELAY);

        return new AlertDialog.Builder(getActivity())
                .setTitle("loading")
                .setMessage("wait")
                .create();
    }

    @Override
    public void onDestroyView() {
        handler.removeMessages(LoadingHandler.MSG_DISMISS);
        super.onDestroyView();
    }

    private static class LoadingHandler extends Handler {
        private static final int MSG_DISMISS = 0;
        private final WeakReference<DialogFragment> ref;

        public LoadingHandler(DialogFragment fragment) {
            ref = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            DialogFragment fragment = ref.get();
            if (fragment != null) {
                fragment.dismiss();
                Activity activity = fragment.getActivity();
                if (activity instanceof AsyncActivity2) {
                    ((AsyncActivity2) activity).onLoadingFinished();
                }
            }
        }
    }
}