package wk.demo.block.bezier;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.List;

import wk.demo.block.screen.utils.CatmullRomSpline;
import wk.demo.block.utils.Vector3;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 16:13
 */
public class CatmullRomSplineBzer extends BaseBzer{
    private CatmullRomSpline catmullRomSpline;
    public CatmullRomSplineBzer(){
        catmullRomSpline = new CatmullRomSpline();
    }

    @Override
    public void cal(Array<Vector2> controlPoint) {
        finalV2.clear();
        catmullRomSpline.getControlPoints().clear();
        for (Vector2 vector2 : controlPoint) {
            catmullRomSpline.add(new Vector3(vector2.x,vector2.y,0));
        }
        List<Vector3> path = catmullRomSpline.getPath(10);
        for (int i = 0; i < path.size(); i++) {
            finalV2.add(new Vector2(path.get(i).x,path.get(i).y));
        }
    }
}
