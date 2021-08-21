package wk.demo.block.screen.utils;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;

public interface Timeline {
    public void apply (Skeleton skeleton,
                       float lastTime,
                       float time,
                       Array<Event> events,
                       float alpha,
                       Animation.MixPose pose,
                       Animation.MixDirection direction);

    public int getPropertyId ();
}

