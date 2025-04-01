package Views;

import Models.Effect;

import java.awt.*;

public abstract class EffectView {
    private int x;
    private int y;
    private Effect pair;

    public abstract void Draw(Graphics2D g2d, int x, int y, int width);
}
