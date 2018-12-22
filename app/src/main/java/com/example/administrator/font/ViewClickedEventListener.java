package com.example.administrator.font;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;



public class ViewClickedEventListener extends View.AccessibilityDelegate {


    public static final int FRAGMENT_TAG_KEY=0xFFF111;
    private String name;
    private Context mContext;

    /**
     * 设置Activity页面中View的事件监听
     * @param activity
     */
    public void setActivityTracker(Activity activity,String name) {
        this.mContext=activity.getApplicationContext();
        this.name=name;
        View contentView = activity.findViewById(android.R.id.content);
        if (contentView != null) {
            setViewClickedTracker(contentView, null);
        }
    }

    /**
     * 设置Fragment页面中View的事件监听
     * @param fragment
     */
    public void setFragmentTracker(Fragment fragment) {
        View contentView = fragment.getView();
        if (contentView != null) {
            setViewClickedTracker(contentView, fragment);
        }
    }

    private void setViewClickedTracker(View view, Fragment fragment) {

        if (view == null) {
            return;
        }

        if (needTracker(view)) {
            if (fragment != null) {
                view.setTag(FRAGMENT_TAG_KEY, fragment);
            }
            view.setAccessibilityDelegate(this);
        }
        if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setViewClickedTracker(((ViewGroup) view).getChildAt(i), fragment);
            }
        }
    }


    private boolean needTracker(View view) {
        if (view.getVisibility() == View.VISIBLE && view.isClickable() && ViewCompat.hasOnClickListeners(view)) {
            return true;
        }
        return false;
    }

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        super.sendAccessibilityEvent(host, eventType);
        if (AccessibilityEvent.TYPE_VIEW_CLICKED == eventType && host != null) {
            SensorUtils.getInstance(mContext).sensor(new ViewRoot(host,name).getCompletePath(),null);
        }
    }






}
