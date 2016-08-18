# ScrollLayout
## 摘要
在ScrollView或者ListView里面使用ViewPager.支持手势上拉滑出,中途停顿,下滑退出页面,类似百度地图首页效果

## Gif动画
![1](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/demo.gif)

## 类似使用
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

            app:allowHorizontalScroll="true"
            app:exitOffset="0dp"
            app:isSupportExit="true"
            app:maxOffset="260dp"
            app:minOffset="50dp"
            app:mode="open">

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

## LICENSE

    Apache License Version 2.0

