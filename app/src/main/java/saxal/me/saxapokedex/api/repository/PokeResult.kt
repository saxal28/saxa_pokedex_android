package saxal.me.saxapokedex.api.repository

import saxal.me.saxapokedex.constants.LoadingStatus

// API RESULT CLASS
data class PokeListResult<R>(
    val loading: String = LoadingStatus.NOT_STARTED,
    val data: List<R> = listOf(),
    var errorMessage: String? = null
)

// API RESULT CLASS
data class PokeResult<R>(
    val loading: String = LoadingStatus.NOT_STARTED,
    val data: R? = null,
    var errorMessage: String? = null
)