package org.terence.backend.web.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.terence.backend.common.utils.orika.BeanFormat;
import org.terence.backend.dao.entity.admin.SysDictionary;
import org.terence.backend.service.service.admin.SysDictionaryService;
import org.terence.backend.service.vo.admin.SysDictionaryVo;
import org.terence.backend.service.vo.base.ObjectResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/7/25 13:33
 */
@RestController
@RequestMapping("admin/sysDictionary")
@Api(tags = "SysDictionaryController", description = "字典")
public class SysDictionaryController {

    private final SysDictionaryService sysDictionaryService;

    @Autowired
    public SysDictionaryController(SysDictionaryService sysDictionaryService) {
        this.sysDictionaryService = sysDictionaryService;
    }

    @ApiOperation(value = "获取性别字典")
    @GetMapping("/getGenderList")
    public ObjectResponse<List<SysDictionaryVo>> getGender() {
        List<SysDictionary> genderList = sysDictionaryService.getGender();
        List<SysDictionaryVo> genderVoList = new ArrayList<>();
        genderList.forEach(item -> genderVoList.add(BeanFormat.formatDictionaryVo().getMapperFacade().map(item, SysDictionaryVo.class)));
        return new ObjectResponse<>(genderVoList);
    }
}
