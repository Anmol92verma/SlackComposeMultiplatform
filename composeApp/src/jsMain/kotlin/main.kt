import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.baseio.slackclone.RootComponent
import dev.baseio.slackclone.SlackApp
import dev.baseio.slackclone.commonui.theme.SlackCloneTheme
import org.jetbrains.skiko.wasm.onWasmReady

val lifecycle = LifecycleRegistry()
val rootComponent by lazy {
    RootComponent(
        context = DefaultComponentContext(lifecycle = lifecycle),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("SlackCMP") {
            SlackCloneTheme {
                SlackApp {
                    rootComponent
                }
            }
        }
    }
}
