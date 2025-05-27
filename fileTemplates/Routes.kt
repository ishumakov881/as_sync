#set($routes = [
    {"name": "Genres", "route": "genres", "isTabItem": false},
    {"name": "Logout", "route": "logout", "isTabItem": false},
    {"name": "Bestsellers", "route": "home", "isTabItem": true},
    {"name": "Newmovies", "route": "newmovies", "isTabItem": true},
    {"name": "Test", "route": "test", "isTabItem": true},
    {"name": "Movies", "route": "movies", "isTabItem": true},
    {"name": "Series", "route": "series", "isTabItem": true},
    {"name": "Settings", "route": "settings", "isTabItem": true},
    {"name": "Devices", "route": "devices", "isTabItem": true}
])

sealed class Route(
    val route: String,
    val isTabItem: Boolean = true
) {
#foreach ($item in $routes)
    #if ($item.isTabItem)
    data object ${item.name} : Route("${item.route}")
    #else
    object ${item.name} : Route("${item.route}", false)
    #end
#end
}