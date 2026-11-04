package com.hyx.agri.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CropDTO {

    private Long id;

    @NotBlank(message = "作物名称不能为空")
    @Size(max = 50, message = "作物名称长度不能超过50")
    private String cropName;

    @NotBlank(message = "品种名称不能为空")
    @Size(max = 50, message = "品种名称长度不能超过50")
    private String varietyName;

    @NotBlank(message = "作物类型不能为空")
    @Size(max = 20, message = "作物类型长度不能超过20")
    private String cropType;

    @Size(max = 100, message = "适宜种植区域长度不能超过100")
    private String suitArea;

    @NotNull(message = "生长周期不能为空")
    @Min(value = 1, message = "生长周期不能小于1天")
    @Max(value = 3650, message = "生长周期不能超过3650天")
    private Integer growthCycle;

    @NotNull(message = "亩产预估不能为空")
    @Min(value = 0, message = "亩产预估不能为负数")
    private Integer yieldLevel;

    @NotNull(message = "推广状态不能为空")
    @Min(value = 0, message = "推广状态只能是0或1")
    @Max(value = 1, message = "推广状态只能是0或1")
    private Integer plantStatus;
}
