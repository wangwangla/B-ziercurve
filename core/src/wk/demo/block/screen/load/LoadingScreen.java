package wk.demo.block.screen.load;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.screen.BaseScreen;

import wk.demo.block.Bziercurve;
import wk.demo.block.group.BezierGroup;
import wk.demo.block.group.LibraryGroup;
import wk.demo.block.group.ShowGroup;

public class LoadingScreen extends BaseScreen {
    public LoadingScreen(Bziercurve game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Label pointData = new Label("",new Label.LabelStyle(){{
            font = Asset.getAsset().loadBitFont("frmb-40.fnt");
        }});
        pointData.setAlignment(Align.center);
        addActor(pointData);
        pointData.setText("cubic-bezier(.02  .97    .97   .1)");
        pointData.pack();
        pointData.setPosition(com.kw.gdx.constant.Constant.GAMEWIDTH /2, Constant.GAMEHIGHT- 10,Align.top);

        BezierGroup bezierGroup = new BezierGroup();
        addActor(bezierGroup);
        bezierGroup.setLabel(pointData);

        LibraryGroup libraryGroup = new LibraryGroup(bezierGroup);
        addActor(libraryGroup);
        libraryGroup.setPosition(Constant.GAMEWIDTH/2.0f,Constant.GAMEHIGHT/2.0f,Align.center);

        ShowGroup showGroup = new ShowGroup(bezierGroup);
        showGroup.setPosition(Constant.GAMEWIDTH,Constant.GAMEHIGHT/2.0f,Align.right);
        addActor(showGroup);


    }
}
