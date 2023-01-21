package org.pickwicksoft.avizage.service.dto

import java.io.Serializable

/**
 * A DTO representing a password change required data - current and new password.
 */
data class PasswordChangeDTO(var currentPassword: String? = null, var newPassword: String? = null) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
