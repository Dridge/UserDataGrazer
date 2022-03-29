package me.richardeldridge.shared.pojos.users

import com.google.gson.annotations.SerializedName

/**
 * @property itemCount
 * @property totalPages
 * @property currentPage
 */
data class Meta(
        @SerializedName("item_count")
        var itemCount: Int? = null,
        @SerializedName("total_pages")
        var totalPages: Int? = null,
        @SerializedName("current_page")
        var currentPage: Int? = null
)
