package wk.demo.block.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import wk.demo.block.action.BeserAction;
import wk.demo.block.constant.Constant;

public class ShowGroup extends Group {
    private LibraryGroup libraryGroup;
    private BezierGroup bezierGroup;
    public ShowGroup(LibraryGroup libraryGroup, BezierGroup bezierGroup){
        Image show = new Image(new NinePatch(
                new Texture("white_100x100.png"),
                20,20,20,20));
        addActor(show);
        show.setSize(300,1200/2.0f);
        setX(1500/2.0f);
        setSize(show.getWidth(),show.getHeight());
        show.setColor(0.2f,0.2f,0.2f,1);

        Image testBlackOne = new Image(new Texture("white_100x100.png"));
        addActor(testBlackOne);
        testBlackOne.setX(75, Align.center);
        Image testBlackTwo = new Image(new Texture("white_100x100.png"));
        addActor(testBlackTwo);
        testBlackTwo.setX(300-75,Align.center);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                testBlackTwo.setY(0);
                testBlackTwo.addAction(
                        Actions.moveToAligned(
                                testBlackTwo.getX(Align.center),
                                1200/2.0f,
                                Align.top,
                                5.3f,
                                Constant.interpolation
                        )
                );
                testBlackOne.setY(0);
                testBlackOne.addAction(
                        Actions.moveToAligned(
                                testBlackOne.getX(Align.center),
                                1200/2.0f,
                                Align.top,
                                5.3f,
                                new BeserAction(bezierGroup.getPoint())
                        )
                );
            }
        });
    }
}
