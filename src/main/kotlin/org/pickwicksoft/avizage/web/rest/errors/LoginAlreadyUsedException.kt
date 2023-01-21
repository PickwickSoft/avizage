package org.pickwicksoft.avizage.web.rest.errors

import org.zalando.problem.Exceptional

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
class LoginAlreadyUsedException :
    BadRequestAlertException(LOGIN_ALREADY_USED_TYPE, "Login name already used!", "userManagement", "userexists") {

    override fun getCause(): Exceptional? = super.cause

    companion object {
        private const val serialVersionUID = 1L
    }
}
