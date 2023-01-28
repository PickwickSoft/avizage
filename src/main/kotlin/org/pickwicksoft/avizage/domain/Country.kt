package org.pickwicksoft.avizage.domain

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "country")
class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id", nullable = false)
    var countryId: Int? = null

    @field:NotNull
    @field:Size(min = 1, max = 2)
    @Column(length = 2, nullable = false)
    var code: String? = null

    @field:NotNull
    @field:Size(max = 20)
    @Column(length = 20, nullable = false)
    var name: String? = null

    @OneToMany(mappedBy = "country")
    var address: MutableSet<Address> = mutableSetOf()
}
