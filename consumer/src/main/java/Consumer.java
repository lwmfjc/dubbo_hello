import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import red.wizard.api.service.ServiceDemo;

import java.io.IOException;

public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        logger.info(" dubbo 消费端已启动 》》》");
        ServiceDemo serviceDemo = (red.wizard.api.service.ServiceDemo) context.getBean("demoService");
        String hello = serviceDemo.sayHello("world");
        //logger.info(hello);
        System.out.println(hello);
        System.in.read();
    }
}