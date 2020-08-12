package saxal.me.saxapokedex.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val moshi = Moshi.Builder()
    // ... add your own JsonAdapters and factories ...
    .add(KotlinJsonAdapterFactory())
    .build()

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

var pokeService: PokeApiService = retrofit.create<PokeApiService>(PokeApiService::class.java)