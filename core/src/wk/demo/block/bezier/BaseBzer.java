package wk.demo.block.bezier;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 16:07
 */
public abstract class BaseBzer {
    protected Array<Vector2> finalV2 = new Array<>();
    public abstract void cal(Array< Vector2 > controlPoint);
    public Array<Vector2> getFinalV2() {
        return finalV2;
    }
}
