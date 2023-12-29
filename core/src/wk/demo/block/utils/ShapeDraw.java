package wk.demo.block.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ShapeDraw extends Actor {
    private ShapeRenderer sr;
    private Array<Vector2> array;
    private Array<Vector2> arrayLib;
    private Vector2 lastPosition = null;
    public Array<Vector2> line;

    public ShapeDraw() {
        this.sr = new ShapeRenderer();
    }

    public void setArray(Array<Vector2> array) {
        this.array = array;
    }

    public void setLine(Array<Vector2> line) {
        this.line = line;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawArray(batch,array,Color.WHITE);
        if (arrayLib!=null){
            drawArray(batch,arrayLib,Color.RED);
        }
        drawArray(batch,line,Color.BLUE);
    }

    public void setArrayLib(Array<Vector2> arrayLib) {
        this.arrayLib = arrayLib;
    }

    private void drawArray(Batch batch, Array<Vector2> array,Color color) {
        sr.setColor(color);
        batch.end();
        sr.setProjectionMatrix(batch.getProjectionMatrix());
        sr.setTransformMatrix(batch.getTransformMatrix());
        sr.begin(ShapeRenderer.ShapeType.Line);
        lastPosition = null;
        for (Vector2 vector2 : array) {
            if (lastPosition!=null){
                sr.rectLine(vector2,lastPosition,21);
            }
            lastPosition = vector2;
        }
        lastPosition = null;
        sr.end();
        batch.begin();
    }
}