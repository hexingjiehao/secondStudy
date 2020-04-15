//生成多个不同的随机数，范围是[min,max]
function generateRandom(count, min, max) {
    var arr = [];
    for (var i = 0; i < count; i++) {
        do {
            var flag = true;
            let item = parseInt(Math.random() * (max - min + 1) + min, 10);
            if (arr.indexOf(item) == -1) {
                arr.push(item);
                flag = false;
            }
        } while (flag);
    }
    return arr;
}

//每次刷新页面执行一次函数
function selectAndNext() {

    // 牛客网自动选择下一题
    var next = document.getElementById("next");

    //判断是正在做的试卷，还是已经做完了一套卷子后的页面
    if (!next) {
        //目前在做完一套的页面

        //点击再练习一套,页面按钮的倒数第二个
        var btnLisk = document.getElementsByClassName("btn");
        var length = btnLisk.length;
        var index = length - 2;
        btnLisk[index].click();

        //点击弹框中的确定按钮,添加两个按钮，又变成倒数第二个
        var confirmBtnLisk = document.getElementsByClassName("btn");
        var length2 = confirmBtnLisk.length;
        var index2 = length2 - 2;

        //睡眠5秒后操作
        setTimeout(function () {
            confirmBtnLisk[index2].click();
        }, 5000);

    } else {
        //模拟人的思考，每1题思考10-30秒时间，超过了就随便选
        var thinkTime = parseInt(Math.random() * (30 - 10 + 1) + 10, 10);

        //睡眠指定时间后选择答案
        setTimeout(function () {
            // 牛客网自动选择随机答案
            var inputList = document.getElementsByTagName("input");
            var choiceCount = inputList.length - 5;
            //生成[1,选项个数]之间的随机数
            var random = parseInt(Math.random() * (choiceCount - 1 + 1) + 1, 10);

            if (inputList[1].type == "checkbox") {
                //页面是多选框--选择多个答案
                var ansList = generateRandom(random, 1, choiceCount);
                var ansLength = ansList.length;
                for (var i = 0; i < ansLength; i++) {
                    inputList[ansList[i]].click();
                }
            } else {
                //页面是单选框--选择一个答案
                inputList[random].click();
            }

            //目前在做题中
            next.click();
        }, thinkTime * 1000);
    }
}

window.onload = selectAndNext;

//睡眠60秒后刷新当前页面
setTimeout(function () {
    location.reload();
}, 60000);


