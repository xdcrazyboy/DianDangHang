var src_dir = "src/main/webapp/web";
var dist_dir = "dist/web";

var config = {
    "dist": {
        "web": dist_dir,
        "common": dist_dir + "/common",
        "resources": dist_dir + "/resources",
        "wechat": {
            "css": dist_dir + "/wechat/css",
            "img": dist_dir + "/wechat/img",
            "js": dist_dir + "/wechat/js",
            "html": dist_dir + "/wechat/view",
            "html_no_dir": dist_dir + "/wechat/view/*.html"
        }
    },
    "src": {
        "web": src_dir + "/**/*",
        "common": src_dir + "/common/**/*",
        "resources": src_dir + "/resources/**/*",
        "wechat": {
            "css": src_dir + "/wechat/css/**/*.css",
            "img": src_dir + "/wechat/img/**/*",
            "js": src_dir + "/wechat/js/**/*.js",
            "html": src_dir + "/wechat/view/*.html"
        }
    },
    "rev": {
        "dir": "rev",
        "json": "./rev/*.json",
        "css": {
            "wechat":"wechat-css.json",
        },
        "js": {
            "wechat":"wechat-js.json",
        }
    }
};

module.exports = config;
