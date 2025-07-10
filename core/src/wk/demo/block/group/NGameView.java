package wk.demo.block.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import wk.demo.block.bezier.BaseBzer;
import wk.demo.block.bezier.BzerManager;
import wk.demo.block.utils.BUL1;
import wk.demo.block.utils.ShapeDraw;

/**
 * 展示线
 */
public class NGameView extends Group {
    private ShapeDraw shapeDraw;
    private long lastTime = Integer.MIN_VALUE;
    private Label label;
    private Image posImage;
    private Array<Vector2> controlPoint = new Array<Vector2>();
    private Array<Image> arrayThis = new Array<>();
    private Vector2 vector2;
    private BaseBzer baseBzer;
    private BUL1 bul1;
    public NGameView(){
        baseBzer = BzerManager.getInstance();
        bul1 = new BUL1();
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
                    Image image1 = arrayThis.removeIndex(arrayThis.size - 1);
                    arrayThis.add(posImage);
                    arrayThis.add(image1);
                    controlPoint.clear();
                    for (int i = 0; i < arrayThis.size; i++) {
                        controlPoint.add(new Vector2(arrayThis.get(i).getX(Align.center),arrayThis.get(i).getY(Align.center)));
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
            for (int i = 0; i < arrayThis.size; i++) {
                controlPoint.add(new Vector2(arrayThis.get(i).getX(Align.center),arrayThis.get(i).getY(Align.center)));

            }
            System.out.println(controlPoint);
            jisuan(controlPoint);

            shapeDraw.setArray(bul1.getAllBezierPos());
            shapeDraw.setLine(controlPoint);

        }
    };

    public void defacultLine() {
        controlPoint.add(new Vector2(0, 0)); //起点


        controlPoint.add(new Vector2(getWidth(), getHeight())); //终点
        for (Vector2 vector2 : controlPoint) {
            Image image = new Image(new Texture("white_cir.png"));
            addActor(image);
            image.setPosition(vector2.x,vector2.y);
            image.addListener(imgaeListener);
            arrayThis.add(image);
        }
        jisuan(controlPoint);
        shapeDraw = new ShapeDraw();
        addActor(shapeDraw);
        shapeDraw.setArray(bul1.getAllBezierPos());
        shapeDraw.setLine(controlPoint);
    }

    public void jisuan(Array<Vector2> controlPoint){
        bul1.setController(controlPoint);
    }


    public Array<Vector2> getData() {
        return baseBzer.getFinalV2();
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void update() {
        baseBzer = BzerManager.getInstance();
    }
}
