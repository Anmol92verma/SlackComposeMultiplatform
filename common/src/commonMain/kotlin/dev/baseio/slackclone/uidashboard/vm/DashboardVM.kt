package dev.baseio.slackclone.uidashboard.vm

import dev.baseio.slackclone.chatcore.data.UiLayerChannels
import dev.baseio.slackdomain.model.workspaces.DomainLayerWorkspaces
import dev.baseio.slackdomain.usecases.workspaces.UseCaseGetSelectedWorkspace
import kotlinx.coroutines.flow.*
import ViewModel

class DashboardVM(private val useCaseGetSelectedWorkspace: UseCaseGetSelectedWorkspace) : ViewModel() {
  val selectedChatChannel = MutableStateFlow<UiLayerChannels.SKChannel?>(null)
  var selectedWorkspace = MutableStateFlow<DomainLayerWorkspaces.SKWorkspace?>(null)
  val isChatViewClosed = MutableStateFlow(true)

  init {
    useCaseGetSelectedWorkspace.invokeFlow().onEach {
      if (selectedWorkspace.value != it) {
        selectedChatChannel.value = null
        isChatViewClosed.value = true
      }
      selectedWorkspace.value = it
    }.launchIn(viewModelScope)
  }

}