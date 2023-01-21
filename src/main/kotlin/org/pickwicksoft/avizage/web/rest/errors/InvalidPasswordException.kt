package org.pickwicksoft.avizage.web.rest.errors

import org.zalando.problem.AbstractThrowableProblem
import org.zalando.problem.Exceptional
import org.zalando.problem.Status

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
class InvalidPasswordException :
    AbstractThrowableProblem(INVALID_PASSWORD_TYPE, "Incorrect password", Status.BAD_REQUEST) {

    override fun getCause(): Exceptional? = super.cause

    companion object {
        private const val serialVersionUID = 1L
    }
}
