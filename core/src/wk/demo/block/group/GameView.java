package wk.demo.block.group;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.constant.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import wk.demo.block.bezier.BaseBzer;
import wk.demo.block.bezier.BzerManager;
import wk.demo.block.constant.BseInterpolation;
import wk.demo.block.utils.ShapeDraw;

/**
 * 展示线
 */
public class GameView extends Group {
    private ShapeDraw shapeDraw;
    private long lastTime = Integer.MIN_VALUE;
    private Label label;
    private Image posImage;
    private Array<Vector2> controlPoint = new Array<Vector2>();
    private Array<Image> array = new Array<>();

    private Vector2 vector2;
    private BaseBzer baseBzer;

    public GameView(){
        baseBzer = BzerManager.getInstance();
    }

    public void init(){
        defacultLine();
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                long l = System.currentTimeMillis() - lastTime;
                lastTime = System.currentTimeMillis();
                if (l<1000){
                    posImage = new Image(new Texture("white_cir.png"));
                    addActor(posImage);
                    posImage.setPosition(x,y);
                    posImage.addListener(imgaeListener);
                    Image image1 = array.removeIndex(array.size - 1);
                    array.add(posImage);
                    array.add(image1);
                    controlPoint.clear();
                    for (int i = 0; i < array.size; i++) {
                        controlPoint.add(array.get(i).getPosition());
                    }
                    jisuan(controlPoint);
                }
            }
        });
    }

    private ClickListener imgaeListener = new ClickListener(){
        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            super.touchDragged(event, x, y, pointer);
            vector2 = new Vector2(event.getStageX(), event.getStageY());
            stageToLocalCoordinates(vector2);
            event.getTarget().setPosition(vector2.x,vector2.y);
            controlPoint.clear();
            for (int i = 0; i < array.size; i++) {
                controlPoint.add(array.get(i).getPosition());
            }
            jisuan(controlPoint);
        }
    };

    public void defacultLine() {
        controlPoint.add(new Vector2(0, 0)); //起点
        controlPoint.add(new Vector2(100,200));
        controlPoint.add(new Vector2(800,10));
        controlPoint.add(new Vector2(getWidth(), getHeight())); //终点
        for (Vector2 vector2 : controlPoint) {
            Image image = new Image(new Texture("white_cir.png"));
            addActor(image);
            image.setPosition(vector2.x,vector2.y);
            image.addListener(imgaeListener);
            array.add(image);
        }
       jisuan(controlPoint);
        shapeDraw = new ShapeDraw();
        addActor(shapeDraw);
        shapeDraw.setArray(baseBzer.getFinalV2());
        shapeDraw.setLine(controlPoint);
    }

    public void jisuan(Array<Vector2> controlPoint){
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();
                builder.append("cubic-bezier( ");
                for (int i = 0; i < controlPoint.size; i++) {
                    Vector2 vector2 = controlPoint.get(i);
                    builder.append("("+formatFloat(vector2.x)+","+formatFloat(vector2.y)+")");
                    if (i == controlPoint.size-1) {
                        builder.append(")");
                    }else {
                        builder.append(";");
                    }
                }
                label.setText(builder.toString());
                baseBzer.cal(controlPoint);
                shapeDraw.setArray(baseBzer.getFinalV2());
                shapeDraw.setLine(controlPoint);
            }
        });
    }

    public String formatFloat(float v){
        System.out.println(v);
        return String.format("%.2f",v);
    }

    public Array<Vector2> getData() {
        return baseBzer.getFinalV2();
    }


    public void drawLibary() {
        Array<Vector2> array2 = new Array<>();
        for (int i = 0; i < 100; i++) {
            float v = i/100.0f;
            float apply = BseInterpolation.interpolation.apply(v);
            array2.add(new Vector2(v*(Constant.GAMEWIDTH/2 - 250),apply*(Constant.GAMEWIDTH/2 - 250)));
        }
        shapeDraw.setArrayLib(array2);
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void update() {
        baseBzer = BzerManager.getInstance();
    }
}
