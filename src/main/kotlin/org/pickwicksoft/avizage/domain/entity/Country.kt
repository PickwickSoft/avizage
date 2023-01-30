package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "country")
class Country(

    @Column(length = 2, nullable = false)
    var code: String,

    @Column(length = 100, nullable = false)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
