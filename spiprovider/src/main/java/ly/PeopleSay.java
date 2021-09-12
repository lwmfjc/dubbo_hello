package ly;

public class PeopleSay implements ISay{
    public void say(String name) {
        System.out.println("hello--"+name);
    }
}
