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
    private Array<Vector2> vector2s = new Array<>();
    private ShapeRenderer renderer;
    private CurveTimeline curveTimeline;
    private Vector2 lastPosition;

    public CureGroup(float c1,float c2,float c3,float c4){
        setSize(Constant.GAMEWIDTH/2.0f,Constant.GAMEWIDTH/2.0f);
        Image image = new Image(   Asset.getAsset().getTexture("white_100x100.png"));
        addActor(image);
        image.setSize(getWidth(),getHeight());
        image.setColor(Color.BLACK);

        renderer = new ShapeRenderer();
        curveTimeline = new CurveTimeline();
        update(c1,c2,c3,c4);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        this.applyTransform(batch, this.computeTransform());
        batch.end();
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
        batch.begin();
        this.resetTransform(batch);
    }

    public void update(float num1, float num2, float num3, float num4) {
        curveTimeline.setCurve(num1,num2,num3,num4);
        vector2s.clear();
        for (int i = 0; i < 100; i++) {
            float curvePercent = curveTimeline.getCurvePercent(i / 100.0f);
            vector2s.add(new Vector2(i/100.0f * getWidth(),curvePercent* getHeight()));
        }


    }
}
