#set($input = ${ROUTES})
#set($items = $input.split(","))
#set($parsedRoutes = [])

#foreach($item in $items)
  #set($entry = $item.trim().split(":"))
  #if($entry.size() == 4)
    #set($routeName = $entry[0])
    #set($isTabItem = $entry[1])
    #set($isSideMenu = $entry[2])
    #set($isGenerateScreen = $entry[3])

    #set($classNameParts = $routeName.split("_"))
    #set($className = "")
    #foreach($part in $classNameParts)
      #set($className = "${className}${part.substring(0,1).toUpperCase()}${part.substring(1)}")
    #end

    #set($discard = $parsedRoutes.add({
      "routeName": "$routeName",
      "className": "$className",
      "isTabItem": "$isTabItem",
      "isSideMenu": "$isSideMenu",
      "isGenerateScreen": "$isGenerateScreen"
    }))
  #end
#end

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.scene

sealed class Route(
    val route: String,
    val isTabItem: Boolean = true,
    val isSideMenu: Boolean = false
) {
#foreach($r in $parsedRoutes)
    #if($r.isTabItem == "true")
    // 🔹 Used in bottom navigation
    #end
    data object $r.className : Route("$r.routeName", $r.isTabItem, $r.isSideMenu)
#end
}

// 🧭 Register all routes inside NavHost
fun addAllRoutes(): RouteBuilder.() -> Unit = {
#foreach($r in $parsedRoutes)
  #if($r.isGenerateScreen == "true")
    scene(route = Route.${r.className}.route) {
        ${r.className}Screen()
    }
  #end
#end
}

#foreach($r in $parsedRoutes)
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
