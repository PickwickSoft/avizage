package org.pickwicksoft.avizage.domain

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "storage")
class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "storage_id", nullable = false)
    var storageId: Int? = null

    @field:Size(max = 50)
    @Column(length = 50)
    var name: String? = null

    @field:Size(max = 100)
    @Column(length = 100)
    var description: String? = null

    @ManyToOne
    @JoinColumn(name = "address_id")
    var address: Address? = null

    @OneToMany(mappedBy = "storage")
    var stocks: MutableSet<Stock> = mutableSetOf()
}
