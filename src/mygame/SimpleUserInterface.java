package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.ui.Picture;

/**
 * test
 * @author normenhansen
 */
public class SimpleUserInterface extends SimpleApplication {
    
    private float distance = 0;
    private BitmapText distanceText;
    Picture logo;

    public static void main(String[] args) {
        SimpleUserInterface app = new SimpleUserInterface();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        setDisplayStatView(false);
        setDisplayFps(false);
        
        Picture frame = new Picture("User interface frame");
        frame.setImage(assetManager, "Interface/frame.png", false);
        frame.move(settings.getWidth()/2-265, 0, -2);
        frame.setWidth(530);
        frame.setHeight(10);
        guiNode.attachChild(frame);
        
        logo = new Picture("logo");
        logo.setImage(assetManager, "Interface/Monkey.png", true);
        logo.move(settings.getWidth()/2-47, 2, -1);
        logo.setWidth(95);
        logo.setHeight(75);
        guiNode.attachChild(logo);
        
        guiFont = assetManager.loadFont("/Interface/Fonts/Orbitron.fnt");
        distanceText = new BitmapText(guiFont);
        distanceText.setSize(guiFont.getCharSet().getRenderedSize());
        distanceText.move(
                settings.getWidth()/2+50,  //x
                distanceText.getLineHeight()+20,   //y
                0); //z (Depth Layer)
        guiNode.attachChild(distanceText);
        
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        distance =  Vector3f.ZERO.distance(cam.getLocation());
        distanceText.setText("Distance: " + distance);
        
        if(distance < 10){
            logo.setImage(assetManager, "Interface/chimpanzee-smile.gif", true);
        }//if
        else{
            logo.setImage(assetManager, "Interface/chimpanzee-sad.gif", true);
        }//else
    }//simpleUpdate

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
