package com.springboot.TestApp.enums;

import jakarta.persistence.EntityManager;

public enum Database {
    DB1, DB2;

    public EntityManager getEntityManager(EntityManager db1EntityManager, EntityManager db2EntityManager) {
        return switch (this) {
            case DB1 -> db1EntityManager;
            case DB2 -> db2EntityManager;
        };
    }

    public static Database fromString(String db) {
        return switch (db.toLowerCase()) {
            case "db1" -> DB1;
            case "db2" -> DB2;
            default -> throw new IllegalArgumentException("Unknown database: " + db);
        };
    }
}