#set($input = ${ROUTES})
#set($items = $input.split(","))

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class Route(
    val route: String,
    val isTabItem: Boolean = true,
    val isSideMenu: Boolean = false
) {
#foreach($entry in $items)
    #set($parts = $entry.trim().split(":"))
    #set($name = $parts[0].trim())
    #set($isTabItem = $parts[1].trim())
    #set($isSideMenu = $parts[2].trim())
    #set($isGenerateScreen = $parts[3].trim())
    #set($nameParts = $name.split("_"))
    #set($className = "")
    #foreach($part in $nameParts)
        #set($className = "$className${part.substring(0,1).toUpperCase()}${part.substring(1)}")
    #end
    #if($isTabItem == "true")
    // 🔹 Used in bottom navigation
    data object $className : Route("$name", true, $isSideMenu)
    #else
    // 🔸 Not in bottom navigation
    object $className : Route("$name", false, $isSideMenu)
    #end
#end
}


// 🧭 Navigation Graph builder
fun NavGraphBuilder.addAllRoutes() {
#foreach($entry in $items)
    #set($parts = $entry.trim().split(":"))
    #set($name = $parts[0].trim())
    #set($isGenerateScreen = $parts[3].trim())
    #set($nameParts = $name.split("_"))
    #set($className = "")
    #foreach($part in $nameParts)
        #set($className = "$className${part.substring(0,1).toUpperCase()}${part.substring(1)}")
    #end
    #if($isGenerateScreen == "true")
    composable(Route.$className.route) {
        ${className}Screen()
    }
    #end
#end
}


// 🧱 Screens & Previews
#foreach($entry in $items)
    #set($parts = $entry.trim().split(":"))
    #set($name = $parts[0].trim())
    #set($isGenerateScreen = $parts[3].trim())
    #set($nameParts = $name.split("_"))
    #set($className = "")
    #foreach($part in $nameParts)
        #set($className = "$className${part.substring(0,1).toUpperCase()}${part.substring(1)}")
    #end
    #if($isGenerateScreen == "true")
@Composable
fun ${className}Screen() {
    // TODO: Implement $className screen UI
}

@Preview(showBackground = true)
@Composable
fun ${className}ScreenPreview() {
    ${className}Screen()
}

    #end
#end
