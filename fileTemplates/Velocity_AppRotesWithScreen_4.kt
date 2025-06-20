#set($input = ${ROUTES})
#set($rawRoutes = $input.split(","))
#set($routes = [])

#foreach($item in $rawRoutes)
  #set($parts = $item.trim().split(":"))
  #set($routeName = $parts[0].trim())

  #if($parts.size() > 1)
    #set($isTabItem = $parts[1].trim())
  #else
    #set($isTabItem = "true")
  #end

  #if($parts.size() > 2)
    #set($isSideMenu = $parts[2].trim())
  #else
    #set($isSideMenu = "false")
  #end

  #if($parts.size() > 3)
    #set($isGenerateScreen = $parts[3].trim())
  #else
    #set($isGenerateScreen = "false")
  #end

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
