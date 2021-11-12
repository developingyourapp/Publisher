package com.hitech.my.publisher.data.dto

import com.google.gson.annotations.SerializedName
import com.hitech.my.publisher.domain.model.ArticleInfo

data class ArticleDto(
    val copyright: String,
    @SerializedName("num_results")
    val numberOfResult: Int,
    val results: List<Result>,
    val status: String
)

data class Result(
    @SerializedName("abstract")
    val summery: String,
    @SerializedName("adx_keywords")
    val adxKeywords: String,
    @SerializedName("asset_id")
    val assetId: Long,
    val byline: String,
    val column: Any,
    @SerializedName("des_facet")
    val desFacet: List<String> = emptyList(),
    val id: Long,
    @SerializedName("nytdsection")
    val dSection: String,
    @SerializedName("published_date")
    val publishedDate: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String,
    @SerializedName("org_facet")
    val orgFacet: List<String> = emptyList(),
    @SerializedName("per_facet")
    val perFacet: List<String> = emptyList(),
    @SerializedName("geo_facet")
    val geoFacet: List<String> = emptyList(),
    val media: List<Media> = emptyList(),
    @SerializedName("eta_id")
    val etaId: Int
)

data class Media(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    @SerializedName("approved_for_syndication")
    val approvedForSyndication : Int,
    @SerializedName("media-metadata")
    val mediaMetadata : List<MediaMetadata> = emptyList()
)

data class MediaMetadata(
    val url: String,
    val format: String,
    val height: Int,
    val weight: Int
)

fun Result.toArticleInfo(): ArticleInfo {
    return ArticleInfo(
        url = this.url,
        summery = this.summery,
        title = this.title,
        publishedDate = this.publishedDate,
        byline = this.byline,
        thumbnailUrl = if (this.media.isNotEmpty()){
            this.media[0].mediaMetadata[0].url
        } else {
            ""
        }
    )
}
