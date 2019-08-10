package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Helper.SpecialAutoVajin;
import com.badlogic.gdx.graphics.Color;

public class AutoVajin_attack extends AbstractGameEffect {

	private boolean Start = true;

	public AutoVajin_attack() {

		this.duration = 1.0F;//倒数时间
		this.startingDuration = 1.0F;//持续时间
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
		if(Start) {
			SpecialAutoVajin.a = 2;
			SpecialAutoVajin.update();
			Start = false;
		}
	}

	public void dispose() {
	}
}