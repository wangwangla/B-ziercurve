package wk.demo.block;

import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.GameInfo;

import wk.demo.block.screen.LoadingScreen;
import wk.demo.block.screen.MainScreen;

@GameInfo(width = 1960,height =1940)
public class Bziercurve extends BaseGame {

    @Override
    protected void loadingView() {
        super.loadingView();
        Constant.viewColor.set(0.6f,0.6f,0.6f,0.6f);
        setScreen(new LoadingScreen(this));
    }
}
