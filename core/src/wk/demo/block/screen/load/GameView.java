package wk.demo.block.screen.load;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import wk.demo.block.constant.Constant;
import wk.demo.block.utils.ShapeDraw;

public class GameView extends Group {
    private ShapeDraw shapeDraw;
    public GameView(){
        setSize(Constant.width,Constant.height);
        xx();
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                long l = System.currentTimeMillis() - lastTime;
                lastTime = System.currentTimeMillis();
                if (l<1000){
                    Image image = new Image(new Texture("white_cir.png"));
                    addActor(image);
                    image.setPosition(x,y);
                    image.addListener(imgaeListener);
                    array.add(image);
                    array1.clear();
                    controlPoint.clear();
                    for (int i = 0; i < array.size; i++) {
                        controlPoint.add(array.get(i).getPosition());
                    }
                    jisuan(controlPoint);
                }
            }
        });
    }

    private long lastTime = Integer.MIN_VALUE;
    private ClickListener imgaeListener = new ClickListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void touchDragged(InputEvent event, float x, float y, int pointer) {
            super.touchDragged(event, x, y, pointer);
            event.getTarget().setPosition(event.getStageX(),event.getStageY());
            array1.clear();
            controlPoint.clear();
            for (int i = 0; i < array.size; i++) {
                controlPoint.add(array.get(i).getPosition());
            }
            jisuan(controlPoint);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
        }
    };

    private Array<Vector2> controlPoint = new Array<Vector2>();
    private Array<Image> array = new Array<>();
    private Array<Vector2> array1 = new Array<>();
    public void xx() {
//        [{x:69,y:732},{x:366,y:111},{x:300,y:774},{x:681,y:684}]
        controlPoint.add(new Vector2(69, 732)); //起点
        controlPoint.add(new Vector2(366, 111)); //控制点
        controlPoint.add(new Vector2(300, 774)); //控制点
        controlPoint.add(new Vector2(681, 684)); //终点
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
        shapeDraw.setArray(array1);
        shapeDraw.setLine(controlPoint);
    }

    public void jisuan(Array<Vector2> controlPoint){
        int n = controlPoint.size - 1; //
        int i, r;
        float u;
        // u的步长决定了曲线点的精度
        for (u = 0; u <= 1; u += 0.01) {
            Vector2 p[] = new Vector2[n + 1];
            for (i = 0; i <= n; i++) {
                p[i] = new Vector2(controlPoint.get(i));
            }
            for (r = 1; r <= n; r++) {
                for (i = 0; i <= n - r; i++) {
                    p[i].x = (1 - u) * p[i].x + u * p[i + 1].x;
                    p[i].y = (1 - u) * p[i].y + u * p[i + 1].y;
                }
            }
            array1.add(p[0]);
        }
    }
}
