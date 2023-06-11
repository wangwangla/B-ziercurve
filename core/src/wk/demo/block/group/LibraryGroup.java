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
        interpolationArrayMap.put("pow2In",Interpolation.pow2In);
        interpolationArrayMap.put("pow2Out",Interpolation.pow2Out);
        interpolationArrayMap.put("slowFast",Interpolation.slowFast);
        interpolationArrayMap.put("fastSlow",Interpolation.fastSlow);
        interpolationArrayMap.put("pow2InInverse",Interpolation.pow2InInverse);
        interpolationArrayMap.put("pow2OutInverse",Interpolation.pow2OutInverse);
        interpolationArrayMap.put("pow3",Interpolation.pow3);
        interpolationArrayMap.put("pow3In",Interpolation.pow3In);
        interpolationArrayMap.put("pow3Out",Interpolation.pow3Out);
        interpolationArrayMap.put("pow3InInverse",Interpolation.pow3InInverse);
        interpolationArrayMap.put("pow3OutInverse",Interpolation.pow3OutInverse);
        interpolationArrayMap.put("pow4",Interpolation.pow4);
        interpolationArrayMap.put("pow4In",Interpolation.pow4In);
        interpolationArrayMap.put("pow4Out",Interpolation.pow4Out);
        interpolationArrayMap.put("pow5",Interpolation.pow5);
        interpolationArrayMap.put("pow5In",Interpolation.pow5In);
        interpolationArrayMap.put("pow5Out",Interpolation.pow5Out);
        interpolationArrayMap.put("sin",Interpolation.sine);
        interpolationArrayMap.put("sinIn",Interpolation.sineIn);
        interpolationArrayMap.put("sinOut",Interpolation.sineOut);
        interpolationArrayMap.put("exp10",Interpolation.exp10);
        interpolationArrayMap.put("exp10In",Interpolation.exp10In);
        interpolationArrayMap.put("exp10Out",Interpolation.exp10Out);
        interpolationArrayMap.put("exp5",Interpolation.exp5);
        interpolationArrayMap.put("exp5In",Interpolation.exp5In);
        interpolationArrayMap.put("exp5Out",Interpolation.exp5Out);
        interpolationArrayMap.put("circle",Interpolation.circle);


        interpolationArrayMap.put("circleIn",Interpolation.circleIn);
        interpolationArrayMap.put("circleOut",Interpolation.circleOut);
        interpolationArrayMap.put("elastic",Interpolation.elastic);
        interpolationArrayMap.put("elasticIn",Interpolation.elasticIn);
        interpolationArrayMap.put("elasticOut",Interpolation.elasticOut);


        interpolationArrayMap.put("swing",Interpolation.swing);
        interpolationArrayMap.put("swingIn",Interpolation.swingIn);
        interpolationArrayMap.put("swingOut",Interpolation.swingOut);

        interpolationArrayMap.put("bound",Interpolation.bounce);
        interpolationArrayMap.put("boundIn",Interpolation.bounceIn);
        interpolationArrayMap.put("boundOut",Interpolation.bounceOut);

        interpolationArrayMap.put("elastic",Interpolation.elastic);
        interpolationArrayMap.put("elasticIn",Interpolation.elasticIn);
        interpolationArrayMap.put("elasticOut",Interpolation.elasticOut);



        interpolationArrayMap.put("bounce",Interpolation.bounce);
        interpolationArrayMap.put("bounceOut",Interpolation.bounceOut);
        interpolationArrayMap.put("bounceIn",Interpolation.bounceIn);


        interpolationArrayMap.put("Swing",Interpolation.swing);
        interpolationArrayMap.put("SwingOut",Interpolation.swingIn);
        interpolationArrayMap.put("SwingIn",Interpolation.swingIn);


    }
}
