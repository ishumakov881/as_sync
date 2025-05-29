#set($input = ${ROUTES})
#set($entries = $input.split(","))
#set($seen = [])
#foreach($e in $entries)
  #set($trimmed = $e.trim())
  #if(!$seen.contains($trimmed))
    #set($void = $seen.add($trimmed))
  #end
#end

// Parsed routes:
#foreach($r in $seen)
  #set($parts = $r.split(":"))
  routeName = "$parts[0]"  
  isTabItem = "$!parts.get(1)"
  isSideMenu = "$!parts.get(2)"
  isGenerateScreen = "$!parts.get(3)"
#end
