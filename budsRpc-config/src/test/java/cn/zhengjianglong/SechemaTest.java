package cn.zhengjianglong;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class SechemaTest {

    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        ctx.getBean("helloService");

    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
