package com.tigiti.skill_managment_system.security;

public class SecurityConstants {
    public static final String SECRET = "tigiti@Securet";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer "; // with space
    public static final String HEADER_STRING = "Authorization"; // le nom de header dans lequel on met le Token

}
