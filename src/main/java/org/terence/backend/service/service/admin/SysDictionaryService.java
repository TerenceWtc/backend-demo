package org.terence.backend.service.service.admin;

import org.terence.backend.dao.entity.admin.SysDictionary;

import java.util.List;

/**
 * @author terence
 * @since 2019/7/25 13:33
 */
public interface SysDictionaryService {
    /** data dictionary of gender
     * @return List<SysDictionary>
     */
    List<SysDictionary> getGender();
}
