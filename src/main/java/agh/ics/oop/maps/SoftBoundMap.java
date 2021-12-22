package agh.ics.oop.maps;

import agh.ics.oop.Vector2d;
import agh.ics.oop.mapparts.IMapElement;

public class SoftBoundMap extends AbstractMap{

    public SoftBoundMap(int width, int height, int animalsInitialEnergy, int plantsEnergy, double jungleRatio) {
        super(width, height, animalsInitialEnergy, plantsEnergy, jungleRatio);
    }

    @Override
    public Vector2d correctPosition(Vector2d position) {
        if (position.x > this.width || position.y > this.height || position.x < -1 || position.y < -1 ||
                (position.x >= this.width && position.y >= this.height) ||
                (position.x >= this.width && position.y < 0) ||
                (position.x < 0 && position.y >= this.height) ||
                (position.x < 0 && position.y < 0))
            throw new RuntimeException("Illegal position");

        if (position.x > this.width-1 || position.x < 0)
            return new Vector2d(position.x % this.width, position.y);

        if (position.y > this.height-1 || position.y < 0)
            return new Vector2d(position.x, position.y % this.height);

        return position;
    }

}