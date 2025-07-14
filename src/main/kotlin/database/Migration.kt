@file:Suppress("DEPRECATION")

package com.example.database

import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.sql.DriverManager

fun migration() {

    val url = System.getenv("DB_URL") ?: error("Missing DB_URL for migration")
    val user = System.getenv("DB_USER") ?: error("Missing DB_USER for migration")
    val password = System.getenv("DB_PASSWORD") ?: error("Missing DB_PASSWORD for migration")

    println("🔄 Running migrations on $url")

    val connect = DriverManager.getConnection(url, user, password)
    connect.use {
        val liquibase = Liquibase(
            "db/changelog/db.changelog-master.yaml",
            ClassLoaderResourceAccessor(),
            JdbcConnection(it)
        )
        liquibase.update()
    }
}