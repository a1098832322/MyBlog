# 开源轻博客系统 
======================================
## V1.0 - Release
* 基于lenve的开源博客系统后续修改开发。
*** lenve项目地址:https://github.com/lenve/JavaEETest/tree/master/MyBlog
* 修改原editor.md编辑器为UEditor富文本编辑器（因为原编辑器在使用HTML格式显示内容时会显得非常操蛋，所以就把它给换了）
* 修改若干页面显示BUG
* 全局使用软删除（即：内容本身还在，但是被标记为删除）
* 修正查询逻辑，使其更贴近于实际生产环境
* 新增多用户/管理员架构，使系统能够资瓷多用户使用。
* ！！暂未提供新用户注册功能（因为我自己用用不到注册功能。。。。），将会在后续版本中考虑加上去嗯~


## 部署说明
* 下载项目源码，然后使用eclipse或者idea导入本maven项目
* 创建一个新数据库，并导入: src\main\resources\blog.sql  文件
* 修改： src\main\java\org\sang\config\MyMVCConfig.java  内的数据库配置为你自己的
* 配置nginx，映射出本地存放上传文件的文件夹
* 编辑： src\main\resources\config.json 文件，将其中"basePath"、"imageUrlPrefix"和"imagePathFormat"属性配置为你自己的。（详细UEditor配置见UEidtor官网）
* 最后发布项目（建议使用Tomcat部署后再用nginx反向代理Tomcat）
* 使用本项目URL访问博客主页
* 后台管理及博客发布页面为：http://你的项目URL/manager
*** 默认管理员账号为：admin，密码为：123 。管理员可管理所有用户发布的博客文章，一般用户仅能修改自己发布的文章。