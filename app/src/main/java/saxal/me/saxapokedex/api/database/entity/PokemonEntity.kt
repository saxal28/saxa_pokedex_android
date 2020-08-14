package saxal.me.saxapokedex.api.database.entity

import androidx.room.*
import java.util.*

@Entity
data class PokemonEntity(
    var modified: Long = Date().time,

    @PrimaryKey val uid: Int,
    val id: Int,
    val name: String
//    @TypeConverters(ListConverter::class)
//    val types: List<Types>

)