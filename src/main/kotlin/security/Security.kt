package com.example.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.security() {

    install(Authentication) {

        jwt("auth-jwt") {

            realm = ""
            verifier {
                JWT.require(Algorithm.HMAC256(""))
                    .withAudience("")
                    .withIssuer()
                    .build()
            }

            validate { credential ->
                if (credential.payload.audience.contains("")) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }
}