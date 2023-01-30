package org.pickwicksoft.avizage.domain.entity

import org.pickwicksoft.avizage.config.IBAN_REGEX
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "bank_account")
class BankAccount(

    @field:Pattern(regexp = IBAN_REGEX)
    @Column(length = 30, nullable = false)
    val iban: String,

    @field:Size(max = 30)
    @Column(name = "bank_name", length = 30, nullable = false)
    val bankName: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
