package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;

public class Main extends SimpleApplication {
    Spatial ship;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);
        cam.setLocation(new Vector3f(0f, 50f, 0f));
        cam.lookAt(cam.getLocation().subtract(Vector3f.UNIT_Y), Vector3f.UNIT_Y);
        
        ship = assetManager.loadModel("Models/Models/kapal/Models/kapal.j3o");
        ship.setLocalTranslation(0f, -5f, 0f);
        //ChaseCamera chase_cam = new ChaseCamera(cam, ship);
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.1f));
        rootNode.addLight(al);

        rootNode.attachChild(ship);
    }

    @Override
    public void simpleUpdate(float tpf) {
        ship.move(2*tpf, tpf*0, 0f);
        //cam.lookAt(ship.getWorldTranslation(), Vector3f.UNIT_Y);
    }
}
