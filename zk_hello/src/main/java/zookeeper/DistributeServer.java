package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {

    private String connectString = "192.168.186.1:2181,192.168.186.129:2181,192.168.186.133:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    public static void main(String[] args) throws Exception {
        args = new String[]{"slave1"};
        DistributeServer server = new DistributeServer();
        // 1.连接zookeeper集群
        server.getConnect();
        // 2.注册节点
        server.register(args[0]);
        // 3.业务逻辑处理
        server.business();
    }

    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {

            }
        });
    }

    private void register(String hostname) throws KeeperException, InterruptedException {
        String path = zkClient.create("/servers/server", hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + " is online");
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
}
