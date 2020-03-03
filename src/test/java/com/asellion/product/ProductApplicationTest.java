package com.asellion.product;

import com.asellion.product.controller.ControllerExceptionHandler;
import com.asellion.product.controller.ProductController;
import com.asellion.product.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * This class is used to test context load.
 *
 * @author Zahra Akbari (akbari.zahra@gmail.com)
 */
@RunWith(SpringRunner.class)

@SpringBootTest
public class ProductApplicationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void loadContextTest() {
        Assert.assertNotNull(applicationContext.getBean(ProductRepository.class));
        Assert.assertNotNull(applicationContext.getBean(ControllerExceptionHandler.class));
        Assert.assertNotNull(applicationContext.getBean(ProductController.class));
    }

}
