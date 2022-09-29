package com.jetapps.jettaskboard.component.multifab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MultiFloatingActionButton(
  modifier: Modifier = Modifier,
  items: List<MultiFabItem>,
  fabState: MutableState<MultiFabState> = rememberMultiFabState(),
  fabIcon: FabIcon,
  fabOption: FabOption = FabOption(),
  onFabItemClicked: (fabItem: MultiFabItem) -> Unit,
  stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
  val rotation by animateFloatAsState(
    if (fabState.value == MultiFabState.Expand) fabIcon.iconRotate ?: 0f else 0f
  )

  Column(
    modifier = modifier.wrapContentSize(),
    horizontalAlignment = Alignment.End
  ) {
    AnimatedVisibility(
      visible = fabState.value.isExpanded(),
      enter = fadeIn() + expandVertically(),
      exit = fadeOut()
    ) {
      LazyColumn(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(15.dp)
      ) {
        items(items = items, key = { it.id }) { item ->
          MiniFabItem(
            item = item,
            fabOption = fabOption,
            onFabItemClicked = onFabItemClicked
          )
        }

        item {} // for spacing
      }
    }
    FloatingActionButton(
      onClick = {
        fabState.value = fabState.value.toggleValue()
        stateChanged(fabState.value)
      },
      backgroundColor = fabOption.backgroundTint,
      contentColor = fabOption.iconTint
    ) {
      Icon(
        painter = painterResource(fabIcon.iconRes),
        modifier = Modifier.rotate(rotation),
        contentDescription = null,
        tint = fabOption.iconTint
      )
    }
  }
}

@Composable
private fun MiniFabItem(
  item: MultiFabItem,
  fabOption: FabOption,
  onFabItemClicked: (item: MultiFabItem) -> Unit
) {
  Row(
    modifier = Modifier
      .wrapContentSize()
      .padding(end = 10.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    if (fabOption.showLabels) {
      Text(
        item.label,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .background(color = MaterialTheme.colors.surface)
          .padding(horizontal = 6.dp, vertical = 4.dp)
      )
    }
    FloatingActionButton(
      modifier = Modifier.size(40.dp),
      onClick = { onFabItemClicked(item) },
      backgroundColor = fabOption.backgroundTint,
      contentColor = fabOption.iconTint
    ) {
      Icon(
        painter = painterResource(item.iconRes),
        contentDescription = null,
        tint = fabOption.iconTint
      )
    }
  }
}