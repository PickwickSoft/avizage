package org.pickwicksoft.avizage.domain

import org.pickwicksoft.avizage.config.PHONE_REGEX
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

@Entity
@Table(name = "supplier")
class Supplier(

    @Column(name = "cif_cnp", nullable = false)
    val cifCnp: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val code: String,

    @Column(nullable = false)
    @OneToMany
    val addresses: MutableSet<Address> = mutableSetOf(),

    @field:Email
    @Column(nullable = false)
    val email: String,

    @Column(name = "contact_person", nullable = false)
    val contactPerson: String,

    @field:Pattern(regexp = PHONE_REGEX)
    @Column(name = "phone", nullable = false)
    val phone: String,

    @OneToMany
    val accounts: MutableSet<BankAccount> = mutableSetOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
