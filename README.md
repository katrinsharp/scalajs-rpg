Scala.js Really Practical Guide

How to run this thing in DEV:

You are gonna need 2 consoles open:

1. sbt ~fastOptJS
2. 
  a. sbt
  b. crossJVM/run

Now you can access /'path-to-folder'/scalajs-rpg/index-fastOpt.html


How to create a release version for your client side:

1. sbt
  a. fullOptJS
  b. project crossJS
  c. releaseJs

Now you can access /'path-to-folder'/scalajs-rpg/index.html 

You will still have to run your server as noted in prevous note. I'll add server side packaging soon.  


If you want to follow the steps as outlined in the presentation (sbtb-2015.md in the current directory),
there are git tags that you can use.  
 


