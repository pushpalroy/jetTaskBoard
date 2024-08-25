package com.jetapps.jettaskboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.jetapps.jettaskboard.core.designsystem.R
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.labelModelList

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    card: CardModel,
    isExpandedScreen: Boolean
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color(0xFF2c2c2e)
    ) {
        Column(
            modifier = Modifier
        ) {
            card.coverImageUrl?.let { safeCoverImageUrl ->
                if (safeCoverImageUrl.isNotEmpty()) {
                    Image(
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth(),
                        painter = rememberAsyncImagePainter(safeCoverImageUrl),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Cover Image"
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                // Todo(Niket) : Remove labels later.
                // Just for demonstration purpose
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    for (label in labelModelList) {
                        Box(
                            modifier = Modifier
                                .width(32.dp)
                                .height(16.dp)
                                .clip(RoundedCornerShape(10))
                                .background(color = Color(label.labelColor))
                        )
                    }
                }

//                card.labels.let { cardLabels ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(4.dp)
//                    ) {
//                        for (label in cardLabels) {
//                            Box(
//                                modifier = Modifier
//                                    .width(32.dp)
//                                    .height(16.dp)
//                                    .clip(RoundedCornerShape(10))
//                                    .background(color = Color(label.labelColor))
//                            )
//                        }
//                    }
//                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier,
                    text = card.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                card.description?.let { safeDescription ->
                    if (safeDescription.isNotEmpty() && isExpandedScreen) {
                        Text(
                            modifier = Modifier,
                            text = safeDescription,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    card.description?.let { safeDescription ->
                        if (safeDescription.isNotEmpty()) {
                            Icon(
                                modifier = Modifier,
                                painter = painterResource(id = R.drawable.ic_notes),
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                    }
                    Icon(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .rotate(130f),
                        painter = painterResource(id = R.drawable.ic_attachment),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
