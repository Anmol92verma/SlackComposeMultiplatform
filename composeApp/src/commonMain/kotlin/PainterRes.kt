import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.*

object PainterRes {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    internal fun gettingStarted(): Painter {
        return painterResource(DrawableResource("gettingstarted.png"))
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    internal fun slackLogo(): Painter {
        return painterResource(DrawableResource("ic_launcher_foreground.png"))
    }
}
