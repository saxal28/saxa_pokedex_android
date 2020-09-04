package saxal.me.saxapokedex.api

import android.util.Log
import okhttp3.*
import saxal.me.saxapokedex.MainActivity

// intercepts API to return mock json generated from node script

class MockJsonInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri()
        Log.i(TAG, uri.toString())

        try {
            Log.d(TAG, uri.path.substring(1))
            val filename = uri.path.substring(1).replace('/', '-') + ".json"
            val responseBody = MainActivity.contextInstance!!.assets.open(filename).bufferedReader().use { it.readText() }

            Log.d(TAG, "Using mock data for ${uri.path}")

            return Response.Builder()
                .code(200)
                .message(responseBody)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseBody.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } catch (ex: Exception) {
            Log.d(TAG, "Using real API data for ${uri.path}")
            return chain.proceed(chain.request())
        }
    }

    companion object {
        const val TAG = "MockJsonInterceptor"
    }
}