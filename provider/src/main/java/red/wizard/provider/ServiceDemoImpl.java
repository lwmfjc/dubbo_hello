package red.wizard.provider;

import red.wizard.api.service.ServiceDemo;

import java.util.Date;

public class ServiceDemoImpl implements ServiceDemo {

    public String sayHello(String name) {
        return name + "[" + new Date() + "]";
    }
}
