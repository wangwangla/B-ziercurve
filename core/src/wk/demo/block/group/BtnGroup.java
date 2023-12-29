package wk.demo.block.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.listener.OrdinaryButtonListener;

import wk.demo.block.constant.BaserConstant;
import wk.demo.block.constant.BserType;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 16:31
 */
public class BtnGroup extends Group {
    public BtnGroup(String name){
        this(name,400,200);
    }

    public BtnGroup(String name,float width,float height){
        setSize(width,height);
        Image bgImage = new Image(new NinePatch(
                Asset.getAsset().getTexture("white_100x100.png"),
                3,3,3,3));
        addActor(bgImage);
        bgImage.setDebug(true);
        setOrigin(Align.center);
        bgImage.setSize(getWidth(),getHeight());
        Label label = new Label("",new Label.LabelStyle(){{
            font = Asset.getAsset().loadBitFont("frmb-40.fnt");
        }});
        addActor(label);
        label.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
        label.setAlignment(Align.center);
        label.setText(name);
        label.setFontScale(0.7f);
        label.setColor(Color.BLACK);
    }
}
