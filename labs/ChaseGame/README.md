In my "game" I've implemented ship which is chasing mouse cursor and is being followed by a torpedo.
I used top-view, for game to be visually logical. 

First I load models of ship and torpedo, using assertManager and setup camera and objects' positions for the initial scene.

Main logic is in game's update loop. There I get world coordinates of mouse cursor.
Then I rotate ship so that it can face cursor`s position. After rotation I get ship`s forward vector and move it in cursor`s direction.
In the end of update loop I simply make torpedo look at ship`s position.
