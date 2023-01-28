package org.pickwicksoft.avizage.domain

import javax.persistence.*

@Entity
@Table(name = "address")
class Address(

    @Column(name = "street_and_number", length = 100, nullable = false)
    val streetAndNumber: String,

    @Column(length = 30, nullable = false)
    val city: String,

    @Column(length = 7, nullable = false)
    val zip: String,

    @ManyToOne
    val county: County? = null,

    @ManyToOne
    val country: Country,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
