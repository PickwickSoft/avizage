package org.pickwicksoft.avizage.domain.entity

import javax.persistence.*

@Entity
@Table(name = "product")
class Product(

    @Column(length = 50, nullable = false)
    val name: String,

    val iconName: String? = null,

    @Column(length = 100)
    val description: String?,

    @ManyToOne
    var unit: Unit,

    @ManyToOne
    var category: Category,

    @Column(name = "bar_code", length = 13)
    var barCode: Long? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)
