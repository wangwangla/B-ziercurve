package wk.demo.block.screen.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;

public class ReadJsonFile {
    public void test(){
//        TextureAtlas atlas = new TextureAtlas(new FileHandle("pohuai_3x3.atlas"));
        SkeletonJson json = new SkeletonJson();
        SkeletonData skeletonData = json.readSkeletonData(new FileHandle("pohuai_3x3.json"));
        System.out.println("xxxxx");
    }

    public static void main(String[] args) {
        ReadJsonFile readJsonFile = new ReadJsonFile();
        readJsonFile.test();
    }
}
