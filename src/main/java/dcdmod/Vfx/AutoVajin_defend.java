package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Helper.SpecialAutoVajin;

public class AutoVajin_defend extends AbstractGameEffect {

	private boolean Start = true;

	public AutoVajin_defend() {

		this.duration = 0.5F;//倒数时间
		this.startingDuration = 0.5F;//持续时间

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
			SpecialAutoVajin.a = 4;
			SpecialAutoVajin.update();
			Start = false;
		}
	}

	public void dispose() {
	}
}