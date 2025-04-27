package com.example.database

import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import java.sql.DriverManager

fun migration() {

    val url = "jdbc:postgresql://localhost/tasks"
    val user = "postgres"
    val password = "ion23#r7"


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