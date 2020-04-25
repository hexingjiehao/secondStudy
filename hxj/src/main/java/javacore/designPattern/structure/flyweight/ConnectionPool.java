package core.designPattern.structure.flyweight;

import java.util.Vector;

/**
 * Created by xiongjie on 2018/11/18.
 *
 * 享元模式有单例，共享变量，有多线程,有池子
 * 本质是：使用锁来获取和释放集合池子
 */
public class ConnectionPool {

    private Vector<Connection> pool;
    Connection conn = null;

    private ConnectionPool() {
        pool = new Vector<Connection>(100);
        for (int i = 0; i < 100; i++) {
            conn = new Connection();
            pool.add(conn);
        }
    }

    public synchronized void release() {
        pool.add(conn);
    }

    public synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

}
