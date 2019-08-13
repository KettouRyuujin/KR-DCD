package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Helper.SpecialDragreder;

public class Dragreder_defend extends AbstractGameEffect {

	private boolean defend = true;
	private boolean defendstart = true;
	
	public Dragreder_defend() {

		this.duration = 3.0F;//倒数时间
		this.startingDuration = 3.0F;//持续时间

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