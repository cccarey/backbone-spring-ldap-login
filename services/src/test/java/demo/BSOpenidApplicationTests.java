package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.christiancarey.bsllogin.BSLoginApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BSLoginApplication.class)
@WebAppConfiguration
public class BSOpenidApplicationTests {

    @Test
    public void contextLoads() {
    }

}
