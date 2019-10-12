package org.terence.backend.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.terence.backend.dao.entity.admin.SysDictionary;
import org.terence.backend.dao.repository.admin.SysDictionaryRepository;
import org.terence.backend.dao.specification.admin.SysDictionarySpec;
import org.terence.backend.service.service.admin.SysDictionaryService;

import java.util.List;

/**
 * @author terence
 * @since 2019/7/25 13:32
 */
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    private final SysDictionaryRepository sysDictionaryRepository;

    @Autowired
    public SysDictionaryServiceImpl(SysDictionaryRepository sysDictionaryRepository) {
        this.sysDictionaryRepository = sysDictionaryRepository;
    }

    @Override
    public List<SysDictionary> getGender() {
        return sysDictionaryRepository.findAll(SysDictionarySpec.getGenderList());
    }
}
