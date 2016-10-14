# ScrollLayout
## Abstract 摘要
在ScrollView或者ListView里面使用ViewPager.支持手势上拉滑出,中途停顿,下滑退出页面,类似高德地图百度地图内场景抽屉拖拽效果效果

## Gif 动画
![1](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/demo.gif)

## Similar 类似使用
![1](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/Screenshot18.png)
![2](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/Screenshot42.png)
![3](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/Screenshot58.png)

## Demo 下载APK体验
[Download Demo](https://github.com/yingLanNull/ScrollLayout/raw/master/Show/app-debug.apk)

## Usage 使用方法
### Step 1
#### Gradle 配置
```
dependencies {
    compile 'com.yinglan.scrolllayout:scrolllayout:1.0.0'
}
```

### Step 2

#### In layout
```
	    <com.yinglan.scrolllayout.ScrollLayout
	        xmlns:app="http://schemas.android.com/apk/res-auto"
            android:id="@+id/scroll_down_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#000000"

            app:allowHorizontalScroll="true"  //是否支持横向滚动
            app:exitOffset="0dp"              //最低部退出状态时可看到的高度，0为不可见
            app:isSupportExit="true"	      //是否支持下滑退出，支持会有下滑到最底部时的回调
            app:maxOffset="260dp"             //打开状态时内容显示区域的高度
            app:minOffset="50dp"              //关闭状态时最上方预留高度
            app:mode="open">                  //默认位置状态，关闭、打开、底部

```

### or

#### In Java Code
```
	{
	    mScrollLayout.setMinOffset(0);
        mScrollLayout.setMaxOffset(800);
        mScrollLayout.setExitOffset(500);
        mScrollLayout.setToOpen();
        mScrollLayout.setIsSupportExit(true);
        mScrollLayout.setAllowHorizontalScroll(true);
        mScrollLayout.setOnScrollChangedListener(mOnScrollChangedListener);
    }

```
## Other 其它

```
	依赖内包含重写的ContentScrollView与ContentListView
	可在ScrollLayout里面里面使用ViewPager等功能，配合使用效果更佳
```

## LICENSE

    Apache License Version 2.0

