package wk.demo.block.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.constant.Constant;

import wk.demo.block.action.BeserAction;
import wk.demo.block.constant.BseInterpolation;

public class NShowGroup extends Group {
    public NShowGroup(NBezierGroup bezierGroup){
        Image show = new Image(new NinePatch(
                new Texture("white_100x100.png"),
                20,20,20,20));
        addActor(show);
        show.setSize(Constant.GAMEWIDTH/2.0f - 200,Constant.GAMEWIDTH/2.0f - 200);
        setX(1500/2.0f);
        setSize(show.getWidth(),show.getHeight());
        show.setColor(0.2f,0.2f,0.2f,1);

        Image testBlackOne = new Image(new Texture("white_100x100.png"));
        addActor(testBlackOne);
        testBlackOne.setSize(Constant.GAMEWIDTH/6.0f,Constant.GAMEWIDTH/6.0f);
        testBlackOne.setX(50, Align.left);
        Image testBlackTwo = new Image(new Texture("white_100x100.png"));
        addActor(testBlackTwo);
        testBlackTwo.setSize(Constant.GAMEWIDTH/6.0f,Constant.GAMEWIDTH/6.0f);
        testBlackTwo.setX(getWidth() - 50,Align.right);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                testBlackTwo.setY(0);
                testBlackTwo.addAction(
                        Actions.moveToAligned(
                                testBlackTwo.getX(Align.center),
                                getHeight(),
                                Align.top,
                                5.3f,
                                BseInterpolation.interpolation
                        )
                );
                testBlackOne.setY(0);
                testBlackOne.addAction(
                        Actions.moveToAligned(
                                testBlackOne.getX(Align.center),
                                getHeight(),
                                Align.top,
                                5.3f,
                                new BeserAction(bezierGroup.getPoint())
                        )
                );
            }
        });
    }
}
