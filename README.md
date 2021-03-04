# jackson-replaces-fastjson
# 2步jackson快速替换fastjson
因为fastjson的安全漏洞,项目需求需要全面替换fastjson,所以写了这个jar包,jackson快速替换Fastjson

1. 第一步,添加依赖
   最新版本信息 https://github.com/zjb-it/jackson-replace-fastjson/packages/651197
```xml
<dependency>
            <groupId>com.github.zjb-it</groupId>
            <artifactId>jackson-replace-fastjson</artifactId>
            <version>${latest.version}</version>
        </dependency>
```
2. 第二步,全局替换包名

idea的快捷键 ctrl+shift+r

```java
import com.alibaba.fastjson.JSON
替换为
import jackson.replaces.fastjson.JSON

import com.alibaba.fastjson.JSONArray
替换为
import jackson.replaces.fastjson.JSONArray

import com.alibaba.fastjson.JSONObject
替换为
import jackson.replaces.fastjson.JSONObject
```
