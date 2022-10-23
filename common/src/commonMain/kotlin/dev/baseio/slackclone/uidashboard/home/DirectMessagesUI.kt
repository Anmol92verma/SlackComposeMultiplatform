package dev.baseio.slackclone.uidashboard.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.baseio.slackclone.commonui.material.SlackSurfaceAppBar
import dev.baseio.slackclone.commonui.theme.SlackCloneSurface
import dev.baseio.slackclone.commonui.theme.SlackCloneColorProvider
import dev.baseio.slackclone.commonui.theme.SlackCloneTypography
import dev.baseio.slackclone.uichannels.directmessages.DMChannelsList
import dev.baseio.slackclone.uichannels.directmessages.DirectMessagesComponent
import dev.baseio.slackdomain.model.channel.DomainLayerChannels

@Composable
fun DirectMessagesUI(onItemClick: (DomainLayerChannels.SKChannel) -> Unit, component: DirectMessagesComponent) {
  SlackCloneSurface(
    color = SlackCloneColorProvider.colors.uiBackground,
    modifier = Modifier.fillMaxSize()
  ) {
    Column {
      DMTopAppBar()
      Spacer(modifier = Modifier.height(8.dp))
      JumpToText()
      Spacer(modifier = Modifier.height(12.dp))
      DMChannelsList(onItemClick, component)
    }
  }
}


@Composable
fun DMTopAppBar() {
  SlackSurfaceAppBar(
    title = {
      Text(
        text = "Direct Messages",
        style = SlackCloneTypography.h5.copy(color = Color.White, fontWeight = FontWeight.Bold)
      )
    },
    backgroundColor = SlackCloneColorProvider.colors.appBarColor,
  )
}
