package org.bistu.web.webstore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBo {
    private static AtomicLong counter = new AtomicLong(1);

    // 商品id
    private Long id;

    // 商品名称
    private String name;

    // 商品描述
    private String description;

    // 商品价格
    private Float price;


    public static ProductBo create() {
        Faker faker = new Faker();

        ProductBo productBo = new ProductBo();
        productBo.setId(counter.getAndIncrement());
        productBo.setName(faker.commerce().productName());
        productBo.setDescription(faker.commerce().material());
        productBo.setPrice(Double.valueOf(faker.number().randomDouble(2, 1, 1000)).floatValue());

        return productBo;
    }

    public static ProductBo create(String Name, String description, float price) {
        ProductBo productBo = new ProductBo();
        productBo.setId(counter.getAndIncrement());
        productBo.setName(Name);
        productBo.setDescription(description);
        productBo.setPrice(price);

        return productBo;
    }
}
