package org.pickwicksoft.avizage.domain

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "address")
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", nullable = false)
    var addressId: Int? = null

    @field:Size(max = 100)
    @Column(name = "street_and_number", length = 100, nullable = false)
    var streetAndNumber: String? = null

    @field:Size(max = 30)
    @Column(name = "city", length = 30, nullable = false)
    var city: String? = null

    @field:Size(max = 7)
    @Column(length = 7)
    var zip: String? = null

    @ManyToOne
    @JoinColumn(name = "county_id")
    var county: County? = null

    @ManyToOne
    @JoinColumn(name = "country_id")
    var country: Country? = null

    @OneToMany(mappedBy = "address")
    var storages: MutableSet<Storage> = mutableSetOf()
}
