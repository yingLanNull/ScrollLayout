# ScrollLayout
## Abstract 摘要
在ScrollView或者ListView里面使用ViewPager.支持手势上拉滑出,中途停顿,下滑退出页面,类似百度地图内场景抽屉拖拽效果效果

## Gif 动画
![1](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/demo.gif)

## Similar 类似使用
![1](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/Screenshot18.png)
![2](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/Screenshot42.png)
![3](https://github.com/yingLanNull/ScrollLayout/blob/master/Show/Screenshot58.png)

## Demo 下载APK体验
[下载 Download Demo](https://github.com/yingLanNull/ScrollLayout/raw/master/Show/app-debug.apk)

## Usage 使用方法
### Step 1
#### Gradle 配置
```
dependencies {
    compile 'com.yinglan.scrolllayout:scrolllayout:1.0.2'
}
```

### Step 2

#### Function and parameter definitions 功能与参数定义


<table>
  <tbody>
    <tr>
     <td align="center">ScrollLayout</td>
    </tr>
    <tr>
      <td align="center">ContentRecyclerView</td>
    </tr>
    <tr>
      <td align="center">ContentListView</td>
    </tr>
    <tr>
      <td align="center">ContentScrollView</td>
    </tr>
  </tbody>
</table>

<table>
  <tdead>
    <tr>
      <th align="center">配置参数</th>
      <th align="center">参数含义</th>
    </tr>
  </tdead>
  <tbody>
    <tr>
      <td align="center">allowHorizontalScroll</td>
      <td align="center">是否支持横向滚动</td>
    </tr>
    <tr>
      <td align="center">exitOffset</td>
      <td align="center">最低部退出状态时可看到的高度，0为不可见</td>
    </tr>
    <tr>
      <td align="center">isSupportExit</td>
      <td align="center">是否支持下滑退出，支持会有下滑到最底部时的回调</td>
    </tr>
    <tr>
      <td align="center">maxOffset</td>
      <td align="center">打开状态时内容显示区域的高度</td>
    </tr>
    <tr>
      <td align="center">minOffset</td>
      <td align="center">关闭状态时最上方预留高度</td>
    </tr>
    <tr>
      <td align="center">mode</td>
      <td align="center">位置状态，关闭、打开、底部</td>
    </tr>
  </tbody>
</table>


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
依赖内包含重写的ContentScrollView、ContentListView与ContentRecyclerView
可在ScrollLayout里面里面使用ViewPager等功能，配合使用效果更佳。
感谢[Ted](https://github.com/xiongwei-git)的库给的方向。

## License
The work done has been licensed under Apache License 2.0. The license file can be found
[here](LICENSE). You can find out more about the license at:

http://www.apache.org/licenses/LICENSE-2.0

