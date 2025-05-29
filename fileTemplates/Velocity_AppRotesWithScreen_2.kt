#set($input = ${ROUTES})
#set($items = $input.split(","))
#set($screens = [])

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

sealed class Route(
    val route: String,
    val isTabItem: Boolean = true,
    val isSideMenu: Boolean = false
) {
#foreach($raw in $items)
    #set($parts = $raw.trim().split(":"))
    #if($parts.size() >= 4)
        #set($name = $parts[0].trim())
        #set($isTab = $parts[1].trim())
        #set($isSide = $parts[2].trim())
        #set($isScreen = $parts[3].trim())

        #set($nameParts = $name.split("_"))
        #set($className = "")
        #foreach($p in $nameParts)
            #set($className = "${className}${p.substring(0,1).toUpperCase()}${p.substring(1)}")
        #end

        #if($isTab == "true")
        data object $className : Route("$name", true, $isSide)
        #else
        object $className : Route("$name", false, $isSide)
        #end

        #if($isScreen == "true")
        #set($void = $screens.add($className))
        #end
    #end
#end
}

#foreach($screen in $screens)

@Composable
fun ${screen}Screen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$screen Screen",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
#end
