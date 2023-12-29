package wk.demo.block.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.constant.Constant;

public class BezierGroup extends Group {
    private GameView view;
    public BezierGroup(){
        setDebug(true);
        Image besziBg = new Image(new NinePatch(
                new Texture("white_100x100.png"),
                20,20,20,20));
        addActor(besziBg);
        besziBg.setSize(Constant.GAMEWIDTH/2 - 250,Constant.GAMEWIDTH/2 - 250);
        besziBg.setColor(0.2f,0.2f,0.2f,1);
        setSize(besziBg.getWidth(),besziBg.getHeight());
        view = new GameView();
        view.setSize(getWidth(),getHeight());
        addActor(view);
        view.init();
        setPosition(50,Constant.GAMEHIGHT/2.0f,Align.left);
    }

    public Array<Vector2> getPoint(){
        return view.getData();
    }

    public void drawLibary() {
        view.drawLibary();
    }

    public void setLabel(Label label) {
        view.setLabel(label);
    }

    public void update() {
        view.update();
    }
}
