# StateManager

[![Release](https://jitpack.io/v/leguang/StateManager.svg)](https://jitpack.io/#leguang/StateManager)

StateManager是一个页面状态管理工具，可以让开发者方便而又灵活地切换界面的状态。（欢迎Star一下）
## 能做什么？([下载 apk](https://github.com/leguang/StateManager/blob/master/app-debug.apk))
- **不必在XML中配置Layout即可给任何界面添加状态，不论是Activity或者是Fragment亦或者View**
- **默认提供空数据状态，异常状态，网络异常状态，Loading状态**
- **简洁的API，简单的配置**
- **也可以在XML中配置使用**

## 如何使用它？

1. 先在项目目录下的的build.gradle 的 repositories 添加:
```
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

2. 然后在App目录下的dependencies添加:
```
	dependencies {
	         //页面状态管理
   		 compile 'com.github.leguang:StateManager:1.0.1'
	}
```
此时同步一下，即已完成引入。

3. 代码中简单使用：

```
//首先配置一下：
 StateManager mStateManager = StateManager.builder(this)//通过Build模式构建。
                .setContent(textView)//为哪部分内容添加状态管理。这里可以是Activity，Fragment或任何View。
                .setLoadingView(R.layout.state_loading)//设置Loading的布局样式。
                .setLoadingText("加载我只服你…")//当然要想这个文字起作用，布局中的TextView的id必须为tv_loading_state。
                .setEmptyView(R.layout.state_empty)//设置空数据的布局样式。
                .setEmptyImage(R.drawable.ic_empty_state_200px)//当然要想设置图片起作用，ImageView的id必须为iv_empty_state。
                .setEmptyText("大爷，实在是没有数据了")//当然要想这个文字起作用，布局中的TextView的id必须为tv_empty_state。
                .setEmptyOnClickListener(new StateListener.OnClickListener() {//设置点击事件。
                    @Override
                    public void onClick(View view) {
                        showToast("空状态");
                    }
                })
                .setErrorView(R.layout.state_error)//设置异常状态的布局样式。
                .setErrorImage(R.drawable.ic_empty_state_200px)//当然要想设置图片起作用，ImageView的id必须为iv_error_state。
                .setErrorText("大爷，出错了")//当然要想这个文字起作用，布局中的TextView的id必须为tv_error_state。
                .setErrorOnClickListener(new StateListener.OnClickListener() {//设置点击事件。
                    @Override
                    public void onClick(View view) {
                        showToast("错误状态");
                    }
                })
                .setNetErrorView(R.layout.state_net_error)//设置网络异常状态的布局样式。
                .setNetErrorImage(R.drawable.ic_empty_state_200px)//当然要想设置图片起作用，ImageView的id必须为iv_net_error_state。
                .setNetErrorText("大爷，有人拔网线了")//当然要想这个文字起作用，布局中的TextView的id必须为tv_net_error_state。
                .setNetErrorOnClickListener(new StateListener.OnClickListener() {//设置点击事件。
                    @Override
                    public void onClick(View view) {
                        showToast("谁拔了我的网线");
                    }
                })
                .build();
 
//在需要用到的地方切换状态。

mStateManager.showLoading();//切换到Loading状态

mStateManager.showEmpty();//切换到空数据状态

mStateManager.showError();//切换到异常状态

mStateManager.showNetError();//切换到网络异常状态

mStateManager.showContent();//切换到默认状态

```

## 高级用法：
你也可以自己定制状态页面样式

```
mStateManager = StateManager.builder(this)
                .setContent(recyclerView)
                .setEmptyView(R.layout.state_custom_empty)
                .setErrorView(R.layout.state_custom_error)
                .setLoadingView(R.layout.state_custom_loading)
                .setConvertListener(new StateListener.ConvertListener() {
                    @Override
                    public void convert(BaseViewHolder holder, StateLayout stateLayout) {
                        holder.setOnClickListener(R.id.bt0, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了正中间");
                            }
                        }).setOnClickListener(R.id.bt1, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了左上角");
                            }
                        }).setOnClickListener(R.id.bt2, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了右上角");
                            }
                        }).setOnClickListener(R.id.bt3, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了左下角");
                            }
                        }).setOnClickListener(R.id.bt4, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "点击了右上角");
                            }
                        }).setOnClickListener(R.id.tv_error, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "出错了");
                            }
                        }).setOnClickListener(R.id.tv_loading, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtils.showToast(RecyclerView.this, "刷新");
                            }
                        });
                    }
                })
                .build();
```

>**持续更新!，欢迎Issues+Star项目**

## 感谢
[hongyangAndroid/LoadingAndRetryManager](https://github.com/hongyangAndroid/LoadingAndRetryManager)

[CymChad/BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)


## License

```
Copyright 2016 李勇

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
