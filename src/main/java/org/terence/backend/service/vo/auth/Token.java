package org.terence.backend.service.vo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author terence
 * @since 2019/2/27 13:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private String accessToken;

    private String refreshToken;
}
