package org.pickwicksoft.avizage.domain

import org.pickwicksoft.avizage.config.PHONE_REGEX
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name = "supplier")
class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @field:Size(max = 15)
    @field:NotNull
    @Column(name = "cif_cnp", nullable = false)
    var cifCnp: String? = null

    @field:Size(max = 50)
    @field:NotNull
    @Column(nullable = false)
    var name: String? = null

    @field:Size(max = 15)
    var code: String? = null

    @OneToOne
    @JoinColumn(name = "address_id")
    var address: Address? = null

    @field:Email
    var email: String? = null

    @field:Size(max = 25)
    @field:NotNull
    @Column(name = "contact_person", nullable = false)
    var contactPerson: String? = null

    @field:NotNull
    @field:Pattern(regexp = PHONE_REGEX)
    @Column(name = "telephone", nullable = false)
    var telephone: String? = null
}
