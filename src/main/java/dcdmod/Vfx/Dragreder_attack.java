package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Helper.SpecialDragreder;

public class Dragreder_attack extends AbstractGameEffect {

	private boolean attack = true;
	
	public Dragreder_attack(float x, float y) {

		this.duration = 1F;//倒数时间
		this.startingDuration = 1F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			if(SpecialDragreder.a != 4) {
				SpecialDragreder.a = 5;
				SpecialDragreder.update();
			}
		}
	}

	public void render(SpriteBatch sb) {
		if(attack){
			SpecialDragreder.a = 2;
			SpecialDragreder.update();
			attack = false;
		}
	}

	public void dispose() {
	}
}
