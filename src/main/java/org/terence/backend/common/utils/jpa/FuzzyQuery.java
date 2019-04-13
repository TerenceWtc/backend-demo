package org.terence.backend.common.utils.jpa;

/**
 * @author terence
 * @since 2019/4/13 10:49
 */
public class FuzzyQuery {

    public static String getFuzzy(String property) {
        return "%" + property + "%";
    }
}
