package mygame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.SkeletonDebugger;

/**
 * test
 * @author normenhansen
 */
public class AnimateModel extends SimpleApplication implements AnimEventListener{

    private Node player = new Node();
    private AnimControl control;
    private static final String ANI_WALK = "Walk";
    private static final String ANI_IDLE = "Idle";
    private AnimChannel channel;
    private static final String MAPPING_WALK = "walk forward";
    
    public static void main(String[] args) {
        AnimateModel app = new AnimateModel();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10);
        
        inputManager.addMapping(MAPPING_WALK, 
                new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener, MAPPING_WALK);
        inputManager.addListener(analogListener, MAPPING_WALK);
        
        Spatial mymodel = assetManager.loadModel("Models/MyModel/Animated/mymodel.j3o");
        player.attachChild(mymodel);
        player.rotate(FastMath.DEG_TO_RAD*-90, 0, 0);
        player.scale(0.1f);
        control = mymodel.getControl(AnimControl.class);
        control.addListener(this);
        channel = control.createChannel();
        channel.setAnim(ANI_IDLE);
        
        SkeletonDebugger skeletonDebugger = new SkeletonDebugger("skeleton", control.getSkeleton());
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        mat.getAdditionalRenderState().setDepthTest(false);
        skeletonDebugger.setMaterial(mat);
        player.attachChild(skeletonDebugger);        
        
        rootNode.attachChild(player);
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.5f, -0.5f, -0.5f ));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        
        for (String anim : control.getAnimationNames())
        { System.out.println(anim); }       
    }//init

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }//simpleRender
    
    private ActionListener actionListener = new ActionListener() {

        public void onAction(String name, boolean isPressed, float tpf) {
            if(name.equals(MAPPING_WALK) && isPressed){
                if(!channel.getAnimationName().equals(ANI_WALK)){
                    channel.setAnim(ANI_WALK);
                }//if
            }//if
            if (name.equals(MAPPING_WALK) && !isPressed) {
                channel.setAnim(ANI_IDLE);
            }//if
        }//onAction
    };//actionListener
    
    private AnalogListener analogListener = new AnalogListener() {

        public void onAnalog(String name, float value, float tpf) {
            if (name.equals(MAPPING_WALK)){
                player.move(0, 0, tpf);
            }//if
        }//onAnalog
    };//AnalogListener
    
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName){
        if(animName.equals(ANI_WALK)){
            System.out.println(control.getSpatial().getName() + " started walking.");
        }//if
        else if(animName.equals(ANI_IDLE)){
            System.out.println(control.getSpatial().getName() + " started idling.");
        }//else if
    }//onAnimChange
    
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName){
        if (animName.equals(ANI_WALK)) {
            System.out.println(control.getSpatial().getName()
            + " completed one walk loop.");
        }//if 
        else if (animName.equals(ANI_IDLE)) {
            System.out.println(control.getSpatial().getName()
            + " completed one idle loop.");
        }//else if
    }//onAnimCycleDone
}//class
