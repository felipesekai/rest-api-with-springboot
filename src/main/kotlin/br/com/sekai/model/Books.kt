package br.com.sekai.model

import jakarta.persistence.*
import java.util.Date
import kotlin.reflect.typeOf

@Entity
@Table(name = "books")
data class Books(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false, length = 180)
    var author: String = "",
    @Column(name= "launch_date", length = 6)
    var launchDate: Date? = null,
    @Column(nullable = false)
    var price: Double = 0.0,
    @Column(nullable = false, length = 250)
    var title : String = ""
)
