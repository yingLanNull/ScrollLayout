/*
 *
 *  * sufly0001@gmail.com Modify the code to enhance the ease of use.
 *  *
 *  * Copyright (C) 2015 Ted xiong-wei@hotmail.com
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package com.yinglan.scrolllayout.content;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ListView;

import com.yinglan.scrolllayout.ScrollLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContentListView extends ListView {
    private final CompositeScrollListener compositeScrollListener =
            new CompositeScrollListener();

    private boolean showShadow = false;
    private View shadowView;

    public ContentListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ContentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentListView(Context context) {
        super(context);
    }

    {
        super.setOnScrollListener(compositeScrollListener);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                ViewParent parent = getParent();
                while (parent != null) {
                    if (parent instanceof ScrollLayout) {
                        int height = ((ScrollLayout) parent).getMeasuredHeight() - ((ScrollLayout) parent).minOffset;
                        if (layoutParams.height == height) {
                            return;
                        } else {
                            layoutParams.height = height;
                            break;
                        }
                    }
                    parent = parent.getParent();
                }
                setLayoutParams(layoutParams);
            }
        });
    }

    /**
     * 添加一个OnScrollListener,不会取代已添加OnScrollListener
     * <p>
     * <b>Make sure call this on UI thread</b>
     * </p>
     *
     * @param listener the listener to add
     */
    @Override
    public void setOnScrollListener(final OnScrollListener listener) {
        addOnScrollListener(listener);
    }

    /**
     * 添加一个OnScrollListener,不会取代已添加OnScrollListener
     * <p>
     * <b>Make sure call this on UI thread</b>
     * </p>
     *
     * @param listener the listener to add
     */
    public void addOnScrollListener(final OnScrollListener listener) {
        throwIfNotOnMainThread();
        compositeScrollListener.addOnScrollListener(listener);
    }

    /**
     * 删除前一个添加scrollListener,只会删除完全相同的对象
     * <p>
     * <b>Make sure call this on UI thread.</b>
     * </p>
     *
     * @param listener the listener to remove
     */
    public void removeOnScrollListener(final OnScrollListener listener) {
        throwIfNotOnMainThread();
        compositeScrollListener.removeOnScrollListener(listener);
    }

    /**
     * 需要调用之前setOnScrollListener
     *
     * @param shadowView the shadow view
     */
    public void setTopShadowView(View shadowView) {
        if (shadowView == null) {
            return;
        }
        this.shadowView = shadowView;
        addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                View firstChild = view.getChildAt(0);
                if (firstChild != null) {
                    if (firstVisibleItem == 0 && firstChild.getTop() == 0) {
                        showShadow = false;
                        showTopShadow();
                    } else if (!showShadow) {
                        showShadow = true;
                        showTopShadow();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });
    }

    private void showTopShadow() {
        if (shadowView == null || shadowView.getVisibility() == View.VISIBLE) {
            return;
        }
        shadowView.setVisibility(View.VISIBLE);
    }

    private void hideTopShadow() {
        if (shadowView == null || shadowView.getVisibility() == View.GONE) {
            return;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        while (parent != null) {
            if (parent instanceof ScrollLayout) {
                ((ScrollLayout) parent).setAssociatedListView(this);
                break;
            }
            parent = parent.getParent();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Must be invoked from the main thread.");
        }
    }

    private class CompositeScrollListener implements OnScrollListener {
        private final List<OnScrollListener> scrollListenerList = new
                ArrayList<OnScrollListener>();

        public void addOnScrollListener(OnScrollListener listener) {
            if (listener == null) {
                return;
            }
            for (OnScrollListener scrollListener : scrollListenerList) {
                if (listener == scrollListener) {
                    return;
                }
            }
            scrollListenerList.add(listener);
        }

        public void removeOnScrollListener(OnScrollListener listener) {
            if (listener == null) {
                return;
            }
            Iterator<OnScrollListener> iterator = scrollListenerList.iterator();
            while (iterator.hasNext()) {
                OnScrollListener scrollListener = iterator.next();
                if (listener == scrollListener) {
                    iterator.remove();
                    return;
                }
            }
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            List<OnScrollListener> listeners = new ArrayList<OnScrollListener>(scrollListenerList);
            for (OnScrollListener listener : listeners) {
                listener.onScrollStateChanged(view, scrollState);
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                             int totalItemCount) {
            List<OnScrollListener> listeners = new ArrayList<OnScrollListener>(scrollListenerList);
            for (OnScrollListener listener : listeners) {
                listener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        }
    }
}
