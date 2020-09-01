package saxal.me.saxapokedex.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val moshi: Moshi = Moshi.Builder()
    // ... add your own JsonAdapters and factories ...
    .add(KotlinJsonAdapterFactory())
    .build()

val client = OkHttpClient.Builder()
    .addInterceptor(MockJsonInterceptor())
    .build()

var retrofit: Retrofit = Retrofit.Builder()
    .client(client)
    .baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

var pokeService: PokeApiService = retrofit.create<PokeApiService>(PokeApiService::class.java)