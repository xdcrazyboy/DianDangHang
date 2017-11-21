var checkNum = 0; //该变量是记录当前选择的评分
var time = 0; //该变量是统计用户评价的次数，这个是我的业务要求统计的（改变评分不超过三次），可以忽略

/*over()是鼠标移过事件的处理方法*/
function over(param) {
    if (param == 1) {
        $("#star1").attr("src", "../img/personCenter/personCenter/star_red.png"); //第一颗星星亮起来，下面以此类推
        $("#message").html("很差"); //设置提示语，下面以此类推
    } else if (param == 2) {
        $("#star1").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star2").attr("src", "../../img/personCenter/personCenter/star_red.png");
        $("#message").html("比较差");
    } else if (param == 3) {
        $("#star1").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star2").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star3").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#message").html("一般");
    } else if (param == 4) {
        $("#star1").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star2").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star3").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star4").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#message").html("比较好");
    } else if (param == 5) {
        $("#star1").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star2").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star3").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star4").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star5").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#message").html("很好");
    }
}
/*out 方法是鼠标移除事件的处理方法，当鼠标移出时，恢复到我的打分情况*/
function out() {
    if (checkNum == 1) { //打分是1，设置第一颗星星亮，其他星星暗，其他情况以此类推
        $("#star1").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star2").attr("src", "../img/personCenter/personCenter/star.png");
        $("#star3").attr("src", "../img/personCenter/personCenter/star.png");
        $("#star4").attr("src", "../img/personCenter/personCenter/star.png");
        $("#star5").attr("src", "../img/personCenter/personCenter/star.png");
        $("#message").html("");
    } else if (checkNum == 2) {
        $("#star1").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star2").attr("src", "../img/personCenter/personCenter/star_red.png");
        $("#star3").attr("src", "../img/personCenter/personCenter/star.png");
        $("#star4").attr("src", "../img/personCenter/personCenter/star.png");
        $("#star5").attr("src", "../img/personCenter/personCenter/star.png");
        $("#message").html("");
    } else if (checkNum == 3) {
        $("#star1").attr("src", "../../img/personCenter/personCenter/star_red.png");
        $("#star2").attr("src", "../../img/personCenter/personCenter/star_red.png");
        $("#star3").attr("src", "../../img/personCenter/personCenter/star_red.png");
        $("#star4").attr("src", "../../img/personCenter/personCenter/star.png");
        $("#star5").attr("src", "../img/personCenter/star.png");
        $("#message").html("");
    } else if (checkNum == 4) {
        $("#star1").attr("src", "../img/personCenter/star_red.png");
        $("#star2").attr("src", "../img/personCenter/star_red.png");
        $("#star3").attr("src", "../img/personCenter/star_red.png");
        $("#star4").attr("src", "../img/personCenter/star_red.png");
        $("#star5").attr("src", "../img/personCenter/star.png");
        $("#message").html("");
    } else if (checkNum == 5) {
        $("#star1").attr("src", "../img/personCenter/star_red.png");
        $("#star2").attr("src", "../img/personCenter/star_red.png");
        $("#star3").attr("src", "../img/personCenter/star_red.png");
        $("#star4").attr("src", "../img/personCenter/star_red.png");
        $("#star5").attr("src", "../img/personCenter/star_red.png");
        $("#message").html("");
    } else if (checkNum == 0) {
        $("#star1").attr("src", "../img/personCenter/star.png");
        $("#star2").attr("src", "../img/personCenter/star.png");
        $("#star3").attr("src", "../img/personCenter/star.png");
        $("#star4").attr("src", "../img/personCenter/star.png");
        $("#star5").attr("src", "../img/personCenter/star.png");
        $("#message").html("");
    }
}
/*click()点击事件处理，记录打分*/
function click(param) {
    time++; //记录打分次数
    checkNum = param; //记录当前打分
    out(); //设置星星数
}