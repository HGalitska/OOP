package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer;

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
        fish.setLocalTranslation(0f, 0f, 1f); //placing ship in the scene center
        fish.rotate(0, 1.5f, 1.5f); //placing ship in the scene center
        fish.scale(0.5f);
        rootNode.attachChild(fish); //adding ship to the scene
        
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
        ship.setLocalRotation(q);
        Vector3f forward = q.mult(Vector3f.UNIT_Z);
        ship.move(forward.mult(tpf).mult(speed));

    }
}
