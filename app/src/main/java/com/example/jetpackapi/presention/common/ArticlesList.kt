package com.example.jetpackapi.presention.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.jetpackapi.domain.model.Article
import com.example.jetpackapi.presention.Dimens.ExtraSmallPadding2
import com.example.jetpackapi.presention.Dimens.MediumPadding1
import com.example.jetpackapi.presention.EmptyScreen

@Composable
fun ArticlesList(
    modifier: Modifier=Modifier,
    articles: LazyPagingItems<Article>,
    onClick :(Article)->Unit
){
    val handelPagingResult= handelPagingResult(article = articles)
    if (handelPagingResult){
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)

        ){
            items (
                count =articles.itemCount
                    ) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier=Modifier,
    articles: List<Article>,
    onClick :(Article)->Unit
){

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)

        ){
            items (
                count =articles.size
            ) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onClick(article) })
                }
            }
        }

}


@Composable
fun handelPagingResult
            (article: LazyPagingItems<Article>):Boolean
{

    val loadState =article.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
         else-> null
    }
    return when{
        loadState.refresh is LoadState.Loading->{
            ShimmerEffect()
            false
        }
        error!=null->{

            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }

}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}

