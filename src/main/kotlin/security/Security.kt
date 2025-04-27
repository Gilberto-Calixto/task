package com.example.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.security() {

    install(Authentication) {

        jwt("auth-jwt") {

            realm = ""
            //verifier {  }

            validate {  }
        }
    }
}