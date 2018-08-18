package br.com.fiap.pokedex.api
import android.content.Context
import android.util.Log
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientApi<T> {

    //Cara que retorna a instancia do retrofit para fazer a interface da api
    fun getClient(c: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .client(getOkhttpClientAuth().build())
                .baseUrl("https://pokedexdx.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(c)
    }

}

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val requestBuilder = chain!!.request().newBuilder()
        //Passando a senha da API
        requestBuilder.addHeader("Authorization", "Basic cG9rZWFwaTpwb2tlbW9u")
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (response.code() == 401) {
            Log.e("MEUAPP", "Error API KEY")
        }
        return response
    }
}


private var picasso: Picasso? = null

fun getPicassoAuth(context: Context): Picasso {
    if(picasso == null) {
        picasso = Picasso
                .Builder(context)
                .downloader(OkHttp3Downloader(getOkhttpClientAuth().build()))
                .build()
    }
    return picasso!!

}

fun getOkhttpClientAuth(): OkHttpClient.Builder {
    val builder = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
    return builder
}

//Interface que vou utilizar nas outras telas
fun getPokemonApi(): PokemonAPI {
    return ClientApi<PokemonAPI>().getClient(PokemonAPI::class.java)
}
