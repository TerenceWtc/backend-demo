package org.terence.backend.common.utils.jwt;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.terence.backend.common.constant.JwtConstant;
import org.terence.backend.common.utils.time.TimeConvertUtil;

/**
 * @author terence
 * @since 2019/2/18 17:44
 */
public class JwtHelper {

    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * generate token with username, id and name
     * encrypt token by file path of private key
     * @param userIwtInfo
     * @param privateKeyPath
     * @param expiration
     * @return
     * @throws Exception
     */
    public static String generateToken(IUserIwtInfo userIwtInfo, String privateKeyPath, int expiration) throws Exception {
        PrivateKey privateKey = rsaKeyHelper.getPrivateKey(privateKeyPath);
        return generation(userIwtInfo, privateKey, expiration);
    }

    /**
     * generate token with username, id and name
     * encrypt token by byte of private key
     * @param userIwtInfo
     * @param privateKeyByte
     * @param expiration
     * @return
     * @throws Exception
     */
    public static String generateToken(IUserIwtInfo userIwtInfo, byte[] privateKeyByte, int expiration) throws Exception {
        PrivateKey privateKey = rsaKeyHelper.getPrivateKey(privateKeyByte);
        return generation(userIwtInfo, privateKey, expiration);
    }

    public static IUserIwtInfo getInfoFromToken(String token, String publicKeyPath) throws Exception {
        PublicKey publicKey = rsaKeyHelper.getPublicKey(publicKeyPath);
        return getInfo(token, publicKey);
    }

    public static IUserIwtInfo getInfoFromToken(String token, byte[] publicKeyByte) throws Exception {
        PublicKey publicKey = rsaKeyHelper.getPublicKey(publicKeyByte);
        return getInfo(token, publicKey);
    }

    private static String generation(IUserIwtInfo userIwtInfo, PrivateKey privateKey, int expiration) {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(expiration);
        return Jwts.builder().setSubject(userIwtInfo.getId())
                .claim(JwtConstant.JWT_KEY_USERNAME, userIwtInfo.getUsername())
                .claim(JwtConstant.JWT_KEY_NAME, userIwtInfo.getName())
                .setExpiration(TimeConvertUtil.LocalDateTime2Date(localDateTime))
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }

    private static IUserIwtInfo getInfo(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return new UserJwtInfo(claims.getSubject(), claims.get(JwtConstant.JWT_KEY_USERNAME).toString(),
                claims.get(JwtConstant.JWT_KEY_NAME).toString());
    }
}
