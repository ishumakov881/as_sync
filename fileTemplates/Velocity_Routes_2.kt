#set($input = ${ROUTES})
#set($routes = $input.split(","))
#set($tabRoutes = ["home", "newmovies", "test", "movies", "series", "settings", "devices"])
#set($input123 = "home,newmovies")

sealed class Route(val route: String, val isTabItem: Boolean = true) {
#foreach($r in $routes)
    #set($rTrimmed = $r.trim())
    #set($isTab = $tabRoutes.contains($rTrimmed))
    #set($nameParts = $rTrimmed.split("_"))
    #set($className = "")
    #foreach($part in $nameParts)
        #set($className = "${className}${part.substring(0,1).toUpperCase()}${part.substring(1)}")
    #end
    #if($isTab)
    data object $className : Route("$rTrimmed")
    #else
    object $className : Route("$rTrimmed", false)
    #end
#end
}
