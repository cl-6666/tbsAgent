# 说明  
>  项目需要预览pdf、word等文件，但是用户不一定安装了wps这样的软件，因此需要在项目里支持查看这些文件。笔者本身是不想使用webview的，因此尝试了很多原生的，但是对于doc文件基本没有什么好的方式，到最后还是用了腾讯的tbs文件游览服务。接入tbs坑较多，主要是x5内核的下载和各种配置问题，这里进行了一个封装，方便开发者使用，强烈建议以依赖的方式导入，这样避免代码同步。

# 效果图  

版本更新历史：  
[![](https://jitpack.io/v/cl-6666/tbsAgent.svg)](https://jitpack.io/#cl-6666/tbsAgent)

- v1.0.0：(2022年12月06日)
  - 第一代版本sdk提交  
  - 支持wps、word、tts、图片等等浏览
  
------  
  `V1.0.0`功能列表 | 是否支持 |
--------|------|
是否支持标题自定义 | 支持  |
是否支持pdf | 支持  |
是否支持word | 支持  |
是否支持图片 | 支持 |
是否支持ppt | 支持  |
是否支持txt | 支持  |

------  
# 屏幕截图    

<img src="https://github.com/cl-6666/tbsAgent/blob/master/img/img1.png" width=280/><img src="https://github.com/cl-6666/tbsAgent/blob/master/img/img2.png" width=280/> 

------  
# 下载体验  
<img src="https://github.com/cl-6666/tbsAgent/blob/master/img/QRCode_420.png"><img/><br/>
因为图方便，用手机浏览器扫码可下载


# 项目依赖
``` 
 repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }

    }
```

Step 2. Add the dependency

``` 
dependencies {
  //依赖添加
  implementation 'com.github.cl-6666:tbsAgent:v1.0.0'
}
```  

# 使用方式  

```  
   //初始化tbs，默认标题是返回
   TbsUtils.init(this,"我是返回");
   
   //需要内容使用，filePath为路径比如：/data/user/0/...../files/TestDoc.doc
  TbsUtils.loadFileType(this,filePath,"我是标题");
  
   //无需标题
  TbsUtils.loadFileType(this,filePath);
```  

## 作者博客地址    
博客地址：https://blog.csdn.net/a214024475?ref=toolbar  

