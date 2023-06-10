package wk.demo.block.screen.load;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import wk.demo.block.Bziercurve;
import wk.demo.block.constant.Constant;
import wk.demo.block.group.BezierGroup;
import wk.demo.block.group.LibraryGroup;
import wk.demo.block.group.ShowGroup;
import wk.demo.block.screen.base.BaseScreen;

public class LoadingScreen extends BaseScreen {
    public LoadingScreen(Bziercurve game) {
        super(game);
    }

    @Override
    public void showView() {
        super.showView();
        Label label = new Label("",new Label.LabelStyle(){
            {
                font = new BitmapFont(
                        Gdx.files.internal("Bahnschrift-Regular_40_1.fnt"),
                        Gdx.files.internal("Bahnschrift-Regular_40_1.png"),false);
            }
        });
        addActor(label);
        label.setText("cubic-bezier(.02  .97    .97   .1)");
        label.pack();
        label.setPosition(Constant.width/2,Constant.height - 10,Align.top);

        BezierGroup bezierGroup = new BezierGroup();
        addActor(bezierGroup);

        LibraryGroup libraryGroup = new LibraryGroup();
        addActor(libraryGroup);

        ShowGroup showGroup = new ShowGroup(libraryGroup,bezierGroup);
        addActor(showGroup);


//        GameView view = new GameView();
//        addActor(view);
    }
}
