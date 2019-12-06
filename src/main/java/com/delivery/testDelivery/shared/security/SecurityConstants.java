package com.delivery.testDelivery.shared.security;


public class SecurityConstants {
    public static final String SECRET = "CanadaDeliveryService";
    public static final long EXPIRATION_TIME = 86_400_000; // 1 day
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users";
    public static final String SIGN_UP_VALIDATE_URL = "/api/users/validate";
    public static final String LOGIN_URL = "/login";
    public static final String SWAGGER_URL = "/swagger-ui.html";
    public static final String FILES_URL = "/api/files/**";
    public static final String PAYMENT_URL = "/payment/charge";
    public static final String MEAL_URL = "/api/meals";
    public static final String CATEGORY_URL = "/api/categories";
    public static final String MEAL_ID_URL = "/api/meals/{id}";
    public static final String CATEGORY_ID_URL = "/api/categories/{id}";
    public static final String MEAL_BY_CATEGORY_ID_URL = "/api/meals/category/{id}";

}
