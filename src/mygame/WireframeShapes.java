package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 * test
 * @author normenhansen
 */
public class WireframeShapes extends SimpleApplication {

    public static void main(String[] args) {
        WireframeShapes app = new WireframeShapes();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        //Box b = new Box(1, 1, 1);
        //Geometry geom = new Geometry("Box", b);

        Sphere mesh = new Sphere(16, 16, 1f);
        Geometry geom2 = new Geometry("Sphere", mesh);
        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");        
        mat.setColor("Color", ColorRGBA.Blue);
        
        //turn on wireframe for mat
        mat.getAdditionalRenderState().setWireframe(true);
        
        //geom.setMaterial(mat);
        geom2.setMaterial(mat);

        //rootNode.attachChild(geom);
        rootNode.attachChild(geom2);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
