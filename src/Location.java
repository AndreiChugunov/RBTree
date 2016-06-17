/**
 * Created by 804314 on 09.04.2016.
 */
public enum Location {
    RIGHT(true, false),
    LEFT(false, true),
    CENTER(false, false);

    public final boolean isRight;
    public final boolean isLeft;

    private Location(boolean right, boolean left) {
        this.isRight = right;
        this.isLeft = left;
    }
}
