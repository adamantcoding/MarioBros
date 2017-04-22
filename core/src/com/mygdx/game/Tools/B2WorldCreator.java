package com.mygdx.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Sprites.Brick;
import com.mygdx.game.Sprites.Coin;

/**
 * Created by Ivan on 21.4.2017..
 */

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){         //GROUND
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth()/2) / MarioBros.PPM, (rectangle.getY() + rectangle.getHeight()/ 2 ) /MarioBros.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rectangle.getWidth()/2/MarioBros.PPM, rectangle.getHeight()/2/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){         //PIPES
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth()/2) / MarioBros.PPM, (rectangle.getY() + rectangle.getHeight()/ 2 ) /MarioBros.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rectangle.getWidth()/2/MarioBros.PPM, rectangle.getHeight()/2/MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){         //BRICKS
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Brick(world, map, rectangle);
        }

        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){         //COINS
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Coin(world, map, rectangle);
        }
    }
}
