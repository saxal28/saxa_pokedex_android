package saxal.me.saxapokedex.api.model
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EggGroup(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class Language(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class Version(
    val name: String,
    val url: String
)

@JsonClass(generateAdapter = true)
data class FlavorTextEntries(
    val flavor_text: String,
    val language: Language,
    val version: Version
)