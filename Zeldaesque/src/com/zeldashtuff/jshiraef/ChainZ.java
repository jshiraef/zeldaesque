//package com.zeldashtuff.jshiraef;
//
//import org.jbox2d.collision.shapes.EdgeShape;
//import org.jbox2d.collision.shapes.PolygonShape;
//import org.jbox2d.common.Vec2;
//import org.jbox2d.dynamics.Body;
//import org.jbox2d.dynamics.BodyDef;
//import org.jbox2d.dynamics.BodyType;
//import org.jbox2d.dynamics.FixtureDef;
//import org.jbox2d.dynamics.joints.RevoluteJointDef;
//import org.jbox2d.testbed.framework.TestbedTest;
//
//public class ChainZ extends TestbedTest {
//	public boolean isSaveLoadEnabled() {
//		return true;
//	}
//	@Override
//	public void initTest(boolean deserialized) {
//			if (deserialized) {
//				return;
//			}
//			Body ground = null;
//			{
//				BodyDef bd = new BodyDef();
//				ground = getWorld().createBody(bd);
//				EdgeShape shape = new EdgeShape();
//				shape.set(new Vec2(-40.0f, 0.0f), new Vec2(40.0f, 0.0f));
//				ground.createFixture(shape, 0.0f);
//			}
//			{
//				PolygonShape shape = new PolygonShape();
//				shape.setAsBox(0.6f, 0.125f);
//				FixtureDef fd = new FixtureDef();
//				fd.shape = shape;
//				fd.density = 20.0f;
//				fd.friction = 0.2f;
//				RevoluteJointDef jd = new RevoluteJointDef();
//				jd.collideConnected = false;
//				final float y = 25.0f;
//				Body prevBody = ground;
//				
//				for (int i = 0; i < 30; ++i) {
//					BodyDef bd = new BodyDef();
//					bd.type = BodyType.DYNAMIC;
//					bd.position.set(0.5f + i, y);
//					Body body = getWorld().createBody(bd);
//					body.createFixture(fd);
//					Vec2 anchor = new Vec2(i, y);
//					jd.initialize(prevBody, body, anchor);
//					getWorld().createJoint(jd);
//					prevBody = body;
//				}
//			}
//	}
//	
//	@Override
//	public String getTestName() {
//		return "Chain";
//	}
//}