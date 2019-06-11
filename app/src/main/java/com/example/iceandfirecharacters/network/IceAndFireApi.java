package com.example.iceandfirecharacters.network;

import com.example.iceandfirecharacters.model.Book;
import com.example.iceandfirecharacters.model.Character;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * IceAndFireApi
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 */
public interface IceAndFireApi {

    String BASE_URL = "https://www.anapioficeandfire.com/api/";

    @GET("books")
    Single<List<Book>> getBooks(
            @Query("page") int page,
            @Query("per_page") int perPage
    );

    @GET("characters")
    Single<List<Character>> getCharacters(
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}
