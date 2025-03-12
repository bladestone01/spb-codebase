package org.bistu.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bistu.web.domain.ProductBo;
import org.bistu.web.domain.ResultInfo;
import org.bistu.web.service.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ProductControllerV2.class)
public class ProductControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProducts() throws Exception {
        List<ProductBo> productBos = new ArrayList<>();
        productBos.add(ProductBo.create(1L,"Test Product 1", "Desc 1", 100.0f));
        productBos.add(ProductBo.create(2L,"Test Product 2", "Desc 2", 200.0f));

        Mockito.when(productService.getOnes()).thenReturn(productBos);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/webstorev2/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);

        List<ProductBo> returnedProductBos = (List<ProductBo>) resultInfo.getData();

        org.junit.jupiter.api.Assertions.assertEquals(2, returnedProductBos.size());
        Mockito.verify(productService, Mockito.times(1)).getOnes();
    }

    @Test
    public void testGetProduct() throws Exception {
        ProductBo productBo = ProductBo.create(1L,"Test Product", "Desc", 100.0f);
        Mockito.when(productService.getOne(1L)).thenReturn(productBo);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/webstorev2/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);
        ProductBo returnedProductBo = objectMapper.convertValue(resultInfo.getData(), ProductBo.class);

        org.junit.jupiter.api.Assertions.assertEquals(1L, returnedProductBo.getId());
        Mockito.verify(productService, Mockito.times(1)).getOne(1L);
    }

    @Test
    public void testGetProductNotFound() throws Exception {
        Mockito.when(productService.getOne(999L)).thenReturn(null);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/webstorev2/products/999"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);

        org.junit.jupiter.api.Assertions.assertNotEquals(0, resultInfo.getCode());
    }

    @Test
    public void testGetProductByParams() throws Exception {
        List<ProductBo> productBos = new ArrayList<>();
        productBos.add(ProductBo.create(1L,"Test Product 1", "Desc 1", 100.0f));
        Mockito.when(productService.getOnes(50.0f)).thenReturn(productBos);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/webstorev2/products/1/params?price=50.0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);
        List<ProductBo> returnedProductBos = (List<ProductBo>) resultInfo.getData();

        org.junit.jupiter.api.Assertions.assertEquals(1, returnedProductBos.size());
        Mockito.verify(productService, Mockito.times(1)).getOnes(50.0f);
    }

    @Test
    public void testCreateProductByForm() throws Exception {
        ProductBo productBo = ProductBo.create("New Product", "New Desc", 99.9f);
        Mockito.when(productService.createOne(Mockito.anyString(), Mockito.anyString(), Mockito.anyFloat())).thenReturn(productBo);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/webstorev2/products/form")
                        .param("name", "New Product")
                        .param("description", "New Desc")
                        .param("price", "99.9"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);
        ProductBo returnedProductBo = objectMapper.convertValue(resultInfo.getData(), ProductBo.class);

        org.junit.jupiter.api.Assertions.assertEquals("New Product", returnedProductBo.getName());
        Mockito.verify(productService, Mockito.times(1)).createOne("New Product", "New Desc", 99.9f);
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductBo productBo = ProductBo.create("Test Add", "Add Desc", 123.45f);
        Mockito.when(productService.createOne(Mockito.any(ProductBo.class))).thenReturn(productBo);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/webstorev2/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productBo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);
        ProductBo returnedProductBo = objectMapper.convertValue(resultInfo.getData(), ProductBo.class);

        org.junit.jupiter.api.Assertions.assertEquals("Test Add", returnedProductBo.getName());
        Mockito.verify(productService, Mockito.times(1)).createOne(Mockito.any(ProductBo.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductBo productBo = ProductBo.create(1L,"Test Update", "Update Desc", 123.45f);
        Mockito.when(productService.udpateOne(Mockito.eq(1L), Mockito.any(ProductBo.class))).thenReturn(productBo);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/webstorev2/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productBo)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);
        ProductBo returnedProductBo = objectMapper.convertValue(resultInfo.getData(), ProductBo.class);
        org.junit.jupiter.api.Assertions.assertEquals("Test Update", returnedProductBo.getName());
        Mockito.verify(productService, Mockito.times(1)).udpateOne(Mockito.eq(1L), Mockito.any(ProductBo.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        ProductBo productBo = ProductBo.create(1L,"Test Delete", "Delete Desc", 123.45f);
        Mockito.when(productService.deleteOne(1L)).thenReturn(productBo);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/webstorev2/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        ResultInfo resultInfo = objectMapper.readValue(content, ResultInfo.class);
        ProductBo returnedProductBo = objectMapper.convertValue(resultInfo.getData(), ProductBo.class);

        org.junit.jupiter.api.Assertions.assertEquals("Test Delete", returnedProductBo.getName());
        Mockito.verify(productService, Mockito.times(1)).deleteOne(1L);
    }
}