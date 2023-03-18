package com.example.bocchitv.Models.Animepahe

data class AnimePaheEpisosdeResult(
    val current_page: Int? = null,
    val `data`: List<Data?>? = null,
    val from: Int? = null,
    val last_page: Int? = null,
    val next_page_url: Any? = null,
    val per_page: Int? = null,
    val prev_page_url: Any? = null,
    val to: Int? = null,
    val total: Int? = null
) {
    data class Data(
        val anime_id: Int? = null,
        val audio: String? = null,
        val created_at: String? = null,
        val disc: String? = null,
        val duration: String? = null,
        val edition: String? = null,
        val episode: Int? = null,
        val episode2: Int? = null,
        val filler: Int? = null,
        val id: Int? = null,
        val session: String? = null,
        val snapshot: String? = null,
        val title: String? = null
    )
}