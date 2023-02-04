package org.pickwicksoft.avizage.domain.entity

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "sale")
class Sale(

    @Column(nullable = false)
    val date: Instant? = Instant.now(),

    @ManyToOne
    val user: User,

    @ManyToOne
    val storage: Storage,

    @ManyToOne
    var product: Product? = null,

    @ManyToOne
    var category: Category? = null,

    @Column(nullable = false)
    val unitPrice: Double,

    @Column(nullable = false)
    val quantity: Double,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
