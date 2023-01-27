package org.pickwicksoft.avizage.domain

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "address")
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Int? = null

    @field:Size(max = 100)
    @Column(name = "street_and_number", length = 100, nullable = false)
    var streetAndNumber: String? = null

    @field:Size(max = 30)
    @Column(name = "city", length = 30, nullable = false)
    var city: String? = null

    @field:Size(max = 7)
    @Column(length = 7)
    var zip: String? = null

    @field:Size(max = 30)
    @ManyToOne
    @JoinColumn(name = "county_id", referencedColumnName = "id")
    var county: County? = null

    @field:Size(max = 50)
    @Column(name = "country", length = 50, nullable = false)
    var country: String? = null
}
