package wk.demo.block.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.view.dialog.base.BaseDialog;

import wk.demo.block.Bziercurve;
import wk.demo.block.constant.BserType;
import wk.demo.block.group.BezierGroup;
import wk.demo.block.group.ItemGroup;
import wk.demo.block.group.LibraryGroup;
import wk.demo.block.group.ShowGroup;

public class MainScreen extends BaseScreen {
    public MainScreen(Bziercurve game) {
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

        BezierGroup bezierGroup = new BezierGroup();
        addActor(bezierGroup);
        bezierGroup.setLabel(pointData);

        LibraryGroup libraryGroup = new LibraryGroup(bezierGroup);
        addActor(libraryGroup);
        libraryGroup.setPosition(Constant.GAMEWIDTH/2.0f,Constant.GAMEHIGHT/2.0f,Align.center);

        ShowGroup showGroup = new ShowGroup(bezierGroup);
        showGroup.setPosition(Constant.GAMEWIDTH,Constant.GAMEHIGHT/2.0f,Align.right);
        addActor(showGroup);


        ScrollPane pane = new ScrollPane(new Table(){{
            BserType [] bserTypes = {
                    BserType.CATMULLROMSOLINE,
                    BserType.POINTCAL,
                    BserType.RBBEBE
            };
            for (int i = 0; i < bserTypes.length; i++) {
                add(new ItemGroup(bserTypes[i],bezierGroup)).pad(40);
                pack();
            }
        }});
        addActor(pane);
        pane.setSize(Constant.GAMEWIDTH/2.0f,300);
        pane.setPosition(Constant.GAMEWIDTH/2,150,Align.center);
    }

    @Override
    protected BaseDialog back() {
        setScreen(LoadingScreen.class);
        return super.back();
    }
}
