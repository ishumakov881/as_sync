#set($input = ${ROUTES})
#set($rawRoutes = $input.split(","))
#set($routes = [])

#foreach($item in $rawRoutes)
    #set($parts = $item.trim().split(":"))
    #set($routeName = $parts[0].trim())
    #set($isTabItem = $parts.size() > 1 ? $parts[1].trim() : "true")
    #set($isSideMenu = $parts.size() > 2 ? $parts[2].trim() : "false")
    #set($isGenerateScreen = $parts.size() > 3 ? $parts[3].trim() : "false")

    #set($nameParts = $routeName.split("_"))
    #set($className = "")
    #foreach($part in $nameParts)
        #set($className = "${className}${part.substring(0,1).toUpperCase()}${part.substring(1)}")
    #end

    #set($void = $routes.add({
        "routeName" : $routeName,
        "className" : $className,
        "isTabItem" : $isTabItem,
        "isSideMenu" : $isSideMenu,
        "isGenerateScreen" : $isGenerateScreen
    }))
#end

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class Route(
    val route: String,
    val isTabItem: Boolean = true,
    val isSideMenu: Boolean = false
) {
#foreach($r in $routes)

    #if($r.isTabItem == "true")
    // 🔹 Used in bottom navigation
    #end
    #if($r.isSideMenu == "true")
    // 📂 Side menu
    #end
    data object $r.className : Route("$r.routeName", $r.isTabItem, $r.isSideMenu)

#end
}

fun NavGraphBuilder.addAllRoutes() {
#foreach($r in $routes)
  #if($r.isGenerateScreen == "true")
    composable(Route.${r.className}.route) {
        ${r.className}Screen()
    }
  #end
#end
}

#foreach($r in $routes)
#if($r.isGenerateScreen == "true")

@Composable
fun ${r.className}Screen() {
    // TODO: Implement ${r.className} screen UI
}

@Preview(showBackground = true)
@Composable
fun ${r.className}ScreenPreview() {
    ${r.className}Screen()
}

#end
#end
