//自动填写举报信息
function tipOffs() {

    //自动填写input内容
    document.getElementsByName("ldjcjb_dwqc")[0].value = "上海{...}信息技术有限公司";
    document.getElementsByName("ldjcjb_dwlxdz")[0].value = "address";
    document.getElementsByName("ldjcjb_dwdh")[0].value = "phone";

    //自动选择下拉框的选项
    document.getElementsByName("ldjcjb_dwscqx_id")[0].value = 110;
    document.getElementsByName("ldjcjb_jjlx_id")[0].value = 107;

    //自动填写举报内容
    document.getElementsByName("ldjcjb_jbnrms")[0].value = "拖欠员工工资,合同上写明每月5日发放上月的工资，到目前为止，该公司还未发放2020年1月，2月的工资。拖欠1月份的工资已经1个多月";

}

window.onload = tipOffs;


