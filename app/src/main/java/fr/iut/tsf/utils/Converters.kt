package fr.iut.tsf.utils

import android.content.Context
import androidx.databinding.InverseMethod
import fr.iut.tsf.R

object Converters {
    @JvmStatic
    @InverseMethod("stringToFloat")
    fun floatToString(value: Float) = if (value == 0f) "" else value.toString()

    @JvmStatic
    fun notesColor(context: Context, value: Int?) = value?.let {
        context.resources.getIntArray(R.array.notes_couleur)[it]
    } ?: 0
}