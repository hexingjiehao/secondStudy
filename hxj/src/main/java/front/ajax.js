/**
 * Created by xiongjie on 2018/11/14.
 */

//创建兼容的ajax对象，从高到低的创建，异常就创建低版本
function  createXHR(){

    try {
        //主流浏览器支持的ajax对象
        return new XMLHttpRequest();
    }catch(e) {

    }


    try{
        //快过时的浏览器支持的ajax对象
        return new ActiveXObject('Microsoft.XMLHTTP');
    }catch (e){

    }


    try{
        //过时浏览器支持的ajax对象
        return new ActiveXObject("Msxml3.XMLHTTP");
        // return new ActiveXObject("Msxml2.XMLHTTP");
    }catch(e){

    }

    //用户浏览器特殊
    alert("请更换主流浏览器");
}


function ajaxMethod(){
    xhr=createXHR();
    xhr.open("POST","http:www.baidu.com",true);
    xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xhr.send("name="+"xiongjie");
    xhr.abort();
}