package algorithm.onlyId;

public class IdUtil {

    private static IdWorker idWorker = new IdWorker(1);

    private IdUtil() {}

    public static long generateId() {
        return idWorker.nextId();
    }
}