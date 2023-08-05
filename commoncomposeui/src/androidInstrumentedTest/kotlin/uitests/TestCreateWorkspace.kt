package uitests

import SlackAppSetup
import SlackAppSetupImpl
import UiAutomation
import UiAutomationDelegateImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Test
import screens.dashboardScreenRobot
import screens.emailAddressInputRobot
import screens.gettingStartedRobot

class TestCreateWorkspace : SlackAppSetup by SlackAppSetupImpl(),
    FakeDependencies by FakeSlackAppDependencies(),
    UiAutomation by UiAutomationDelegateImpl() {


    @Before
    fun setup() {
        runBlocking {
            setupFakeNetwork()
        }
    }

    @Test
    fun testCreateWorkspaceFlow(): Unit = runBlocking {
        with(rule) {
            setAppContent()
            awaitIdle()

            gettingStartedRobot {
                sendMagicLink()
            }

            emailAddressInputRobot {
                enterEmailAndSubmit()
                enterWorkspaceAndSubmit()
            }

            withContext(Dispatchers.Main) {
                rootComponent.navigateAuthorizeWithToken("faketoken")
            }

            dashboardScreenRobot {
                testWorkspaceIsLoaded()
            }
        }
    }

    private suspend fun setupFakeNetwork() {
        fakeLocalKeyValueStorage()

        fakeCurrentLoggedinUser()

        fakeSendMagicLink()

        fakeGetWorkspaces()

        fakeDMChannels()

        fakePublicChannels()

        fakeListenToChangeInMessages()

        fakeListenToChangeInUsers()

        fakeListenToChangeInChannels()

        fakeListenToChangeInDMChannels()

        fakeListenToChangeInChannelMembers()
    }

}