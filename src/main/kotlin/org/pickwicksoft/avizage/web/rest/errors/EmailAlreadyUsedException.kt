package org.pickwicksoft.avizage.web.rest.errors

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
class EmailAlreadyUsedException :
    BadRequestAlertException(EMAIL_ALREADY_USED_TYPE, "Email is already in use!", "userManagement", "emailexists") {

    companion object {
        private const val serialVersionUID = 1L
    }
}
