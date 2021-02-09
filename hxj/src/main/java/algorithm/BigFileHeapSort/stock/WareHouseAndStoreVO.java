package algorithm.BigFileHeapSort.stock;

import java.util.List;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: algorithm.BigFileHeapSort
 * @className WareHouseAndStoreVO
 * @author: xj
 * @create: 2020-12-01 01:10:14
 **/
public class WareHouseAndStoreVO extends BaseBillVO {
    private Integer distributionBillType;
    private List<WareStockVO> wareStockVOList;
    private String storeTransferAutoFlag;
    private String initialAutoFlag;
    private List<WareStockStoreAllocateVO> wareStockStoreAllocateVOList;
    private List<WareStockLossOverVO> wareStockLossOverVOList;

    public WareHouseAndStoreVO() {
    }

    public Integer getDistributionBillType() {
        return this.distributionBillType;
    }

    public void setDistributionBillType(Integer distributionBillType) {
        this.distributionBillType = distributionBillType;
    }

    public List<WareStockVO> getWareStockVOList() {
        return this.wareStockVOList;
    }

    public void setWareStockVOList(List<WareStockVO> wareStockVOList) {
        this.wareStockVOList = wareStockVOList;
    }

    public List<WareStockStoreAllocateVO> getWareStockStoreAllocateVOList() {
        return this.wareStockStoreAllocateVOList;
    }

    public void setWareStockStoreAllocateVOList(List<WareStockStoreAllocateVO> wareStockStoreAllocateVOList) {
        this.wareStockStoreAllocateVOList = wareStockStoreAllocateVOList;
    }

    public List<WareStockLossOverVO> getWareStockLossOverVOList() {
        return this.wareStockLossOverVOList;
    }

    public void setWareStockLossOverVOList(List<WareStockLossOverVO> wareStockLossOverVOList) {
        this.wareStockLossOverVOList = wareStockLossOverVOList;
    }

    public String getStoreTransferAutoFlag() {
        return this.storeTransferAutoFlag;
    }

    public void setStoreTransferAutoFlag(String storeTransferAutoFlag) {
        this.storeTransferAutoFlag = storeTransferAutoFlag;
    }

    public String getInitialAutoFlag() {
        return this.initialAutoFlag;
    }

    public void setInitialAutoFlag(String initialAutoFlag) {
        this.initialAutoFlag = initialAutoFlag;
    }
}

