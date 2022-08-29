package dev.baseio.slackclone.uichannels.createsearch

import ViewModel
import androidx.paging.map
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackclone.domain.mappers.UiModelMapper
import dev.baseio.slackclone.domain.model.channel.DomainLayerChannels
import dev.baseio.slackclone.domain.usecases.channels.UseCaseFetchChannelCount
import dev.baseio.slackclone.domain.usecases.channels.UseCaseSearchChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchChannelsVM constructor(
  private val ucFetchChannels: UseCaseSearchChannel,
  private val useCaseFetchChannelCount: UseCaseFetchChannelCount,
  private val chatPresentationMapper: UiModelMapper<DomainLayerChannels.SlackChannel, UiLayerChannels.SlackChannel>
) : ViewModel() {

  val search = MutableStateFlow("")
  val channelCount = MutableStateFlow(0)

  var channels = MutableStateFlow(flow(""))

  init {
    viewModelScope.launch {
      val count = useCaseFetchChannelCount.perform()
      channelCount.value = count
    }
  }

  private fun flow(search: String) = ucFetchChannels.performStreaming(search).map { channels ->
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }
  }

  fun search(newValue: String) {
    search.value = newValue
    channels.value = flow(newValue)
  }

  fun navigate(channel: UiLayerChannels.SlackChannel) {
    TODO("pending navigateBackWithResult")
    /*composeNavigator.navigateBackWithResult(
      NavigationKeys.navigateChannel,
      channel.uuid!!,
      SlackScreen.Dashboard.name
    )*/
  }

}