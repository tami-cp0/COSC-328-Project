package com.notesapp

data class Note(
    val id: Long = System.currentTimeMillis(),
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun getFormattedDate(): String {
        val date = java.util.Date(timestamp)
        val format = java.text.SimpleDateFormat("MMM dd, yyyy HH:mm", java.util.Locale.getDefault())
        return format.format(date)
    }
}
