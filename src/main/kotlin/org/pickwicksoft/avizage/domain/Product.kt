package org.pickwicksoft.avizage.domain

import javax.persistence.*

@Entity
@Table(name = "product")
class Product(

    @Column(length = 50, nullable = false)
    val name: String,

    @Column(length = 100)
    val description: String?,

    @ManyToOne
    val unit: Unit,

    @OneToMany(mappedBy = "product")
    var stocks: MutableSet<Stock> = mutableSetOf(),

    @Column(name = "bar_code", length = 13)
    var barCode: Long? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)
