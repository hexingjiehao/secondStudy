package algorithm.BigFileHeapSort.stock;

import java.util.Date;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: algorithm.BigFileHeapSort.stock
 * @className EntrepotMergeFreeExpectedQtyLog
 * @description: TODO
 * @author: xj
 * @create: 2020-12-01 01:38:19
 **/
public class EntrepotMergeFreeExpectedQtyLog {

    private Long id;

    private Long groupId;

    private Long companyId;

    private String mergeBillNumber;

    private Integer billType;

    private Integer billStatus;

    private Long businessIdSend;

    private Long businessIdReceive;

    private Long wareInsideCode;

    private Double mergeApplyQty;

    private Long billLifeId;

    private String operateTimeMillisStr;

    private Long billOperateUser;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public EntrepotMergeFreeExpectedQtyLog setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getGroupId() {
        return groupId;
    }

    public EntrepotMergeFreeExpectedQtyLog setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public EntrepotMergeFreeExpectedQtyLog setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getMergeBillNumber() {
        return mergeBillNumber;
    }

    public EntrepotMergeFreeExpectedQtyLog setMergeBillNumber(String mergeBillNumber) {
        this.mergeBillNumber = mergeBillNumber;
        return this;
    }

    public Integer getBillType() {
        return billType;
    }

    public EntrepotMergeFreeExpectedQtyLog setBillType(Integer billType) {
        this.billType = billType;
        return this;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public EntrepotMergeFreeExpectedQtyLog setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
        return this;
    }

    public Long getBusinessIdSend() {
        return businessIdSend;
    }

    public EntrepotMergeFreeExpectedQtyLog setBusinessIdSend(Long businessIdSend) {
        this.businessIdSend = businessIdSend;
        return this;
    }

    public Long getBusinessIdReceive() {
        return businessIdReceive;
    }

    public EntrepotMergeFreeExpectedQtyLog setBusinessIdReceive(Long businessIdReceive) {
        this.businessIdReceive = businessIdReceive;
        return this;
    }

    public Long getWareInsideCode() {
        return wareInsideCode;
    }

    public EntrepotMergeFreeExpectedQtyLog setWareInsideCode(Long wareInsideCode) {
        this.wareInsideCode = wareInsideCode;
        return this;
    }

    public Double getMergeApplyQty() {
        return mergeApplyQty;
    }

    public EntrepotMergeFreeExpectedQtyLog setMergeApplyQty(Double mergeApplyQty) {
        this.mergeApplyQty = mergeApplyQty;
        return this;
    }

    public Long getBillLifeId() {
        return billLifeId;
    }

    public EntrepotMergeFreeExpectedQtyLog setBillLifeId(Long billLifeId) {
        this.billLifeId = billLifeId;
        return this;
    }

    public String getOperateTimeMillisStr() {
        return operateTimeMillisStr;
    }

    public EntrepotMergeFreeExpectedQtyLog setOperateTimeMillisStr(String operateTimeMillisStr) {
        this.operateTimeMillisStr = operateTimeMillisStr;
        return this;
    }

    public Long getBillOperateUser() {
        return billOperateUser;
    }

    public EntrepotMergeFreeExpectedQtyLog setBillOperateUser(Long billOperateUser) {
        this.billOperateUser = billOperateUser;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public EntrepotMergeFreeExpectedQtyLog setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public EntrepotMergeFreeExpectedQtyLog setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }
}
