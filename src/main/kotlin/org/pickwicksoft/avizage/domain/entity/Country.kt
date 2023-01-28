package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "country")
class Country(

    @Column(length = 3, nullable = false)
    var code: String,

    @Column(length = 20, nullable = false)
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
