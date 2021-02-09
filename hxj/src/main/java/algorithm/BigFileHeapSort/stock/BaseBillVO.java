package algorithm.BigFileHeapSort.stock;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: algorithm.BigFileHeapSort
 * @className BaseBillVO
 * @author: xj
 * @create: 2020-12-01 01:10:44
 **/
public class BaseBillVO {
    private Long groupId;
    private Long companyId;
    private String billNumber;
    private Integer billType;
    private Long businessIdSend;
    private Long businessId;
    private Integer businessType;
    private String operateTimeMillisStr;
    private Long billLifeId;
    private Long billOperateUser = 0L;
    private Long billModifyUser = 0L;
    private Long businessObjectId = 0L;

    public BaseBillVO() {
    }

    public Long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getBillNumber() {
        return this.billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Integer getBillType() {
        return this.billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public Long getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getOperateTimeMillisStr() {
        return this.operateTimeMillisStr;
    }

    public void setOperateTimeMillisStr(String operateTimeMillisStr) {
        this.operateTimeMillisStr = operateTimeMillisStr;
    }

    public Long getBusinessIdSend() {
        return this.businessIdSend;
    }

    public void setBusinessIdSend(Long businessIdSend) {
        this.businessIdSend = businessIdSend;
    }

    public Integer getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Long getBillLifeId() {
        return this.billLifeId;
    }

    public void setBillLifeId(Long billLifeId) {
        this.billLifeId = billLifeId;
    }

    public Long getBillOperateUser() {
        return this.billOperateUser;
    }

    public void setBillOperateUser(Long billOperateUser) {
        this.billOperateUser = billOperateUser;
    }

    public Long getBusinessObjectId() {
        return this.businessObjectId;
    }

    public void setBusinessObjectId(Long businessObjectId) {
        this.businessObjectId = businessObjectId;
    }

    public Long getBillModifyUser() {
        return this.billModifyUser;
    }

    public void setBillModifyUser(Long billModifyUser) {
        this.billModifyUser = billModifyUser;
    }
}
