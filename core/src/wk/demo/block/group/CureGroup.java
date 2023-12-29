package wk.demo.block.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;

import wk.demo.block.screen.utils.CurveTimeline;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 17:04
 */
public class CureGroup extends Group {
    Array<Vector2> vector2s = new Array<>();
    ShapeRenderer renderer;
    public CureGroup(){
        setSize(Constant.GAMEWIDTH/2.0f,Constant.GAMEWIDTH/2.0f);
        Image image = new Image(   Asset.getAsset().getTexture("white_100x100.png"));
        addActor(image);
        image.setSize(getWidth(),getHeight());
        image.setColor(Color.BLACK);

        renderer = new ShapeRenderer();
        CurveTimeline curveTimeline = new CurveTimeline();
        curveTimeline.setCurve(0,0,1.0f,1.0f);
        for (int i = 0; i < 100; i++) {
            float curvePercent = curveTimeline.getCurvePercent(i / 100.0f);
            vector2s.add(new Vector2(i/100.0f * getWidth(),curvePercent* getHeight()));
        }
    }

    Vector2 lastPosition;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        this.applyTransform(batch, this.computeTransform());
        batch.flush();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Line);

        for (Vector2 vector2 : vector2s) {
            if (lastPosition == null){
                lastPosition = vector2;
                continue;
            }
            renderer.line(lastPosition,vector2);
            lastPosition = vector2;
        }
        lastPosition = null;
        renderer.end();
        this.resetTransform(batch);
    }
}
