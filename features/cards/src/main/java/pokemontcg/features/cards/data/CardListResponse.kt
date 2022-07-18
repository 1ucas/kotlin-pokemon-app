package pokemontcg.features.cards.data

import com.google.gson.annotations.SerializedName

internal class CardListResponse(
    @SerializedName("cards")
    val cards: List<CardDTO>
)
