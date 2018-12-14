package garine.learn.user.service.utils;

import com.garine.learn.common.util.JsonUtils;
import com.google.common.collect.Maps;
import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;


public class JwtTokenUtils {

    /**
     * token默认过期时间 30 分钟
     */
    public static final Integer  DEFAULT_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;

    public static final String  DEFAULT_TOKEN_SECRETKEY = "123456";

    public static void main(String[] args) {
        System.out.println(generatorSecretkey("123456"));

        String tolen = generateTokenBy("1admin");
        System.out.println(tolen);

        System.out.println(pharseToken(tolen));
    }
    private static String generatorSecretkey(String key){
        return BaseEncoding.base64().encode(key.getBytes());
    }

    //seperator--------------------------------------------------------------------------

    /**
     * key-val格式获取payload中信息
     * @param token
     * @param secretkey
     * @return
     */
    public static Claims pharseToken(String token, String secretkey){
        Jws<Claims> claimsJwt=Jwts.parser()
                                .setSigningKey(secretkey)
                                .parseClaimsJws(token);
        return claimsJwt.getBody();
    }

    public static Claims pharseToken(String token){
        Jws<Claims> claimsJwt=Jwts.parser()
                .setSigningKey(generatorSecretkey(DEFAULT_TOKEN_SECRETKEY))
                .parseClaimsJws(token);
        return claimsJwt.getBody();
    }

    /**
     *
     * @param header like
     *               {
     *                  "alg": "HS256",
     *                  "typ": "JWT"
     *                }
     * @param payload like
     *                {
     *                   "sub": "1234567890",
     *                   "name": "John Doe",
     *                   "admin": true,
     *                   "exp": ${timestamp}
     *                 }
     *
     * @param secretkey 加盐
     * @return token=base64(header).base64(payload).sinatrue ,
     *         sinatrue=SignatureAlgorithm.HS256( base64(header) + "." + base64(payload) , base64(key))
     *         secretKey=base64(key)
     *         JWT规范是以上实现，目前jwt包的实现不是
     */
    public static String generateTokenBy(Map<String, Object> header, Map<String, Object> payload, String secretkey){
         return Jwts.builder()
                .setHeader(header)
                .setPayload(JsonUtils.beanToJson(payload))
                .signWith(SignatureAlgorithm.HS256, secretkey).compact();
    }

    public static String generateTokenBy(String userName, long exp, String secretkey){
        Map<String, Object> defaultHeader = Maps.newHashMap();
        defaultHeader.put("alg", "HS256");
        defaultHeader.put("typ", "JWT");

        Map<String, Object> payload = Maps.newHashMap();
        payload.put("userName", userName);
        payload.put("exp", exp);
        return generateTokenBy(defaultHeader, payload, secretkey);
    }

    public static String generateTokenBy(String userName, String secretkey){
        return generateTokenBy(userName, getDefaultTokenExpireTime(), secretkey);
    }

    public static String generateTokenBy(String userName){
        return generateTokenBy(userName, generatorSecretkey(DEFAULT_TOKEN_SECRETKEY));
    }

    public static Long getDefaultTokenExpireTime(){
        Date curDate = new Date();
        return curDate.getTime() + DEFAULT_TOKEN_EXPIRE_TIME;
    }

}
