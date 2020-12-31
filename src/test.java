import org.junit.Test;

public class test {
    @Test
    public void test(){
        String x = "123";
        x = x.substring(0,x.length()-1);
        System.out.println(x);
    }
}