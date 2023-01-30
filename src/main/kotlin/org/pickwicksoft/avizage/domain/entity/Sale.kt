package org.pickwicksoft.avizage.domain.entity

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "sale")
class Sale(

    @Column(nullable = false)
    val date: Date,

    @ManyToOne
    val user: User,

    @ManyToOne
    val storage: Storage,

    @ManyToOne
    val product: Product,

    @Column(nullable = false)
    val unitPrice: Double,

    @Column(nullable = false)
    val quantity: Double,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
)
