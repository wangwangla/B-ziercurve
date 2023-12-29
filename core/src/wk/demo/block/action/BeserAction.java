package wk.demo.block.action;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.constant.Constant;

import wk.demo.block.group.BezierGroup;

public class BeserAction extends Interpolation {
    public Array<Vector2> array;

    public BeserAction(Array<Vector2> array){
        this.array = array;
    }

    @Override
    public float apply(float a) {
        if (array.size<=0)return 0;
        int size = array.size;
        int v = (int) (size * a);
        if (v == 1) {
            v = size - 1;
        }
        Vector2 vector2 = array.get(v);
        return vector2.y/(Constant.GAMEWIDTH/2 - 250);
    }
}
