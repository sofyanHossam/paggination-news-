package com.example.jetpackapi.presention.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.jetpackapi.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetpackapi.domain.model.Article
import com.example.jetpackapi.presention.Dimens.ArticleCardSize
import com.example.jetpackapi.presention.Dimens.ExtraSmallPadding
import com.example.jetpackapi.presention.Dimens.ExtraSmallPadding2
import com.example.jetpackapi.presention.Dimens.SmallIconSize

@Composable
fun ArticleCard(
    modifier: Modifier= Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
){
    val context = LocalContext.current
    Row(modifier=modifier.clickable { onClick?.invoke() }
    ,) {
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                )
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.LightGray
                    )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(painter =  painterResource(id = R.drawable.ic_time ),
                    contentDescription =null,
                    modifier=Modifier.size(SmallIconSize) ,
                    tint = colorResource(id = R.color.body)

                    )
                Spacer(modifier = Modifier.width(ExtraSmallPadding))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.body)
                )
            }

        }

    }

}

