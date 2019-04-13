package org.terence.backend.common.utils.orika;

import com.alibaba.fastjson.JSON;
import org.terence.backend.service.vo.base.PageVo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author terence
 * @since 2019/4/12 16:20
 */
public class JsonFormat {


    public static PageVo json2PageVo(String page) {
        String jsonTemp = "";
        try {
            jsonTemp = URLDecoder.decode(page, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(jsonTemp, PageVo.class);
    }
}
