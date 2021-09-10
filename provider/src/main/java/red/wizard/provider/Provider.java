package red.wizard.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {
    private static final Logger logger = LoggerFactory.getLogger(Provider.class);
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        //logger.info("dubbo 服务端已启动 《《《");
        System.out.println("dubbo 服务端已启动 《《《");
        System.in.read();
    }
}
