package algorithm.onlyId;

/**
 * 这个是通过机器时间的位运算来生成唯一id
 * 需要注意：有可能会人为的修改机器的时间位以前的时间
 */
public class IdWorker {
    private final long workerId;
    private final static long twepoch = 1361753741828L;
    private long sequence = 0L;
    //机器标识位数
    private final static long workerIdBits = 5L;
    //机器ID最大值
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    //毫秒内自增位
    private final static long sequenceBits = 10L;
    //机器ID偏左移位
    private final static long workerIdShift = sequenceBits;
    //时间毫秒左移
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    public IdWorker(final long workerId) {
        super();
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            //当前毫秒内，则+1
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                //当前毫秒内计数满了，则等待下一秒
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        //时间错误
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        //ID偏移组合生成最终的ID，并返回ID
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << workerIdShift) | (this.sequence);
        return nextId;
    }

    //等待下一个毫秒的到来
    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
