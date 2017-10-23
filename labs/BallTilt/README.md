Created very basic Android Application: ball moving on device's tilt. Used Accelerometer Sensor.
Graphic is implemented using android.graphics package. 

First I set main view of the app. Then I get display bounds, for my ball to move only on visible part.
I'm setting ball's initial position to the center of screen. 
Ball has two main characteristics: position and speed. They are changing due to device's tilt.
To make it do that, I added a listener to sensor of type accelerometer. On every device move it changes ball's speed.

Then I implemented a TimerTask update loop. It changes ball's position due to new speed, received from sensor listener.
If its new position is supposed to be out of display's bounds, i make it appear on the opposite side of screen.


