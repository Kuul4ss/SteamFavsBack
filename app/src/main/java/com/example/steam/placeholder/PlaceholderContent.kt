package com.example.steam.fragment.placeholder

import kotlin.collections.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()


    private val COUNT = 25

    fun add(name: String, editor: ArrayList<String>?, price: String, id: String, description: String) {
        var ediFinal = editor?.get(0)
        if (editor != null) {
            for(s in editor.subList(1, editor.size)) {
                ediFinal = "$ediFinal, $s"
            }
        }
        if (ediFinal != null) {
            addItem(name, ediFinal, price, id, description)
        }
    }

    private fun addItem(name: String, editor: String, price: String, id: String, description: String) {
        ITEMS.add(PlaceholderItem(name, editor, price, id, description))
        println(ITEMS[ITEMS.size-1])
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return PlaceholderItem(position.toString(), "Item " + position, makeDetails(position), "", "")

    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val name: String, val editor: String, val price: String, val id: String, val description: String) {
        override fun toString(): String {
            return "PlaceholderItem(name='$name', editor='$editor', price='$price', id='$id', id='$description')"
        }
    }
}