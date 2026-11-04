package com.hyx.agri.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.hyx.agri.common.PageResult;
import com.hyx.agri.dto.CropDTO;
import com.hyx.agri.entity.Crop;
import com.hyx.agri.exception.BusinessException;
import com.hyx.agri.mapper.CropMapper;
import com.hyx.agri.service.CropService;
import com.hyx.agri.vo.CropVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class CropServiceImpl extends ServiceImpl<CropMapper, Crop> implements CropService {

    @Override
    public CropVO add(CropDTO dto) {
        log.info("新增农作物品种, cropName={}, varietyName={}", dto.getCropName(), dto.getVarietyName());
        Crop crop = new Crop();
        BeanUtils.copyProperties(dto, crop);
        crop.setId(null);
        save(crop);
        log.info("新增完成, id={}", crop.getId());
        return toVo(crop);
    }

    @Override
    public CropVO update(CropDTO dto) {
        if (dto.getId() == null) {
            throw new BusinessException("修改时 id 不能为空");
        }
        log.info("修改农作物品种, id={}", dto.getId());
        Crop existing = getById(dto.getId());
        if (existing == null) {
            throw new BusinessException("农作物品种不存在");
        }
        BeanUtils.copyProperties(dto, existing);
        updateById(existing);
        return toVo(getById(existing.getId()));
    }

    @Override
    public CropVO detail(Long id) {
        log.info("按 id 查询农作物品种, id={}", id);
        Crop crop = super.getById(id);
        if (crop == null) {
            throw new BusinessException("农作物品种不存在");
        }
        return toVo(crop);
    }

    @Override
    public void delete(Long id) {
        log.info("逻辑删除农作物品种, id={}", id);
        Crop crop = super.getById(id);
        if (crop == null) {
            throw new BusinessException("农作物品种不存在");
        }
        removeById(id);
    }

    @Override
    public PageResult<CropVO> page(long pageNum, long pageSize, String cropName, String varietyName,
                                   String cropType, Integer plantStatus) {
        log.info("分页查询, pageNum={}, pageSize={}, cropName={}, varietyName={}, cropType={}, plantStatus={}",
                pageNum, pageSize, cropName, varietyName, cropType, plantStatus);
        LambdaQueryWrapper<Crop> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(cropName)) {
            wrapper.like(Crop::getCropName, cropName);
        }
        if (StringUtils.hasText(varietyName)) {
            wrapper.like(Crop::getVarietyName, varietyName);
        }
        if (StringUtils.hasText(cropType)) {
            wrapper.eq(Crop::getCropType, cropType);
        }
        if (plantStatus != null) {
            wrapper.eq(Crop::getPlantStatus, plantStatus);
        }
        wrapper.orderByDesc(Crop::getId);
        Page<Crop> page = page(new Page<>(pageNum, pageSize), wrapper);
        List<CropVO> records = page.getRecords().stream().map(this::toVo).toList();
        return PageResult.of(page.getTotal(), page.getPages(), page.getCurrent(), records);
    }

    private CropVO toVo(Crop crop) {
        CropVO vo = new CropVO();
        BeanUtils.copyProperties(crop, vo);
        return vo;
    }
}
