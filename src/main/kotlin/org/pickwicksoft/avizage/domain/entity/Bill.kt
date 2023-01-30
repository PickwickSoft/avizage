package org.pickwicksoft.avizage.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "bill")
class Bill(

    @Column(nullable = false)
    val number: String,

    @Column(nullable = false)
    val date: Date,

    @Column(nullable = false)
    val value: Double = 0.0,

    @ManyToOne
    val user: User,

    @OneToMany
    val sales: MutableSet<Sale> = mutableSetOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
