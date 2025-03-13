package org.bistu.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBo {
    private Long id;
    private String name;
    private Integer age;
    private Date birthDate;
}
