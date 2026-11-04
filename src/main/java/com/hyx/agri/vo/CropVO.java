package com.hyx.agri.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CropVO {

    private Long id;
    private String cropName;
    private String varietyName;
    private String cropType;
    private String suitArea;
    private Integer growthCycle;
    private Integer yieldLevel;
    private Integer plantStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
