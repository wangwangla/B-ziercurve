package wk.demo.block.screen;

import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.view.dialog.base.BaseDialog;

import wk.demo.block.group.CureGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 17:02
 */
public class CureScreen extends BaseScreen {
    public CureScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        CureGroup group = new CureGroup();
        addActor(group);
        group.setPosition(Constant.WIDTH/2.0f,Constant.GAMEHIGHT/2.0f, Align.center);
    }

    @Override
    protected BaseDialog back() {
        setScreen(LoadingScreen.class);
        return super.back();
    }
}
