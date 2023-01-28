package org.pickwicksoft.avizage.domain

import org.pickwicksoft.avizage.config.IBAN_REGEX
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "bank_account")
class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", nullable = false)
    var accountId: Int? = null

    @field:NotNull
    @field:Size(max = 30)
    @field:Pattern(regexp = IBAN_REGEX)
    @Column(length = 30, nullable = false)
    var iban: String? = null

    @field:NotNull
    @field:Size(max = 30)
    @Column(name = "bank_name", length = 30, nullable = false)
    var bankName: String? = null
}
