
package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Helper.SpecialDragreder;

public class Dragreder_appear extends AbstractGameEffect {

	private boolean appear = true;
	
	public Dragreder_appear() {

		this.duration = 3.0F;//倒数时间
		this.startingDuration = 3.0F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.5F && appear) {
				SpecialDragreder.a = 1;
				SpecialDragreder.update();
				appear = false;
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
				SpecialDragreder.a = 5;
				SpecialDragreder.update();
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}