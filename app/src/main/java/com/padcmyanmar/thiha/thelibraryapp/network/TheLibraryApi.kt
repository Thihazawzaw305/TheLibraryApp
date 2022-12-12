package com.padcmyanmar.thiha.thelibraryapp.network

import com.padcmyanmar.thiha.thelibraryapp.network.responses.BookListResponse
import com.padcmyanmar.thiha.thelibraryapp.network.responses.GoogleBookListResponse
import com.padcmyanmar.thiha.thelibraryapp.network.responses.MoreBooksResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface TheLibraryApi {

    @GET(API_GET_BOOK_LIST)
    fun getBookList(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
    ): Observable<BookListResponse>

    @GET(API_GET_MORE_BOOKS)
    fun getBookListByListName(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(PARAM_LIST) list: String
    ): Observable<MoreBooksResponse>

    @GET
    fun searchBooks(
        @Url url : String = GOOGLE_BASED_URL,
        @Query(PARAM_QUERY) query: String,
    ) : Observable<GoogleBookListResponse>
}