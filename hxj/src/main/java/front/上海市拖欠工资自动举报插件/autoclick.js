//自动填写举报信息
function tipOffs() {
    //睡眠5秒后操作----需要手动去打开页面，让数据透露出来
    setTimeout(function () {
        var coporationName="上海{...}信息技术有限公司";
        var coporationAddress="";
        var coporationPhone="";
        var question=coporationName+"拖欠员工工资,合同上写明每月5日发放上月的工资，到目前为止，该公司还未发放2020年1月，2月的工资。拖欠1月份的工资已经1个多月";

        //自动填写input内容
        var inputList = document.getElementsByTagName("input");

        var name=inputList[0];
        name.value=coporationName;

        var address=inputList[1];
        address.value=coporationAddress;

        var phone=inputList[2];
        phone.value=coporationPhone;

        //自动选择下拉框的选项
        document.getElementsByName("ldjcjb_dwscqx_id")[0].value=110;
        document.getElementsByName("ldjcjb_jjlx_id")[0].value=107;

        //自动填写举报内容
        document.getElementsByName("ldjcjb_jbnrms")[0].value=question;
    }, 5000);
}

window.onload = tipOffs;


