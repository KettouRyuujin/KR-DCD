package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Agito_power_t extends AbstractGameEffect {

	private boolean start = true;
	
	public Agito_power_t() {

		this.duration = 0.6F;//倒数时间
		this.startingDuration = 0.6F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(18);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) {

		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(19);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}