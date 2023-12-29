package wk.demo.block.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;

import wk.demo.block.constant.BaserConstant;
import wk.demo.block.group.BtnGroup;
import wk.demo.block.group.ItemGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 16:55
 */
public class LoadingScreen extends BaseScreen {

    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        Image bgImage = new Image(new NinePatch(
                Asset.getAsset().getTexture("white_100x100.png"),
                3,3,3,3));
        addActor(bgImage);
        bgImage.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        addActor(new Table(){{
            BtnGroup bzer= new BtnGroup("Bzer");
            BtnGroup cure = new BtnGroup("Cure");
            add(bzer);
            row();
            add(cure);
            pack();

            bzer.addListener(new OrdinaryButtonListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    setScreen(MainScreen.class);
                }
            });
            cure.addListener(new OrdinaryButtonListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    setScreen(CureScreen.class);
                }
            });
        }});
    }
}
