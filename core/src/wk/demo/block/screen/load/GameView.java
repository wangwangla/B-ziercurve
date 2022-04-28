package wk.demo.block.screen.load;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import wk.demo.block.BseInterpolation;
import wk.demo.block.bezier.RBBezierUtil;
import wk.demo.block.constant.Constant;
import wk.demo.block.screen.utils.CatmullRomSpline;
import wk.demo.block.screen.utils.Vector3;
import wk.demo.block.utils.ShapeDraw;

public class GameView extends Group {
    private ShapeDraw shapeDraw;
    public GameView(){

        catmullRomSpline = new CatmullRomSpline();
        setDebug(true);
        setSize(Constant.width,720);
        defacultLine();
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (y>720)return;
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

        Image image = new Image(new Texture("white_squ.png"));
        image.setPosition(getWidth() - 260,830);
        addActor(image);


        Image image1 = new Image(new Texture("white_squ.png"));
        addActor(image1);
        image1.setY(830);


        Image image3 = new Image(new Texture("white_squ.png"));
        addActor(image3);
        image3.setY(830);
        image3.setX(0,Align.center);
        image3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
               image1.setScale(1,1);
            }
        });

        image.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                image1.addAction(Actions.scaleTo(3,3,3, new Bse(array1)));
                BseInterpolation bseInterpolation = new BseInterpolation();
                bseInterpolation.setCurve(0.25F, 0, 0.75F, 1F);
                image1.addAction(Actions.scaleTo(3,3,3, bseInterpolation));
                save();
            }
        });

    }
    Image image;
    private boolean start = false;
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
    Vector2 sss = new Vector2(360,0);
    public void defacultLine() {
        controlPoint.add(new Vector2(0, 0)); //起点
        controlPoint.add(sss);
        controlPoint.add(new Vector2(710, 0)); //终点
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

    CatmullRomSpline catmullRomSpline;
    public void jisuan(Array<Vector2> controlPoint){

        catmullRomSpline.getControlPoints().clear();
        for (Vector2 vector2 : controlPoint) {
            catmullRomSpline.add(new Vector3(vector2.x,vector2.y,0));
        }
        List<Vector3> path = catmullRomSpline.getPath(10);
        for (int i = 0; i < path.size(); i++) {
            array1.add(new Vector2(path.get(i).x,path.get(i).y));
        }


//        int n = controlPoint.size - 1; //
//        int i, r;
//        float u;
//        // u的步长决定了曲线点的精度
//        for (u = 0; u <= 1; u += 0.01) {
//            Vector2 p[] = new Vector2[n + 1];
//            for (i = 0; i <= n; i++) {
//                p[i] = new Vector2(controlPoint.get(i));
//            }
//            for (r = 1; r <= n; r++) {
//                for (i = 0; i <= n - r; i++) {
//                    p[i].x = (1 - u) * p[i].x + u * p[i + 1].x;
//                    p[i].y = (1 - u) * p[i].y + u * p[i + 1].y;
//                }
////            }
////            array1.add(p[0]);
////        }
//
//        int steps = RBBezierUtil.init(controlPoint.get(0), controlPoint.get(1), controlPoint.get(2), 0.1F);
//        if (steps == 0) {
////            LogU.log("steps:" + steps + " " + p0 + p1 + p2);
//        }
////        Array<Vector2> array = new Array<>();
//        for (int m = 1; m <= steps; ++m) {
//            float[] data = RBBezierUtil.getAnchorPointRB(m,false);
//            if (data != null) {
//                if (data.length>=2){
//                    array1.add(new Vector2(data[0],data[1]));
//                }
//            }
//        }



//        System.out.println("================");
//
//        Vector2 rp[] = new Vector2[controlPoint.size];
//        for (int i = 0; i < controlPoint.size; i++) {
//            rp[i] = controlPoint.get(i);
//        }
//
//
//        Vector2 p0 = new Vector2(0, 0);
//        Vector2 p1 = new Vector2(0, 0);
//        Vector2 p2 = new Vector2(0, 0);
//
//        for (int j = 0; j < rp.length - 2; ++j) {
//            if (j == 0) {
//                p0.x = rp[0].x;
//                p0.y = rp[0].y;
//            } else {
//                p0.x = (rp[j].x + rp[j + 1].x) / 2;
//                p0.y = (rp[j].y + rp[j + 1].y) / 2;
//            }
//            p1.x = rp[j + 1].x;
//            p1.y = rp[j + 1].y;
//            if (j <= rp.length - 4) {
//                p2.x = (rp[j + 1].x + rp[j + 2].x) / 2;
//                p2.y = (rp[j + 1].y + rp[j + 2].y) / 2;
//            } else {
//                p2.x = rp[j + 2].x;
//                p2.y = rp[j + 2].y;
//            }
//            if ((p2.x - p0.x) * (p1.y - p0.y) - (p2.y - p0.y) * (p1.x - p0.x) == 0) {
//                p1.x++;
//                p1.y++;
//            }
//            int steps = RBBezierUtil.init(p0, p1, p2, 1);
//            if (steps == 0) {
////                LogU.log("steps:" + steps + " " + p0 + p1 + p2);
//            }
//            for (int m = 1; m <= steps; ++m) {
//                float[] data = RBBezierUtil.getAnchorPointRB(m,false);
//                if (data != null) {
//                    array1.add(new Vector2(data[0],data[1]));
//                }
//            }
//        }
    }



    public void save(){
        try {
            FileWriter stream = new FileWriter(new File("./text.txt"));
            for (Vector2 vector2 : controlPoint) {
                stream.write(vector2.x+", "+vector2.y+" ");
            }
            stream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private float timess = 0;
    private int index= 0;
    private int deIndex = 1;
    @Override
    public void act(float delta) {
        super.act(delta);
        timess += delta;
        if (start) {
            if (timess > 0.1F){
                sss.y = sss.y +5;
                array1.clear();
                jisuan(controlPoint);
                if (index>=array1.size-1)deIndex =-1;
                if (index<=0)deIndex = 1;
                Vector2 vector2 = array1.get(index);
                image.setPosition(vector2.x,vector2.y,Align.center);
                timess = 0;
                index+=deIndex;
            }
        }
    }
}