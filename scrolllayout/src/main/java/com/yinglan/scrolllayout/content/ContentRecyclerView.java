package com.yinglan.scrolllayout.content;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;

import com.yinglan.scrolllayout.ScrollLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContentRecyclerView extends RecyclerView {

    private final ContentRecyclerView.CompositeScrollListener compositeScrollListener =
            new ContentRecyclerView.CompositeScrollListener();

    public ContentRecyclerView(Context context) {
        super(context);
    }

    public ContentRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    {
        super.addOnScrollListener(compositeScrollListener);

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


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        while (parent != null) {
            if (parent instanceof ScrollLayout) {
                ((ScrollLayout) parent).setAssociatedRecyclerView(this);
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

    private class CompositeScrollListener extends OnScrollListener {
        private final List<RecyclerView.OnScrollListener> scrollListenerList = new
                ArrayList<RecyclerView.OnScrollListener>();

        public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
            if (listener == null) {
                return;
            }
            for (RecyclerView.OnScrollListener scrollListener : scrollListenerList) {
                if (listener == scrollListener) {
                    return;
                }
            }
            scrollListenerList.add(listener);
        }

        public void removeOnScrollListener(AbsListView.OnScrollListener listener) {
            if (listener == null) {
                return;
            }
            Iterator<RecyclerView.OnScrollListener> iterator = scrollListenerList.iterator();
            while (iterator.hasNext()) {
                RecyclerView.OnScrollListener scrollListener = iterator.next();
                if (listener == scrollListener) {
                    iterator.remove();
                    return;
                }
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView view, int scrollState) {
            List<RecyclerView.OnScrollListener> listeners = new ArrayList<RecyclerView.OnScrollListener>(scrollListenerList);
            for (RecyclerView.OnScrollListener listener : listeners) {
                listener.onScrollStateChanged(view, scrollState);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            List<RecyclerView.OnScrollListener> listeners = new ArrayList<RecyclerView.OnScrollListener>(scrollListenerList);
            for (RecyclerView.OnScrollListener listener : listeners) {
                listener.onScrolled(recyclerView, dx, dy);
            }
        }
    }
}
