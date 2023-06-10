package wk.demo.block.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import wk.demo.block.screen.load.GameView;

public class BezierGroup extends Group {
    private GameView view;
    public BezierGroup(){
        Image beszi = new Image(new NinePatch(
                new Texture("white_100x100.png"),
                20,20,20,20));
        addActor(beszi);
        beszi.setSize(1200/2.0f,1200/2.0f);
        beszi.setColor(0.2f,0.2f,0.2f,1);
        setSize(1200/2.0f,1200/2.0f);
        view = new GameView();
        view.setSize(1200/2.0f,1200/2.0f);
        addActor(view);
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
}
