package wk.demo.block.bezier;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 16:12
 */
public class PointCalBzer extends BaseBzer{
    @Override
    public void cal(Array<Vector2> controlPoint) {
        finalV2.clear();
        int n = controlPoint.size - 1; //
        int i, r;
        float u;
        // u的步长决定了曲线点的精度
        for (u = 0; u <= 1; u += 0.01) {
            Vector2 p[] = new Vector2[n + 1];
            for (i = 0; i <= n; i++) {
                p[i] = new Vector2(controlPoint.get(i));
            }
            for (r = 1; r <= n; r++) {
                for (i = 0; i <= n - r; i++) {
                    p[i].x = (1 - u) * p[i].x + u * p[i + 1].x;
                    p[i].y = (1 - u) * p[i].y + u * p[i + 1].y;
                }
            }
            finalV2.add(p[0]);
        }
    }
}
