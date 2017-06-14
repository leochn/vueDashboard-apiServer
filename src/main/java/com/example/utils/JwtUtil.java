package com.example.utils;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.example.config.Constant;
import com.example.pojo.SysUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	public static String generalSubject(SysUser user){
		JSONObject jo = new JSONObject();
		jo.put("userId", user.getId());
		jo.put("userName", user.getLoginName());
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
                .signWith(SignatureAlgorithm.HS256, signingKey.getBytes());
        return builder.compact();
    }

//    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
//        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
//        if(token == null) return null;
//        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
//    }
    

    public static void main(String[] args) {
    	SysUser sysUser = new SysUser();
    	sysUser.setId("102134622");
    	sysUser.setLoginName("admin");
		String token = generateToken(Constant.JWT_SECRET, generalSubject(sysUser));
		System.out.println("token=" + token);
		Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
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
	}
}
