package ly;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<ISay> load = ServiceLoader.load(ISay.class);
        Iterator<ISay> iterator = load.iterator();
        while(iterator.hasNext()) {
            ISay say = iterator.next();
            say.say("haha");
        }
    }
}
