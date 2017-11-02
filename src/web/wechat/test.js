/**
 * Created by ALISURE on 2017/9/12.
 */

// var result = (function (){
//     var result = [];
//     for(var i=0;i<5;i++)
//         result[i] = function(){return i;}
//     return result;
// })();
// for(var j=0;j<5;j++) {
//     console.log(result[j]());
// }
//
//
// var result2 = (function (){
//     var result = [];
//     for(var i=0;i<5;i++){
//         result[i] = function(num){
//             return function () {return num;};
//         }(i);
//     }
//     return result;
// })();
// for(var j=0;j<5;j++)console.log(result2[j]());

// console.log(typeof null); // object
// var what;
// console.log(typeof what); // undefined
// var what = undefined;
// console.log(typeof what); // undefined
// console.log(NaN == NaN); //false


function deal_time(time) {
    if(time.length != 19){
        return "";
    }
    var result = {
        top_info: "",
        bottom_info: ""
    };

    var date = new Date(Date.now());
    var now_year = date.getFullYear();
    var now_month = parseInt(date.getMonth()) + 1;
    var now_day = parseInt(date.getDay()) + 1;

    var money_year = time.substr(0, 4);
    var money_month = time.substr(5, 2);
    var money_day = time.substr(8, 2);

    if(now_year == money_year){ /*当年*/
        if(now_month == money_month){ /*当月*/
            if(now_day == money_day){ /*当天*/
                result.top_info = "今天";
                result.bottom_info = time.substr(11, 8);
            }else{
                result.top_info = "本月";
                result.bottom_info = time.substr(5, 11);
            }
        }else{
            result.top_info = (money_month >= 10 ? money_month : money_month.substr(1, 1)) + "月";
            result.bottom_info = time.substr(5, 11);
        }
    }else{
        result.top_info = money_year + "年";
        result.bottom_info = time.substr(5, 11);
    }
    return result;
}
console.log(deal_time("2017-10-01 21:23:34"));