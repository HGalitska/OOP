package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class Main extends SimpleApplication {
    Spatial ship; //main model of my game
    Spatial fish;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    public final Vector3f getForward(Node node)
    {
        Vector3f forward = new Vector3f(0,0, 1);
        node.localToWorld(forward,forward);
        return forward;
    }

    @Override
    public void simpleInitApp() {   
        viewPort.setBackgroundColor(ColorRGBA.Blue);
        flyCam.setEnabled(false); //camera doesn't move on mouse move
        
        cam.setLocation(new Vector3f(0f, 50f, 0f)); //camera is high above
        cam.lookAt(cam.getLocation().subtract(Vector3f.UNIT_Y), Vector3f.UNIT_Y); //creates a top view
        
        ship = assetManager.loadModel("Models/Models/kapal/Models/kapal.j3o"); //loading ship model
        ship.setLocalTranslation(0f, 0f, 0f); //placing ship in the scene center
        ship.scale(0.7f);
        rootNode.attachChild(ship); //adding ship to the scene
        
        fish = assetManager.loadModel("Models/Models/fish/Models/fish.j3o"); //loading torpedo model
        fish.setLocalTranslation(0f, 0f, -5f);
        fish.scale(0.5f);
        rootNode.attachChild(fish); //adding fish to the scene
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(0, -1, 0));
        rootNode.addLight(dl); //adding light to the scene
    }

    @Override
    public void simpleUpdate(float tpf) {
        Vector3f mousePos = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0); //getting cursor position in world coordinates
        mousePos.y = 0;

        Quaternion q = new Quaternion();
        q.lookAt(mousePos, Vector3f.UNIT_Y);
        rootNode.setLocalRotation(q);
        Vector3f forward = q.mult(Vector3f.UNIT_Z);
        rootNode.move(forward.mult(tpf).mult(speed));
        
       
        Vector3f shipPosition = ship.getLocalTranslation();
        Quaternion q1 = new Quaternion();
        q1.lookAt(shipPosition, Vector3f.UNIT_Y);
        fish.setLocalRotation(q1);
        fish.rotate(0, 0, 1f);
    }
}
