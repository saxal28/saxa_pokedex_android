package saxal.me.saxapokedex.api.model

import saxal.me.saxapokedex.constants.LoadingStatus

data class PokeListResult<R>(
    val loading: String = LoadingStatus.NOT_STARTED,
    val data: List<R> = listOf(),
    var errorMessage: String? = null
)