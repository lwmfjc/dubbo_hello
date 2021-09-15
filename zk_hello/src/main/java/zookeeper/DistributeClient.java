package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributeClient {

    private String connectString =  "192.168.186.1:2181,192.168.186.129:2181,192.168.186.133:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;


    public static void main(String[] args) throws Exception {
        DistributeClient client = new DistributeClient();
        // 1.连接zookeeper集群
        client.getConnect();
        // 2.注册监听
        client.getChildren();
        // 3.业务逻辑处理
        client.business();
    }

    private void getConnect() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                try {
                    getChildren();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/servers", true);
        // 存储服务器节点主机名称集合
        ArrayList<String> hosts = new ArrayList<String>();
        for (String child : children) {
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            hosts.add(new String(data));
        }
        System.out.println(hosts);
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
}
