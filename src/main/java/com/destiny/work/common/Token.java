package com.destiny.work.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Destiny_hao on 2017/9/27.
 */
public class Token {

    private static final String SECRET = "asdgzxg";
    private static Logger log = LoggerFactory.getLogger(Token.class);
    private static final Map<String, Object> header = new HashMap<>();

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public static String createToken(Map<String, Object> claims) {
        long nowTime = System.currentTimeMillis();
        Date now = new Date(nowTime);
        System.out.println("time------"+now);
        System.out.println("time------"+new Date(nowTime+300000));
        header.put("typ", "JWT");
        header.put("alg", "HS265");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowTime))
                .setHeader(header)
                .setExpiration(new Date(nowTime+300000)) //过期时间 5分钟自动过期(60*1000*5)
                .signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    public static Map<String, Object> checkToken(String token) {
        try {
            Map<String, Object> claims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token).getBody();
            System.out.println(claims.toString());
            return claims;
        } catch (Exception e) {
            log.error("json web token verify failed");
            return null;
        }
    }
}
