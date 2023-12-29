package wk.demo.block.bezier;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import wk.demo.block.utils.RBBezierUtil;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 16:10
 */
public class RBBeBezier extends BaseBzer{
    @Override
    public void cal(Array<Vector2> controlPoint) {
        Vector2 rp[] = new Vector2[controlPoint.size];
        for (int i = 0; i < controlPoint.size; i++) {
            rp[i] = controlPoint.get(i);
        }
        Vector2 p0 = new Vector2(0, 0);
        Vector2 p1 = new Vector2(0, 0);
        Vector2 p2 = new Vector2(0, 0);
        Array<Vector2> array3 = new Array<>();
        for (int j = 0; j < rp.length - 2; ++j) {
            if (j == 0) {
                p0.x = rp[0].x;
                p0.y = rp[0].y;
            } else {
                p0.x = (rp[j].x + rp[j + 1].x) / 2;
                p0.y = (rp[j].y + rp[j + 1].y) / 2;
            }
            p1.x = rp[j + 1].x;
            p1.y = rp[j + 1].y;
            if (j <= rp.length - 4) {
                p2.x = (rp[j + 1].x + rp[j + 2].x) / 2;
                p2.y = (rp[j + 1].y + rp[j + 2].y) / 2;
            } else {
                p2.x = rp[j + 2].x;
                p2.y = rp[j + 2].y;
            }
            if ((p2.x - p0.x) * (p1.y - p0.y) - (p2.y - p0.y) * (p1.x - p0.x) == 0) {
                p1.x++;
                p1.y++;
            }
            int steps = RBBezierUtil.init(p0, p1, p2, 1);
            for (int m = 1; m <= steps; ++m) {
                float[] data = RBBezierUtil.getAnchorPointRB(m,false);
                if (data != null) {
                    array3.add(new Vector2(data[0],data[1]));
                }
            }
        }
        finalV2.clear();
        finalV2.addAll(array3);
    }
}
