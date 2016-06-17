/**
 * Created by 804314 on 09.04.2016.
 */
public enum Color {
    BLACK(false),
    RED(true);

    public final boolean isRed;
    public final boolean isBlack;

    private Color(boolean value) {
        this.isRed = value;
        this.isBlack = !value;
    }

    public Color not() {
        return this.isRed ? BLACK : RED;
    }
}
