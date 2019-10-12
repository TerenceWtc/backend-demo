package org.terence.backend.dao.specification.admin;

import org.springframework.data.jpa.domain.Specification;
import org.terence.backend.common.constant.CommonConstant;
import org.terence.backend.dao.entity.admin.SysDictionary;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/7/26 10:43
 */
public class SysDictionarySpec {

    public static Specification<SysDictionary> getGenderList() {
        return (Root<SysDictionary> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(builder.equal(root.get("type"), CommonConstant.DICTIONARY_GENDER_TYPE));
            predicateList.add(builder.equal(root.get("active"), true));
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
