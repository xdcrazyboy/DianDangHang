/**
 * Created by ALISURE on 2017/5/21.
 */
'use strict';

var config = require("./gulpfile.config.js");
var gulp = require("gulp"),
    css = require("gulp-clean-css"),
    js = require("gulp-uglify"),
    rename = require("gulp-rename"),
    rev = require("gulp-rev"),
    clean = require("gulp-clean"),
    rev_collector = require("gulp-rev-collector"),
    file_include = require("gulp-file-include"),
    auto_prefixer = require("gulp-autoprefixer");

/*移动其他资源*/
gulp.task('move-resources', function () {
    gulp.src([config.src.common]).pipe(gulp.dest(config.dist.common));
    gulp.src([config.src.resources]).pipe(gulp.dest(config.dist.resources));
    gulp.src([config.src.wechat.img]).pipe(gulp.dest(config.dist.wechat.img));
});

/*清除文件*/
gulp.task("clean", function () {
    return gulp.src([config.dist.wechat.html, config.dist.wechat.css, config.dist.wechat.js]).pipe(clean());
});

/*HTML合并*/
gulp.task("html", ['clean'], function () {
    return gulp.src(config.src.wechat.html).pipe(file_include()).pipe(gulp.dest(config.dist.wechat.html));
});

/*css前缀、压缩、MD5、重命名*/
gulp.task('mini-css-and-md5', ["html"], function () {
    return gulp.src(config.src.wechat.css).pipe(auto_prefixer({
        browsers: ['last 2 versions', 'Android >= 4.0', ">= 1%"],
        cascade: true, //是否美化属性值 默认：true
        remove:true //是否去掉不必要的前缀 默认：true
    })).pipe(css()).pipe(rev()).pipe(gulp.dest(config.dist.wechat.css))
        .pipe(rev.manifest({path: config.rev.css.wechat})).pipe(gulp.dest(config.rev.dir));
});

/*js压缩、MD5、重命名*/
gulp.task('mini-js-and-md5', ["mini-css-and-md5"], function () {
    return gulp.src(config.src.wechat.js).pipe(js()).pipe(rev())
        .pipe(gulp.dest(config.dist.wechat.js))
        .pipe(rev.manifest({path: config.rev.js.wechat})).pipe(gulp.dest(config.rev.dir));
});

/* 替换名称 */
gulp.task('rev',['mini-js-and-md5'], function () {
    return gulp.src([config.rev.json, config.dist.wechat.html_no_dir])
        .pipe(rev_collector()).pipe(gulp.dest(config.dist.wechat.html));
});

/* 在开发环境下，移动js css，即未经过压缩和重命名。 */
gulp.task('move-js-css', ["html"], function () {
    gulp.src([config.src.wechat.css]).pipe(gulp.dest(config.dist.wechat.css));
    gulp.src([config.src.wechat.js]).pipe(gulp.dest(config.dist.wechat.js));
});

/*监听文件改变*/
gulp.task("watch", function () {
    gulp.watch(config.src.web, ['move-js-css']);
});

/* 开发时的默认任务 */
gulp.task("default", ["move-js-css", "watch", "move-resources"]);


/* 上线的时候运行此gulp */
gulp.task("product", ["rev", "move-resources"]);
