Scala.js Really Practical Guide

Please checkout <https://github.com/katrinsharp/scalajs-rpg/wiki> for explanation about the steps of the tutorial. 

How to run the final result - master branch:

How to run this thing in DEV:

You are gonna need 2 consoles open:

```
1. sbt ~fastOptJS
2. 
  a. sbt
  b. crossJVM/run
```

Now you can open index-fastOpt.html from root directory in your web browser


How to create a release version for your client side:

```
1. sbt
  a. fullOptJS
  b. project crossJS
  c. releaseJs
```

Now you can open index.html from root directory in your web browser

You will still have to run your server as noted in prevous note.

