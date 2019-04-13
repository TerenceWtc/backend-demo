package org.terence.backend.dao.repository.admin.specification;

import org.springframework.data.jpa.domain.Specification;
import org.terence.backend.common.utils.jpa.FuzzyQuery;
import org.terence.backend.dao.entity.admin.User;
import org.terence.backend.service.vo.base.ParamsVo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author terence
 * @since 2019/2/25 14:17
 */
public class UserSpec {

    public static Specification<User> searchAll(List<ParamsVo> paramsVoList) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            paramsVoList.forEach(paramsVo -> {
                    String property = FuzzyQuery.getFuzzy(paramsVo.getProperty());
                    predicateList.add(builder.like(root.get(paramsVo.getDirection()), property));
                }
            );
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
