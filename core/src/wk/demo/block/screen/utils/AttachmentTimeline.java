package wk.demo.block.screen.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;

/**
 * 这个动画和附件有关系的
 * 开始到时候也和其他的动画一样
 * time1  resource1
 * time2  resource2
 * time3  resource3
 * time4  resource4
 * time5  resource5
 *
 * apply时间段内使用那个rource
 *
 */
public class AttachmentTimeline implements Timeline {

    int slotIndex;
    final float[] frames; // time, ...
    final String[] attachmentNames;

    public AttachmentTimeline (int frameCount) {
        frames = new float[frameCount];
        attachmentNames = new String[frameCount];
    }

    @Override
    public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha, Animation.MixPose pose, Animation.MixDirection direction) {

    }

    /** Sets the time in seconds and the attachment name for the specified key frame. */
    public void setFrame (int frameIndex, float time, String attachmentName) {
        frames[frameIndex] = time;
        attachmentNames[frameIndex] = attachmentName;
    }
}
