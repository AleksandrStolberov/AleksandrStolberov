package com.example.pix.data.flickr

import android.util.Log
import com.example.pix.data.flickr.mapper.toEntity
import com.example.pix.domain.entity.Picture

class FlickrRepository(
    private val flickrApi: FlickrApi
) {
    suspend fun search(
        text: String = "cats",
        page: Int = 1,
        count: Int = 100
    ): Result<List<Picture>> = runCatching {
        val result = flickrApi.search(text, page, count)
        result.photos?.photo?.map {
            it.toEntity(it.title)
        } ?: error("Error in repository: ${result.message}")
    }
}