package org.bistu.web.service;

import org.bistu.web.domain.ProductBo;
import org.bistu.web.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        productService.init();
        System.out.println("hello world");
    }

    @Test
    //@Order(1)
    public void testGetOne() {
        List<ProductBo> productBos = this.productService.getOnes();
        Assertions.assertEquals(3, productBos.size());

        Long productId = productBos.get(0).getId();
        ProductBo productBo = productService.getOne(productId);
        Assertions.assertNotNull(productBo);
        Assertions.assertEquals(productId, productBo.getId());
    }

    @Test
    public void testGetOnes() {
        List<ProductBo> productBos = productService.getOnes();
        Assertions.assertEquals(3, productBos.size());
    }

    @Test
    public void testGetOnesByPrice() {
        List<ProductBo> productBos = productService.getOnes(0f);
        Assertions.assertEquals(3, productBos.size());

        List<ProductBo> productBos2 = productService.getOnes(100000f);
        Assertions.assertEquals(0, productBos2.size());
    }

    @Test
    public void testCreateOne() {
        ProductBo newProductBo = ProductBo.create("newProduct", "desc", 99.9f);
        ProductBo createdProductBo = productService.createOne(newProductBo);
        Assertions.assertNotNull(createdProductBo);
        Assertions.assertEquals("newProduct", createdProductBo.getName());

        ProductBo createdProductBo2 = productService.createOne("newProduct2", "desc2", 99.9f);
        Assertions.assertNotNull(createdProductBo2);
        Assertions.assertEquals("newProduct2", createdProductBo2.getName());

        Assertions.assertEquals(5, productService.getOnes().size());
    }

    @Test
    public void testCreateOneByParams() {
        ProductBo createdProductBo = productService.createOne("newProduct3", "desc3", 99.9f);
        Assertions.assertNotNull(createdProductBo);
        Assertions.assertEquals("newProduct3", createdProductBo.getName());
        Assertions.assertEquals(4, productService.getOnes().size());
    }


    @Test
    public void testUpdateOne() {
        ProductBo productBo = productService.getOne(1L);
        ProductBo updatedProductBo = ProductBo.create("updatedName", "updatedDesc", 999.9f);
        ProductBo result = productService.udpateOne(1L, updatedProductBo);
        Assertions.assertEquals("updatedName", result.getName());
        Assertions.assertEquals(999.9f, result.getPrice());
        Assertions.assertEquals("updatedDesc", result.getDescription());
        Assertions.assertEquals(productBo.getId(), result.getId());
    }

    @Test
    public void testDeleteOne() {
        productService.deleteOne(1L);
        Assertions.assertNull(productService.getOne(1L));
    }
}
