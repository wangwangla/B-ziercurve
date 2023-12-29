package wk.demo.block.bezier;

import com.badlogic.gdx.utils.ArrayMap;

import wk.demo.block.constant.BaserConstant;
import wk.demo.block.constant.BserType;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/29 16:17
 */
public class BzerManager {
    private static ArrayMap<BserType,BaseBzer> arrayMap = new ArrayMap<>();
    public static BaseBzer getInstance(){
        if (arrayMap.containsKey(BaserConstant.bserType)) {
            return arrayMap.get(BaserConstant.bserType);
        }
        BaseBzer baseBzer;
        switch (BaserConstant.bserType){
            case RBBEBE:
                 baseBzer = new RBBeBezier();
                break;
            case POINTCAL:
                baseBzer = new PointCalBzer();
                break;
            case CATMULLROMSOLINE:
                baseBzer = new CatmullRomSplineBzer();
                break;
            default:
                baseBzer = new RBBeBezier();
                arrayMap.put(BaserConstant.bserType,baseBzer);
                return baseBzer;
        }
        arrayMap.put(BaserConstant.bserType,baseBzer);
        return baseBzer;
    }
}
