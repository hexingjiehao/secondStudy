package algorithm.BigFileHeapSort.stock;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: algorithm.BigFileHeapSort
 * @className WareStockStoreAllocateVO
 * @description: TODO
 * @author: xj
 * @create: 2020-12-01 01:12:05
 **/
public class WareStockStoreAllocateVO {
    private Long wareInsideCode;
    private String madeNumber;
    private Long batchCode;
    private Long stallId;
    private Integer stallType;
    private String stallCode;
    private String stallName;
    private Double allocateQty;

    public WareStockStoreAllocateVO() {
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

    public Long getStallId() {
        return this.stallId;
    }

    public void setStallId(Long stallId) {
        this.stallId = stallId;
    }

    public Integer getStallType() {
        return this.stallType;
    }

    public void setStallType(Integer stallType) {
        this.stallType = stallType;
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

    public Double getAllocateQty() {
        return this.allocateQty;
    }

    public void setAllocateQty(Double allocateQty) {
        this.allocateQty = allocateQty;
    }

    public String toString() {
        return "WareStockStoreAllocateVO{wareInsideCode=" + this.wareInsideCode + ", madeNumber='" + this.madeNumber + '\'' + ", batchCode=" + this.batchCode + ", stallId=" + this.stallId + ", stallType=" + this.stallType + ", stallCode='" + this.stallCode + '\'' + ", stallName='" + this.stallName + '\'' + ", allocateQty=" + this.allocateQty + '}';
    }
}

