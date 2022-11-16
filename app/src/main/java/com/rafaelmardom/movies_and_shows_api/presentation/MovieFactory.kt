package com.rafaelmardom.movies_and_shows_api.presentation

import android.content.Context
import android.content.SharedPreferences
import com.rafaelmardom.movies_and_shows_api.data.ApiClient
import com.rafaelmardom.movies_and_shows_api.data.MovieDataRepository
import com.rafaelmardom.movies_and_shows_api.data.local.xml.MovieXmlLocalDataSource
import com.rafaelmardom.movies_and_shows_api.data.remote.api.MovieApiRemoteDataSource
import com.rafaelmardom.movies_and_shows_api.domain.GetMoviesFeedUseCase
import com.rafaelmardom.movies_and_shows_api.domain.MovieRepository

class MovieFactory {
    /*
    private fun getApiClient() = ApiClient()

    private fun getSharedPreferences(
        context: Context,
        nameSharedPreferences: String
    ): SharedPreferences {
        return context.getSharedPreferences(nameSharedPreferences, Context.MODE_PRIVATE)
    }
    */

    fun getMoviesViewModel(applicationContext: Context) : MovieFeedViewModel {
        return MovieFeedViewModel(getMovieFeedUseCase(applicationContext))
    }

    private fun getMovieFeedUseCase(context: Context): GetMoviesFeedUseCase {
        return GetMoviesFeedUseCase(
            getMovieRepository(context)
        )
    }
    fun getMovieRepository(context: Context): MovieRepository {
        return MovieDataRepository(
            MovieXmlLocalDataSource(
                //getSharedPreferences(context, "moviesSharedPreferences")
                context.getSharedPreferences("movies", Context.MODE_PRIVATE)
            ),
            //MovieApiRemoteDataSource(getApiClient())
            MovieApiRemoteDataSource(
                ApiClient()
            )
        )
    }
}