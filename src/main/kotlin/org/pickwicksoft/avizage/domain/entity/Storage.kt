package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "storage")
class Storage(

    @Column(length = 50)
    val name: String,

    @Column(length = 100)
    val description: String? = null,

    @ManyToOne
    val address: Address,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
