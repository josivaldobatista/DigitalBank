package com.jfb.digital_banking_gateway.utils;

public final class Constantes {

    public static final String KAFKA_BOOTSTRAP_SERVERS = "localhost:9092";

    public static final String KAFKA_GROUP_ID_OBJECTS = "digital-banking-group-objects";


    public static final String INSERT_ACCOUNT_KAFKA_TOPIC = "account-save-topic";
    public static final String DELETE_ACCOUNT_KAFKA_TOPIC = "account-delete-topic";
    public static final String UPDATE_ACCOUNT_KAFKA_TOPIC = "account-update-topic";
    public static final String INSERT_CUSTOMER_KAFKA_TOPIC = "customer-save-topic";
    public static final String INSERT_USER_KAFKA_TOPIC = "user-save-topic";
    public static final String INSERT_CUSTOMER_ERROR_TOPIC = "insert-customer-error-topic";
    public static final String DELETE_CUSTOMER_KAFKA_TOPIC = "customer-delete-topic";
    public static final String UPDATE_CUSTOMER_KAFKA_TOPIC = "customer-update-topic";

}
