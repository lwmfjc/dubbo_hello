package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZookeeper {
    private String connectString =  "192.168.186.1:2181,192.168.186.129:2181,192.168.186.133:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;


    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                List<String> children = null;
                try {
                    children = zkClient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (String child : children) {
                    System.out.println(child);
                }
            }
        });
    }
    @Test
    public void createNode() throws Exception {
        // 参数1：要创建的节点的路径； 参数2：节点数据 ； 参数3：节点权限 ；参数4：节点的类型
        String path = zkClient.create("/demo", "hello".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }
    // 获取子节点并监听节点变化
    @Test
    public void getChildrenAndWatch() throws Exception {

        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        // 延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }
    // 判断znode是否存在
    @Test
    public void exist() throws Exception {
        Stat stat = zkClient.exists("/animals", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }
}
