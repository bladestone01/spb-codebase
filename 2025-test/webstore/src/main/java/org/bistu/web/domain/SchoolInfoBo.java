package org.bistu.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolInfoBo {
    private Long id;
    private String name;
    private Integer studentNum;
    private Integer valid = 1;
}
