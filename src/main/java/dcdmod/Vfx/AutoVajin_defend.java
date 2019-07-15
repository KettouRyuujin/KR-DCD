package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.Helper.SpecialAutoVajin;

import com.badlogic.gdx.graphics.Color;

public class AutoVajin_defend extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean Start = true;

	public AutoVajin_defend(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 0.5F;//倒数时间
		this.startingDuration = 0.5F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			SpecialAutoVajin.a = 5;
			SpecialAutoVajin.update();
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(Start) {
			SpecialAutoVajin.a = 4;
			SpecialAutoVajin.update();
		}
	}

	public void dispose() {
	}
}