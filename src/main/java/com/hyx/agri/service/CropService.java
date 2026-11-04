package com.hyx.agri.service;

import com.hyx.agri.common.PageResult;
import com.hyx.agri.dto.CropDTO;
import com.hyx.agri.vo.CropVO;

public interface CropService {

    CropVO add(CropDTO dto);

    CropVO update(CropDTO dto);

    CropVO detail(Long id);

    void delete(Long id);

    PageResult<CropVO> page(long pageNum, long pageSize, String cropName, String varietyName,
                            String cropType, Integer plantStatus);
}
