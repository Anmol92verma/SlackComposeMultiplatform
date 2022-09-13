package dev.baseio.slackclone.uichannels.createsearch

import ViewModel
import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackdomain.mappers.UiModelMapper
import dev.baseio.slackdomain.model.channel.DomainLayerChannels
import dev.baseio.slackdomain.usecases.channels.UseCaseFetchChannelCount
import dev.baseio.slackdomain.usecases.channels.UseCaseSearchChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SearchChannelsVM constructor(
  private val ucFetchChannels: UseCaseSearchChannel,
  private val useCaseFetchChannelCount: UseCaseFetchChannelCount,
  private val chatPresentationMapper: UiModelMapper<DomainLayerChannels.SKChannel, UiLayerChannels.SKChannel>
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

  private fun flow(search: String) = ucFetchChannels.performStreamingNullable(search).map { channels ->
    channels.map { channel ->
      chatPresentationMapper.mapToPresentation(channel)
    }
  }

  fun search(newValue: String) {
    search.value = newValue
    channels.value = flow(newValue)
  }

}