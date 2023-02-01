package org.pickwicksoft.avizage.domain.entity

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "bill")
class Bill(

    @Column(nullable = false)
    val number: String,

    @Column(nullable = false)
    val date: Instant? = Instant.now(),

    @Column(nullable = false, name = "total")
    val value: Double = 0.0,

    @ManyToOne
    val user: User,

    @OneToMany
    val sales: MutableSet<Sale> = mutableSetOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
