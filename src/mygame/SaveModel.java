package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.io.File;
import java.io.IOException;
import org.bushe.swing.event.Logger;

/**
 * test
 * @author normenhansen
 */
public class SaveModel extends SimpleApplication {

    public static void main(String[] args) {
        SaveModel app = new SaveModel();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        String userHome = System.getProperty("user.home");
        assetManager.registerLocator(userHome, FileLocator.class);
        try{
            System.out.println("1");
            Node loadedNode = (Node) assetManager.loadModel("/SavedGames/savedgame.j3o");
            rootNode.attachChild(loadedNode);
        }catch(com.jme3.asset.AssetNotFoundException e){
            System.out.println("2");
        }//catch
        
        Spatial mymodel = assetManager.loadModel("Models/MyModel/mymodel.j3o");
        mymodel.move(
                FastMath.nextRandomFloat()*10-5, 
                FastMath.nextRandomFloat()*10-5, 
                FastMath.nextRandomFloat()*10-5);
        rootNode.attachChild(mymodel);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        
        flyCam.setMoveSpeed(10);
    }
    
    @Override
    public void stop(){
        System.out.println("3");
        String userHome = System.getProperty("user.home");
        File file = new File(
                userHome + "/SavedGames/" + "savedgame.j3o");
        BinaryExporter exporter = BinaryExporter.getInstance();
        try{
            System.out.println("4");
            exporter.save(rootNode, file);
        }catch(IOException ex){
            System.out.println("5");
            Logger.getLogger(SaveModel.class.getName())
                    .log(Logger.Level.FATAL, "Error: Failed to save game!", ex);
        }//catch
        super.stop(); //continue quitting game
    }//stop

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
