package unitTest.Testcontainers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

public class RedisBackedCacheIntTest {
    @Rule
    public GenericContainer redis = new GenericContainer<>("redis:4").withExposedPorts(6379);
    @Before
    public void setUp() {
        String address = redis.getContainerIpAddress();
        Integer port = redis.getFirstMappedPort();
        System.out.println(address);
        System.out.println(port);
    }
    @Test
    public void testSimplePutAndGet() {
        System.out.println("访问docker容器测试成功");
    }
}
