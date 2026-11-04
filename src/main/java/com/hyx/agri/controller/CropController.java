package com.hyx.agri.controller;

import com.hyx.agri.common.PageResult;
import com.hyx.agri.common.Result;
import com.hyx.agri.dto.CropDTO;
import com.hyx.agri.service.CropService;
import com.hyx.agri.vo.CropVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/crop")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping("/add")
    public Result<CropVO> add(@Valid @RequestBody CropDTO dto) {
        log.info("新增接口入参: {}", dto);
        CropVO vo = cropService.add(dto);
        log.info("新增接口出参: {}", vo);
        return Result.ok(vo);
    }

    @PutMapping("/update")
    public Result<CropVO> update(@Valid @RequestBody CropDTO dto) {
        log.info("修改接口入参: {}", dto);
        CropVO vo = cropService.update(dto);
        log.info("修改接口出参: {}", vo);
        return Result.ok(vo);
    }

    @GetMapping("/{id}")
    public Result<CropVO> get(@PathVariable Long id) {
        log.info("查询接口入参 id={}", id);
        CropVO vo = cropService.detail(id);
        log.info("查询接口出参: {}", vo);
        return Result.ok(vo);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        log.info("删除接口入参 id={}", id);
        cropService.delete(id);
        return Result.ok(null);
    }

    @GetMapping("/list")
    public Result<PageResult<CropVO>> list(@RequestParam(defaultValue = "1") long pageNum,
                                           @RequestParam(defaultValue = "10") long pageSize,
                                           @RequestParam(required = false) String cropName,
                                           @RequestParam(required = false) String varietyName,
                                           @RequestParam(required = false) String cropType,
                                           @RequestParam(required = false) Integer plantStatus) {
        log.info("分页接口入参 pageNum={}, pageSize={}, cropName={}, varietyName={}, cropType={}, plantStatus={}",
                pageNum, pageSize, cropName, varietyName, cropType, plantStatus);
        PageResult<CropVO> page = cropService.page(pageNum, pageSize, cropName, varietyName, cropType, plantStatus);
        log.info("分页接口出参 total={}, pages={}", page.getTotal(), page.getPages());
        return Result.ok(page);
    }
}
