package pokemontcg.features.pokemon.data.entities

import com.google.gson.annotations.SerializedName
import pokemontcg.features.pokemon.domain.model.Pokemon
import pokemontcg.libraries.common.MapTo

data class PokemonDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Long,
    @SerializedName("sprites")
    val sprites: Sprites
) : MapTo<Pokemon> {

    override fun mapTo() = Pokemon(name = this.name, imageURL = this.sprites.frontDefault)
}

data class Sprites (
    @SerializedName("front_default")
    val frontDefault: String
)
