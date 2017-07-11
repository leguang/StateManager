# StateManager

[![Release](https://jitpack.io/v/leguang/StateManager.svg)](https://jitpack.io/#leguang/StateManager)

StateManager是一个页面状态管理工具，可以让开发者方便而又灵活地切换界面的状态。（欢迎Star一下）
## 能做什么？([下载 apk]( ))
- **不必在XML中配置Layout即可给任何界面添加状态，不论是Activity或者是Fragment亦或者View**
- **默认提供空数据状态，异常状态，网络异常状态，Loading状态**
- **简洁的API，简单的配置**
- **也可以在XML中配置实用**

# 如何使用它？

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
	        compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:v1.9.7'
	}
```
   

# License

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