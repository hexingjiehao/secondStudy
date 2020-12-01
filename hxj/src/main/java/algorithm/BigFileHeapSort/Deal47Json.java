package algorithm.BigFileHeapSort;

import algorithm.BigFileHeapSort.stock.EntrepotMergeFreeExpectedQtyLog;
import algorithm.BigFileHeapSort.stock.WareHouseAndStoreVO;
import algorithm.BigFileHeapSort.stock.WareStockVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.joda.time.DateTime;
import tool.jsonSchema.JsonUtil;

import java.io.*;
import java.util.*;

/**
 * @param
 * @Description 处理合单释放的47个消息体
 * @status done
 * @methodName
 * @return
 * @Author xj
 * @Date 2020/12/1 0:55
 **/
public class Deal47Json {

    public static void main(String[] args) {
        //1.读取文件，将其存为List<String>
        File file = new File("msg.txt");
        if (file == null) {
            System.out.println("文件不存在");
            return;
        }
        System.out.println("文件存在");

        //2.逐行遍历文件数据
        BufferedReader brDb = null;
        String dbLine = "NAN";
        List<String> list = new ArrayList<>();
        try {
            brDb = new BufferedReader(new FileReader(file));
            while ((dbLine = brDb.readLine()) != null) {
                //统计结果，写入文件
                list.add(dbLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解析文件数据:");
        System.out.println(JsonUtil.toJsonString(list));

        //2.循环list,每个item转化为StockVO
        List<WareHouseAndStoreVO> stockList = new ArrayList<>();
        int totalCount = 0;
        List<Integer> countList = new ArrayList<>();
        for (String value : list) {
            System.out.println("明细数据为:");
            System.out.println(value);

            //3.将字符串转化为StockVO
            WareHouseAndStoreVO wareHouseAndStoreVO = JSON.parseObject(value, new TypeReference<WareHouseAndStoreVO>() {
            });

            System.out.println("转化后的实体：");
            System.out.println(JsonUtil.toJsonString(wareHouseAndStoreVO));
            int count = wareHouseAndStoreVO.getWareStockVOList().size();
            System.out.println("每条库存实体的明细为: " + count);
            totalCount += count;
            countList.add(count);

            stockList.add(wareHouseAndStoreVO);
        }
        System.out.println("全部转化为stockVO后的list：");
        System.out.println(JsonUtil.toJsonString(stockList));
        System.out.println("没去重的合单明细条数为：" + totalCount);
        System.out.println("没去重的合单明细List为：" + countList);

        //3.将StockVo的明细进行去重--维度为接收业务机构+商品自编码
        for (WareHouseAndStoreVO vo : stockList) {
            List<WareStockVO> wareStockVOS = vo.getWareStockVOList();
            int size = wareStockVOS.size();

            List<WareStockVO> wareStockVOSNew = new ArrayList<>();
            Map<String, WareStockVO> map = new HashMap<>();
            for (int i = 0; i < size; i++) {
                WareStockVO ware = wareStockVOS.get(i);
                String key = ware.getBusinessIdReceive() + "#" + ware.getWareInsideCode();
                if (map.get(key) != null) {
                    continue;
                }
                //不重复的数据，重新放入list种，并绑定主表VO
                map.put(key, ware);
                wareStockVOSNew.add(ware);
            }
            //重新绑定主表和明细表关系
            vo.setWareStockVOList(wareStockVOSNew);
        }

        //4.将去重后端额数据重新打印到控制台
        System.out.println("去重后的数据为:");
        System.out.println(JsonUtil.toJsonString(stockList));
        int totalSize = 0;
        List<Integer> sizeList = new ArrayList<>();
        for (WareHouseAndStoreVO vo : stockList) {
            List<WareStockVO> wareStockVOS = vo.getWareStockVOList();
            int size = wareStockVOS.size();

            System.out.println("去重后的明细条数为: " + size);
            totalSize += size;
            sizeList.add(size);
        }
        System.out.println("去重后的合单明细条数为：" + totalSize);
        System.out.println("去重后的合单明细List为：" + sizeList);

        //5.将最后转化为的stockVO封装成合单日志表的记录: 总共应该生成1847条合单日志数据
        List<EntrepotMergeFreeExpectedQtyLog> mergeLogList = new ArrayList<>();
        for (WareHouseAndStoreVO vo : stockList) {
            List<WareStockVO> wareStockVOS = vo.getWareStockVOList();
            int size = wareStockVOS.size();

            for (int i = 0; i < size; i++) {
                WareStockVO ware = wareStockVOS.get(i);

                //创建合单日志记录，并赋值
                EntrepotMergeFreeExpectedQtyLog mergeLog = new EntrepotMergeFreeExpectedQtyLog();
                mergeLog.setGroupId(vo.getGroupId())
                        .setCompanyId(vo.getCompanyId())
                        .setMergeBillNumber(vo.getBillNumber())
                        .setBillType(vo.getBillType())
                        .setBillStatus(vo.getBusinessType())
                        .setBusinessIdSend(100120641L)
                        .setBusinessIdReceive(ware.getBusinessIdReceive())
                        .setWareInsideCode(ware.getWareInsideCode())
                        .setMergeApplyQty(ware.getApplyQty())
                        .setBillLifeId(vo.getBillLifeId())
                        .setOperateTimeMillisStr(vo.getOperateTimeMillisStr())
                        .setBillOperateUser(vo.getBillOperateUser());

                mergeLogList.add(mergeLog);
            }
        }

        //6.将该合单记录打印出来--应该为1847条
        System.out.println("最终转化为合单日志记录为：");
        System.out.println(JsonUtil.toJsonString(mergeLogList));
        System.out.println("最终转化为合单日志记录条数为：");
        System.out.println(mergeLogList.size());

        //7.将合单日志表--封装成1847条insert语句。然后再开发库批量执行。暂时用一个表进行处理，明天西凤
        List<String> sqlList = new ArrayList<>();
        for (EntrepotMergeFreeExpectedQtyLog mergeLog : mergeLogList) {
            String sql = String.format(
                    "INSERT INTO h3_warehouse.t_warehouse_entrepot_merge_free_expected_qty_log_back " +
                            "(group_id, company_id, merge_bill_number, bill_type, bill_status, business_id_send, business_id_receive, ware_inside_code, " +
                            "merge_apply_qty, bill_life_id, operate_time_millis_str, bill_operate_user) " +
                            "VALUES " +
                            "(%d, %d, %s, %d, %d, %d, %d, %d, %f, %d, %s, %d);",

                    mergeLog.getGroupId(),
                    mergeLog.getCompanyId(),
                    mergeLog.getMergeBillNumber(),
                    mergeLog.getBillType(),
                    mergeLog.getBillStatus(),
                    mergeLog.getBusinessIdSend(),
                    mergeLog.getBusinessIdReceive(),
                    mergeLog.getWareInsideCode(),
                    mergeLog.getMergeApplyQty(),
                    mergeLog.getBillLifeId(),
                    mergeLog.getOperateTimeMillisStr(),
                    mergeLog.getBillOperateUser()
                    );

            sqlList.add(sql);
            System.out.println(sql);
        }
        System.out.println("最终插入合单日志备份表的记录条数为："+sqlList.size());
        System.out.println("最终插入合单日志备份表的记录为：");
        System.out.println(JsonUtil.toJsonString(sqlList));

        //8.将1847条合单日志记录写入文件
        File logFile = null;
        BufferedWriter bw = null;

        try {
            logFile = new File("mergeLog.txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            bw = new BufferedWriter(new FileWriter(logFile, true));
            for (String line : sqlList) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(logFile==null){
            System.out.println("创建的日志文件不存在");
        }else{
            System.out.println("创建的日志文件存在");
        }
    }
}
