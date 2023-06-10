package wk.demo.block.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LibraryGroup extends Group {
    public LibraryGroup(){
        Image library = new Image(new NinePatch(
                new Texture("white_100x100.png"),
                20,20,20,20));
        addActor(library);
        library.setSize(300,1200);
        setX(1220);
        setSize(library.getWidth(),library.getHeight());
    }
}
