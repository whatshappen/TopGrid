# TopGrid
高仿网易新闻新版频道管理功能

## 描述
该项目是仿网易新闻客户端的频道管理功能，实现ITEM的拖拽，添加和删除操作。

## 使用  
可拖拽的GridView：  
    <pre><code>
		<com.whatshappen.topgrid.view.DragGrid
                	android:id="@+id/userGridView"
                	android:layout_width="fill_parent"
                	android:layout_height="wrap_content"
                	android:layout_marginLeft="10dip"
                	android:layout_marginRight="10dip"
                	android:gravity="center"
                	android:horizontalSpacing="6dip"
                	android:listSelector="@android:color/transparent"
                	android:numColumns="4"
                	android:scrollbars="vertical"
                	android:stretchMode="columnWidth"
                	android:verticalSpacing="14.0px" />

</code></pre>
不可拖拽的GridView：  
	<pre><code>
		<com.whatshappen.topgrid.view.OtherGridView
                    android:id="@+id/otherGridView"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginLeft="10dip"
                    android:layout_marginRight="10dip"
                    android:gravity="center"
                    android:horizontalSpacing="6dip"
                    android:listSelector="@android:color/transparent"
                    android:numColumns="4"
                    android:scrollbars="vertical"
                    android:stretchMode="columnWidth"
                    android:verticalSpacing="14.0px" />

</code></pre>

## 实现效果
![](https://github.com/whatshappen/TopGrid/blob/master/screenshots/demo.gif)  

在此要感谢[vipra](http://blog.csdn.net/vipzjyno1/article/details/25005851)，此项目是在这个项目基础上修改的。
## License
Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)  
<pre>
Copyright 2014 trinea.cn

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
