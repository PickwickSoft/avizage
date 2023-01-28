package org.pickwicksoft.avizage.domain

import javax.persistence.*

@Entity
@Table(name = "unit")
class Unit(

    @Column(length = 25, nullable = false)
    val name: String,

    @Column(length = 5, nullable = false)
    val code: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
