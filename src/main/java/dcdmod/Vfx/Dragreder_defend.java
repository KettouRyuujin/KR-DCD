package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.Helper.SpecialDragreder;

import com.badlogic.gdx.graphics.Color;

public class Dragreder_defend extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean defend = true;
	boolean defendstart = true;
	
	public Dragreder_defend(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 3.0F;//倒数时间
		this.startingDuration = 3.0F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.3F && defend) {
			defend = false;
			if(SpecialDragreder.a != 4) {
				SpecialDragreder.a = 3;
				SpecialDragreder.update();
			}
		}
		if (this.duration < 0) {
			this.isDone = true;
			if(SpecialDragreder.a != 4) {
				SpecialDragreder.a = 5;
				SpecialDragreder.update();
			}
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(defendstart) {
			if(SpecialDragreder.a != 4) {
				SpecialDragreder.a = 6;
				SpecialDragreder.update();
				defendstart = false;
			}
		}
	}

	public void dispose() {
	}
}