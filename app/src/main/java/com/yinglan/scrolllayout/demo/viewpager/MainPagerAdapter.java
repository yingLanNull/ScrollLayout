package com.yinglan.scrolllayout.demo.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.yinglan.scrolllayout.demo.model.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @function viewpageradapter
 * @auther: Created by yinglan
 * @time: 16/3/16
 */
public class MainPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private ArrayList<Address> mAllAddressList;
    private Map<Integer, View> mAllImageMap;
    private Context mContext;
    private OnClickItemListenerImpl mOnClickItemListener;

    @Override
    public void onClick(View v) {
        if (null != v && v instanceof ImageView) {
            if (mOnClickItemListener != null) {
                int position = -1;
                Address address = (Address) v.getTag();
                if (mAllAddressList != null && address != null) {
                    position = mAllAddressList.indexOf(address);
                }
                mOnClickItemListener.onClickItem(v, position);
            }
        }
    }

    public MainPagerAdapter(Context context) {
        mContext = context;
        mAllImageMap = new HashMap<>();
        mAllAddressList = new ArrayList<>();
    }

    public void initViewUrl(ArrayList<Address> addresses) {
        if (null == addresses) return;
        mAllAddressList.clear();
        mAllAddressList.addAll(addresses);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAllAddressList.size();
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        if (mAllImageMap.containsKey(position)) {
            container.removeView(mAllImageMap.get(position));
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView;
        Address address = mAllAddressList.get(position);
        if (mAllImageMap.containsKey(position)) {
            View oldView = mAllImageMap.get(position);
            Object tag = oldView.getTag();
            if (null != tag && tag instanceof Address) {
                if (tag.equals(address)) {
                    itemView = oldView;
                    container.addView(itemView);
                    return itemView;
                }
            }
            container.removeView(oldView);
            mAllImageMap.remove(position);
        }

        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(address.getImageUrl()).into(imageView);
        imageView.setTag(address);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        mAllImageMap.put(position, imageView);
        itemView = imageView;
        itemView.setOnClickListener(this);
        container.addView(itemView);
        return itemView;
    }

    public void setOnClickItemListener(OnClickItemListenerImpl onClickItemListener) {
        mOnClickItemListener = onClickItemListener;
    }

    public interface OnClickItemListenerImpl {
        void onClickItem(View item, int position);
    }

}
