package wk.demo.block.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.view.dialog.base.BaseDialog;

import wk.demo.block.group.BtnGroup;
import wk.demo.block.group.CureGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 17:02
 */
public class CureScreen extends BaseScreen {
    private Group numGroup;
    private CureGroup group;

    public CureScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        group = new CureGroup(0,0,1.0f,1.0f);
        addActor(group);
        group.setPosition(Constant.GAMEWIDTH/2.0f,Constant.GAMEHIGHT/2.0f, Align.center);
        numGroup = new Table();
        numGroup.addActor(createTextField("num1",0));
        numGroup.addActor(createTextField("num2",1));
        numGroup.addActor(createTextField("num3",2));
        numGroup.addActor(createTextField("num4",3));
        addActor(numGroup);
        numGroup.setSize(4*500+50 * 3,100);
        numGroup.setPosition(Constant.GAMEWIDTH/2.0f,Constant.GAMEHIGHT - 50,Align.top);
        BtnGroup ok = new BtnGroup("OK",100,100);
        addActor(ok);
        ok.setPosition(Constant.GAMEWIDTH/2.0f,50,Align.bottom);
        ok.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                float num1 = readTextFieldValue(numGroup.findActor("num1"));
                System.out.println(num1);
                float num2 = readTextFieldValue(numGroup.findActor("num2"));
                System.out.println(num2);
                float num3 = readTextFieldValue(numGroup.findActor("num3"));
                System.out.println(num3);
                float num4 = readTextFieldValue(numGroup.findActor("num4"));
                System.out.println(num4);

                group.update(num1,num2,num3,num4);
            }
        });
    }

    public float readTextFieldValue(TextField textField){
        try {
            String text = textField.getText();
            float v = Float.parseFloat(text);
            return v;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Actor createTextField(String name, int i) {
        TextField field = new TextField("",new TextField.TextFieldStyle(){{
            font = Asset.getAsset().loadBitFont("frmb-40.fnt");
            background = new NinePatchDrawable(
                    new NinePatch(
                            Asset.getAsset().getSprite("textfield/textfieldbg.png"),
                            50,50,40,40));
            cursor = new TextureRegionDrawable(Asset.getAsset().getSprite("textfield/textcursor.png"));
            fontColor = Color.BLACK;
        }
        });
        field.setName(name);
        field.setSize(500,100);
        field.setMessageText("Enter your username");
        field.setX(i*550);
        if (i == 0 || i == 1){
            field.setText(0+"");
        }else {
            field.setText(1+"");
        }
        return field;
    }

    @Override
    protected BaseDialog back() {
        setScreen(LoadingScreen.class);
        return super.back();
    }
}
