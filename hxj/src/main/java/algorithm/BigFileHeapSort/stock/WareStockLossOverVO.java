package algorithm.BigFileHeapSort.stock;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: algorithm.BigFileHeapSort
 * @className WareStockLossOverVO
 * @author: xj
 * @create: 2020-12-01 01:12:44
 **/
public class WareStockLossOverVO {
    private Long groupId;
    private Long companyId;
    private Long businessId;
    private Long wareInsideCode;
    private String madeNumber;
    private Long batchCode;
    private Integer stallType;
    private Long stallId;
    private Integer lossOrOverType;
    private Double lossOrOverQty;

    public WareStockLossOverVO() {
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

    public Long getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getWareInsideCode() {
        return this.wareInsideCode;
    }

    public void setWareInsideCode(Long wareInsideCode) {
        this.wareInsideCode = wareInsideCode;
    }

    public String getMadeNumber() {
        return this.madeNumber;
    }

    public void setMadeNumber(String madeNumber) {
        this.madeNumber = madeNumber;
    }

    public Long getBatchCode() {
        return this.batchCode;
    }

    public void setBatchCode(Long batchCode) {
        this.batchCode = batchCode;
    }

    public Integer getStallType() {
        return this.stallType;
    }

    public void setStallType(Integer stallType) {
        this.stallType = stallType;
    }

    public Long getStallId() {
        return this.stallId;
    }

    public void setStallId(Long stallId) {
        this.stallId = stallId;
    }

    public Integer getLossOrOverType() {
        return this.lossOrOverType;
    }

    public void setLossOrOverType(Integer lossOrOverType) {
        this.lossOrOverType = lossOrOverType;
    }

    public Double getLossOrOverQty() {
        return this.lossOrOverQty;
    }

    public void setLossOrOverQty(Double lossOrOverQty) {
        this.lossOrOverQty = lossOrOverQty;
    }

    public String toString() {
        return "WareStockLossOverVO{groupId=" + this.groupId + ", companyId=" + this.companyId + ", businessId=" + this.businessId + ", wareInsideCode=" + this.wareInsideCode + ", madeNumber='" + this.madeNumber + '\'' + ", batchCode=" + this.batchCode + ", stallType=" + this.stallType + ", stallId=" + this.stallId + ", lossOrOverType=" + this.lossOrOverType + ", lossOrOverQty=" + this.lossOrOverQty + '}';
    }
}
