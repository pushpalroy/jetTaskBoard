package com.jetapps.jettaskboard.component.multifab

import androidx.annotation.DrawableRes

/**
 * @param id Cannot be duplicated with the [id] value of another [MultiFabItem].
 */
data class MultiFabItem(
  val id: Int,
  @DrawableRes val iconRes: Int,
  val label: String = ""
)