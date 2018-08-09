package com.diabin.jbox.widget;

import android.util.Log;
import android.view.View;

import com.diabin.jbox.R;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Position;

import java.util.Random;

public class JboxImpl {
    private static final String TAG = "mu@" + JboxImpl.class.getSimpleName();
    private World mWorld;//模拟世界
    private float dt = 1f / 60;//模拟世界运动频率
    private int mVelocityIterations = 5;//速率迭代器
    private int mPositionIterations = 20;//迭代次数
    private int mWidth, mHeight;
    private float mDensity = 0.5f;
    private float mRatio = 50;//坐标映射比例
    private final Random mRandom = new Random();

    public JboxImpl(float density) {
        this.mDensity = density;
    }

    public void setWorldSize(int width, int height) {
        mHeight = height;
        mWidth = width;
    }

    public void createWorld() {
        if (mWorld == null) {
            mWorld = new World(new Vec2(0, 10.0f));
            updateVerticalBounds();
            updateHorizontalBounds();
        }
    }

    private void updateVerticalBounds() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;

        PolygonShape box = new PolygonShape();
        float boxWidth = switchPositionToBody(mWidth);
        float boxHeight = switchPositionToBody(mRatio);
        box.setAsBox(boxWidth, boxHeight);// 确定为矩形

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = mDensity;
        fixtureDef.friction = 0.8f;//摩擦系数
        fixtureDef.restitution = 0.5f;// 补偿系数

        bodyDef.position.set(0, -boxHeight);
        Body topBody = mWorld.createBody(bodyDef);//创建一个真实的body
        topBody.createFixture(fixtureDef);

        bodyDef.position.set(0, switchPositionToBody(mHeight) + boxHeight);
        Body bottomBody = mWorld.createBody(bodyDef);//创建一个真实的body
        bottomBody.createFixture(fixtureDef);
    }

    private void updateHorizontalBounds() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;

        PolygonShape box = new PolygonShape();
        float boxWidth = switchPositionToBody(mRatio);
        float boxHeight = switchPositionToBody(mHeight);
        box.setAsBox(boxWidth, boxHeight);// 确定为矩形

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = mDensity;
        fixtureDef.friction = 0.8f;//摩擦系数
        fixtureDef.restitution = 0.5f;// 补偿系数

        bodyDef.position.set(-boxWidth, 0);
        Body leftBody = mWorld.createBody(bodyDef);//创建一个真实的body
        leftBody.createFixture(fixtureDef);

        bodyDef.position.set(switchPositionToBody(mWidth) + boxWidth, 0);
        Body rightBody = mWorld.createBody(bodyDef);//创建一个真实的body
        rightBody.createFixture(fixtureDef);
    }

    //view坐标映射为物理坐标
    private float switchPositionToBody(float viewPosition) {
        return viewPosition / mRatio;
    }

    public boolean isBodyView(View view) {
        Body body = (Body) view.getTag(R.id.dn_view_body_tag);
        return body != null;
    }

    public void createBody(View view) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.setType(BodyType.DYNAMIC);
        bodyDef.position.set(switchPositionToBody(
                view.getX() + view.getWidth() / 2), switchPositionToBody(
                view.getY() + view.getHeight() / 2));
        Shape shape = null;
        Boolean isCircle = (Boolean) view.getTag(R.id.dn_view_circle_tag);
        if (isCircle != null && isCircle) {
            shape = createCircleShape(switchPositionToBody
                    (view.getWidth() / 2));

        } else {
            Log.i(TAG, "createBody view tag is not circle!!!");
            return;
        }
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(shape);
        fixtureDef.friction = 0.8f;
        fixtureDef.density = mDensity;
        fixtureDef.restitution = 0.5f;
        Body body = mWorld.createBody(bodyDef);
        body.createFixture(fixtureDef);
        view.setTag(R.id.dn_view_body_tag, body);
        body.setLinearVelocity(new Vec2(mRandom.nextFloat(), mRandom.nextFloat()));
    }

    private Shape createCircleShape(float radius) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        return circleShape;
    }

    public void startWorld() {
        if (mWorld != null) {
            mWorld.step(dt, mVelocityIterations, mPositionIterations);
        }
    }

    public float getViewX(View view) {
        Body body = (Body) view.getTag(R.id.dn_view_body_tag);
        if (body != null) {
            return switchPositionToView(body.getPosition().x) - view.getWidth() / 2;
        }
        return 0;
    }

    public float getViewY(View view) {
        Body body = (Body) view.getTag(R.id.dn_view_body_tag);
        if (body != null) {
            return switchPositionToView(body.getPosition().y) - view.getHeight() / 2;
        }
        return 0;
    }

    //物理坐标映射为view坐标
    private float switchPositionToView(float bodyPosition) {
        return bodyPosition * mRatio;
    }

    public float getViewRotation(View view) {
        Body body = (Body) view.getTag(R.id.dn_view_body_tag);
        if (body != null) {
            float angle = body.getAngle();
            return (float) (angle / Math.PI * 180f) % 360;
        }
        return 0;
    }

    public void applyLinearImpulse(float x, float y, View view) {
        Body body = (Body) view.getTag(R.id.dn_view_body_tag);
        Vec2 impulse = new Vec2(x, y);
        body.applyLinearImpulse(impulse, body.getPosition(), true);
    }
}
