package wk.demo.block.screen.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;

public class AttachmentTimeline implements Timeline {
    /**
     * 一个图片显示出来
     *
     * 然后时间到之后使用下一个
     */
    public void xx(){

        //它是去读取slot的
//        timeline.slotIndex = slot.index;
//        for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next)
//            timeline.setFrame(frameIndex++, valueMap.getFloat("time"), valueMap.getString("name"));
//        timelines.add(timeline);
//        duration = Math.max(duration, timeline.getFrames()[timeline.getFrameCount() - 1]);
    }

    @Override
    public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha, Animation.MixPose pose, Animation.MixDirection direction) {

    }

    @Override
    public int getPropertyId() {
        return 0;
    }
    int slotIndex;
    final float[] frames; // time, ...
    final String[] attachmentNames;

    public AttachmentTimeline (int frameCount) {
        frames = new float[frameCount];
        attachmentNames = new String[frameCount];
    }

    public int getPropertyId () {
        return (TimelineType.attachment.ordinal() << 24) + slotIndex;
    }

    /** The number of key frames for this timeline. */
    public int getFrameCount () {
        return frames.length;
    }

    public void setSlotIndex (int index) {
        if (index < 0) throw new IllegalArgumentException("index must be >= 0.");
        this.slotIndex = index;
    }

    /** The index of the slot in {@link Skeleton#getSlots()} that will be changed. */
    public int getSlotIndex () {
        return slotIndex;
    }

    /** The time in seconds for each key frame. */
    public float[] getFrames () {
        return frames;
    }

    /** The attachment name for each key frame. May contain null values to clear the attachment. */
    public String[] getAttachmentNames () {
        return attachmentNames;
    }

    /** Sets the time in seconds and the attachment name for the specified key frame. */
    public void setFrame (int frameIndex, float time, String attachmentName) {
        frames[frameIndex] = time;
        attachmentNames[frameIndex] = attachmentName;
    }

    public void apply (Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha, MixPose pose,
                       MixDirection direction) {

        Slot slot = skeleton.slots.get(slotIndex);
        if (direction == out && pose == setup) {
            String attachmentName = slot.data.attachmentName;
            slot.setAttachment(attachmentName == null ? null : skeleton.getAttachment(slotIndex, attachmentName));
            return;
        }

        float[] frames = this.frames;
        if (time < frames[0]) { // Time is before first frame.
            if (pose == setup) {
                String attachmentName = slot.data.attachmentName;
                slot.setAttachment(attachmentName == null ? null : skeleton.getAttachment(slotIndex, attachmentName));
            }
            return;
        }

        int frameIndex;
        if (time >= frames[frames.length - 1]) // Time is after last frame.
            frameIndex = frames.length - 1;
        else
            frameIndex = binarySearch(frames, time) - 1;

        String attachmentName = attachmentNames[frameIndex];
        slot.setAttachment(attachmentName == null ? null : skeleton.getAttachment(slotIndex, attachmentName));
    }
}
