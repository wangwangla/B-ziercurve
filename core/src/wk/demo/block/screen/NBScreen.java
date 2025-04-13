package wk.demo.block.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.view.dialog.base.BaseDialog;

import wk.demo.block.constant.BserType;
import wk.demo.block.group.BezierGroup;
import wk.demo.block.group.ItemGroup;
import wk.demo.block.group.LibraryGroup;
import wk.demo.block.group.NBezierGroup;
import wk.demo.block.group.NShowGroup;
import wk.demo.block.group.ShowGroup;

public class NBScreen extends BaseScreen {
    public NBScreen(BaseGame game) {
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
        pointData.setColor(Color.CYAN);
        pointData.pack();
        pointData.setPosition(com.kw.gdx.constant.Constant.GAMEWIDTH /2, Constant.GAMEHIGHT- 10,Align.top);

        NBezierGroup bezierGroup = new NBezierGroup();
        addActor(bezierGroup);
        bezierGroup.setLabel(pointData);


        NShowGroup showGroup = new NShowGroup(bezierGroup);
        showGroup.setPosition(Constant.GAMEWIDTH,Constant.GAMEHIGHT/2.0f,Align.right);
        addActor(showGroup);
        showGroup.setDebug(true);


    }

    @Override
    protected BaseDialog back() {
        setScreen(LoadingScreen.class);
        return super.back();
    }
}
