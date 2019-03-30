package org.terence.backend.common.utils.time;

import org.terence.backend.common.utils.NullValueUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Author: terence
 * @Date: 2019/2/19 11:16
 */
public class TimeConvertUtil {

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        NullValueUtil.handleNull(localDateTime);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }
}
