package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwordCount {
    private Integer sum_A;
    private Integer sum_AA;
    private Integer sum_S;
    private Integer sum_SS;
}
