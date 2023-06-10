package wk.demo.block.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ArrayMap;

import wk.demo.block.constant.Constant;

public class LibraryGroup extends Group {
    private ArrayMap<String,Interpolation> interpolationArrayMap;
    private BezierGroup bezierGroup;
    public LibraryGroup(BezierGroup bezierGroup){
        this.bezierGroup = bezierGroup;
        initData();
        Constant.interpolation = Interpolation.linear;
        bezierGroup.drawLibary();
        Image library = new Image(new NinePatch(
                new Texture("white_100x100.png"),
                20,20,20,20));
        addActor(library);
        library.setSize(300/2.0f,1200/2.0f);
        setX(1220/2.0f);
        library.setColor(Color.BLACK);
        setSize(library.getWidth(),library.getHeight());

        Table table = new Table(){{
            for (int i = 0; i < interpolationArrayMap.size; i++) {
                String key = interpolationArrayMap.getKeyAt(i);
                Label label = new Label(key,new Label.LabelStyle(){{
                    font = new BitmapFont();
                }});
                label.pack();
                add(label);
                row();
                label.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        Constant.interpolation = interpolationArrayMap.get(key);
                        bezierGroup.drawLibary();
                    }
                });
            }
            pack();
        }};
        ScrollPane pane = new ScrollPane(table,new ScrollPane.ScrollPaneStyle());
        addActor(pane);
        pane.setSize(getWidth(),getHeight()-200);
    }

    private void initData(){
        interpolationArrayMap = new ArrayMap<>();
        interpolationArrayMap.put("linear",Interpolation.linear);
        interpolationArrayMap.put("smooth",Interpolation.smooth);
        interpolationArrayMap.put("smooth2",Interpolation.smooth2);
        interpolationArrayMap.put("smoother",Interpolation.smoother);
        interpolationArrayMap.put("fade",Interpolation.fade);
        interpolationArrayMap.put("pow2",Interpolation.pow2);
        interpolationArrayMap.put("pow2In",Interpolation.linear);
        interpolationArrayMap.put("pow2Out",Interpolation.linear);
        interpolationArrayMap.put("slowFast",Interpolation.linear);
        interpolationArrayMap.put("fastSlow",Interpolation.linear);
        interpolationArrayMap.put("pow2InInverse",Interpolation.linear);
        interpolationArrayMap.put("pow2OutInverse",Interpolation.linear);
        interpolationArrayMap.put("pow3",Interpolation.linear);
        interpolationArrayMap.put("pow3In",Interpolation.linear);
        interpolationArrayMap.put("pow3Out",Interpolation.linear);
        interpolationArrayMap.put("pow3InInverse",Interpolation.linear);
        interpolationArrayMap.put("pow3OutInverse",Interpolation.linear);
        interpolationArrayMap.put("pow4",Interpolation.linear);
        interpolationArrayMap.put("pow4In",Interpolation.linear);
        interpolationArrayMap.put("pow4Out",Interpolation.linear);
        interpolationArrayMap.put("pow5",Interpolation.linear);
        interpolationArrayMap.put("pow5In",Interpolation.linear);
        interpolationArrayMap.put("pow5Out",Interpolation.linear);
        interpolationArrayMap.put("sin",Interpolation.linear);
        interpolationArrayMap.put("sinIn",Interpolation.linear);
        interpolationArrayMap.put("sinOut",Interpolation.linear);


    }
}
