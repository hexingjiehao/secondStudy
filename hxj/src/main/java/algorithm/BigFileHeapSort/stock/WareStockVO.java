package algorithm.BigFileHeapSort.stock;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: algorithm.BigFileHeapSort
 * @className WareStockVO
 * @description: TODO
 * @author: xj
 * @create: 2020-12-01 01:11:27
 **/
public class WareStockVO {
    private Long groupId;
    private Long companyId;
    private Long businessIdSend;
    private Long businessIdReceive;
    private Long wareInsideCode;
    private Long stallId;
    private String stallCode;
    private String stallName;
    private Integer stallType;
    private String madeNumber;
    private Long batchCode;
    private Double applyQty;
    private Double distributionQty;
    private Double sumDistributionQty;
    private Double outRecheckQty;
    private Double receiveQty;
    private Double receiveRejectQty;
    private Double inspectQty;
    private Double inspectRejectQty;
    private Double inStockQty;
    private Double inStockRejectQty;
    private Double returnWarehouseQty;

    public WareStockVO() {
    }

    public Long getBusinessIdSend() {
        return this.businessIdSend;
    }

    public void setBusinessIdSend(Long businessIdSend) {
        this.businessIdSend = businessIdSend;
    }

    public Long getBusinessIdReceive() {
        return this.businessIdReceive;
    }

    public void setBusinessIdReceive(Long businessIdReceive) {
        this.businessIdReceive = businessIdReceive;
    }

    public Long getWareInsideCode() {
        return this.wareInsideCode;
    }

    public void setWareInsideCode(Long wareInsideCode) {
        this.wareInsideCode = wareInsideCode;
    }

    public Long getStallId() {
        return this.stallId;
    }

    public void setStallId(Long stallId) {
        this.stallId = stallId;
    }

    public String getStallCode() {
        return this.stallCode;
    }

    public void setStallCode(String stallCode) {
        this.stallCode = stallCode;
    }

    public String getStallName() {
        return this.stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public Integer getStallType() {
        return this.stallType;
    }

    public void setStallType(Integer stallType) {
        this.stallType = stallType;
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

    public Double getApplyQty() {
        return this.applyQty;
    }

    public void setApplyQty(Double applyQty) {
        this.applyQty = applyQty;
    }

    public Double getDistributionQty() {
        return this.distributionQty;
    }

    public void setDistributionQty(Double distributionQty) {
        this.distributionQty = distributionQty;
    }

    public Double getSumDistributionQty() {
        return this.sumDistributionQty;
    }

    public void setSumDistributionQty(Double sumDistributionQty) {
        this.sumDistributionQty = sumDistributionQty;
    }

    public Double getOutRecheckQty() {
        return this.outRecheckQty;
    }

    public void setOutRecheckQty(Double outRecheckQty) {
        this.outRecheckQty = outRecheckQty;
    }

    public Double getReceiveQty() {
        return this.receiveQty;
    }

    public void setReceiveQty(Double receiveQty) {
        this.receiveQty = receiveQty;
    }

    public Double getReceiveRejectQty() {
        return this.receiveRejectQty;
    }

    public void setReceiveRejectQty(Double receiveRejectQty) {
        this.receiveRejectQty = receiveRejectQty;
    }

    public Double getInspectQty() {
        return this.inspectQty;
    }

    public void setInspectQty(Double inspectQty) {
        this.inspectQty = inspectQty;
    }

    public Double getInspectRejectQty() {
        return this.inspectRejectQty;
    }

    public void setInspectRejectQty(Double inspectRejectQty) {
        this.inspectRejectQty = inspectRejectQty;
    }

    public Double getInStockQty() {
        return this.inStockQty;
    }

    public void setInStockQty(Double inStockQty) {
        this.inStockQty = inStockQty;
    }

    public Double getInStockRejectQty() {
        return this.inStockRejectQty;
    }

    public void setInStockRejectQty(Double inStockRejectQty) {
        this.inStockRejectQty = inStockRejectQty;
    }

    public Double getReturnWarehouseQty() {
        return this.returnWarehouseQty;
    }

    public void setReturnWarehouseQty(Double returnWarehouseQty) {
        this.returnWarehouseQty = returnWarehouseQty;
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

    public String toString() {
        return "WareStockVO{groupId=" + this.groupId + ", companyId=" + this.companyId + ", businessIdSend=" + this.businessIdSend + ", businessIdReceive=" + this.businessIdReceive + ", wareInsideCode=" + this.wareInsideCode + ", stallId=" + this.stallId + ", stallCode='" + this.stallCode + '\'' + ", stallName='" + this.stallName + '\'' + ", stallType=" + this.stallType + ", madeNumber='" + this.madeNumber + '\'' + ", batchCode=" + this.batchCode + ", applyQty=" + this.applyQty + ", distributionQty=" + this.distributionQty + ", sumDistributionQty=" + this.sumDistributionQty + ", outRecheckQty=" + this.outRecheckQty + ", receiveQty=" + this.receiveQty + ", receiveRejectQty=" + this.receiveRejectQty + ", inspectQty=" + this.inspectQty + ", inspectRejectQty=" + this.inspectRejectQty + ", inStockQty=" + this.inStockQty + ", inStockRejectQty=" + this.inStockRejectQty + ", returnWarehouseQty=" + this.returnWarehouseQty + '}';
    }
}
