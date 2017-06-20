package com.example.utils;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.config.Constant;
import com.example.model.SysUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtil {
	
	public static String generalSubject(SysUser user){
		JSONObject jo = new JSONObject();
		
		jo.put("userId", user.getId());
		jo.put("loginName", user.getLoginName());
		return jo.toJSONString();
	}
	
    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
		Date expTime = new Date(nowMillis + Constant.JWT_EXPIRATION); 
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expTime)
                .claim(Constant.CLAIMKEY, Constant.CLAIMKEYVALUE)
                .signWith(SignatureAlgorithm.HS256, signingKey.getBytes());
        return builder.compact();
    }
    
    public static boolean isJwtValid(String jwt) {
        try {
            //解析JWT字符串中的数据，并进行最基础的验证
            Claims claims = Jwts.parser()
                    .setSigningKey(Constant.JWT_SECRET.getBytes())   //SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
                    .parseClaimsJws(jwt)  //jwt是JWT字符串
                    .getBody();
            String vaule = claims.get(Constant.CLAIMKEY, String.class);//获取自定义字段key
            //判断自定义字段是否正确
            if (Constant.CLAIMKEYVALUE.equals(vaule)) {
                return true;
            } else {
                return false;
            }
        }
        //在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
        //在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
        catch (SignatureException | ExpiredJwtException e) {
        	e.printStackTrace();
            return false;
        }catch (Exception e) {
        	e.printStackTrace();
        	return false;
		}
    }
    
    
    public static SysUser getSysUser (String token){
    	try {
    		Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
    		String subject = claims.getSubject();
    		SysUser sysUser = new SysUser();
    		JSONObject object = JSON.parseObject(subject);
    		String userId = object.getString("userId");
    		String loginName = object.getString("loginName");
    		sysUser.setId(userId);
    		sysUser.setLoginName(loginName);
    		return sysUser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static void main(String[] args) {
    	SysUser sysUser = new SysUser();
    	sysUser.setId("102134622");
    	sysUser.setLoginName("admin");
		String token = generateToken(Constant.JWT_SECRET, generalSubject(sysUser));
		System.out.println("token=" + token);
		Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
		String vaule = claims.get("key", String.class);
		System.out.println(vaule);
		String subject = claims.getSubject();
		Date issuedAt = claims.getIssuedAt();
		Date expiration = claims.getExpiration();
		System.out.println(subject);
		System.out.println(issuedAt);
		System.out.println(expiration);
		System.out.println(expiration.getTime());
		long nowMillis = System.currentTimeMillis();
		Date expTime = new Date(nowMillis + 30 * 60 * 1000);
		if (expTime.getTime() < expiration.getTime()) {
			System.out.println("jwt-有效验证...");
		} else {
			System.out.println("jwt-失效...");
		}
		System.out.println(isJwtValid(token));
	}
}
