DianDangHang

一、说明：
    由于node_modules、target、dist、rev太大或者没有必要，没有上传，所以需要clone后进行一些操作。

二、所需环境：
    安装Note、安装cnpm（若没有cnpm，将下面命令中的cnpm换成npm）

三、使用步骤：
    1.用Git进行Clone:
        git clone https://github.com/ALISURE/DianDangHang.git

    2.设置gulp
        命令行中输入：
            cnpm install --save-dev gulp

    3.安装gulp插件
        命令行中输入：
            cnpm install gulp-clean-css gulp-uglify gulp-rename gulp-rev gulp-clean gulp-rev-collector gulp-file-include gulp-autoprefixer --save-dev

四、运行步骤：
    1.运行gulp的default任务
        生成dist和rev文件夹

    2.前端代码就在dist中,直接使用即可

    3.OK
