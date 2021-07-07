# UmengThirdParties
友盟第三方登录基础包，主要目的是如果手动去添加友盟基础包，有很多资源文件需要拷贝，这样会造成自己项目很乱，还容易漏掉，目前只是导入基础包，平台包全部都没有导入，这个根据项目需要自行导入。环境配置根据实际使用进行配置。

# 使用说明：
## 第一步：
### 在 Project 的 build.gradle 文件中 添加仓库支持
```groovy
allprojects {
    repositories {
        
        maven { url 'https://jitpack.io' }
    }
} 
```
## 第二步：
### 在需要引用的项目的 build.gradle 添加依赖
[see javadoc](https://javadoc.jitpack.io/com/github/itzheng/UmengThirdParties/latest/javadoc/index.html)
[![](https://jitpack.io/v/itzheng/UmengThirdParties.svg)](https://jitpack.io/#itzheng/UmengThirdParties)
```groovy
dependencies {
        
       implementation 'com.github.itzheng:UmengThirdParties:6.9.3.0.1'
}
```

