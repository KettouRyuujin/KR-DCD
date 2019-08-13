
package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Helper.SpecialDragreder;
import dcdmod.Patches.AbstractSummonedAnimation;

public class Dragreder_disappear extends AbstractGameEffect {

	private boolean disappear = true;
	
	public Dragreder_disappear() {

		this.duration = 3.0F;//倒数时间
		this.startingDuration = 3.0F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			AbstractSummonedAnimation.clear("DRAGREDER");
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		if(disappear) {
			SpecialDragreder.a = 4;
			SpecialDragreder.update();
			disappear = false;
		}
	}

	public void dispose() {
	}
}